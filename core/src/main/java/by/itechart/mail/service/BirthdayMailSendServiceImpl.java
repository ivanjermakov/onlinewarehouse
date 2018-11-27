package by.itechart.mail.service;

import by.itechart.common.entity.User;
import by.itechart.common.service.UserService;
import by.itechart.mail.entity.BirthdayMailSend;
import by.itechart.mail.repository.BirthdayMailSendRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigInteger;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class BirthdayMailSendServiceImpl implements BirthdayMailSendService {

    private final BirthdayMailSendRepository birthdayMailSendRepository;
    private final UserService userService;

    @Autowired
    public BirthdayMailSendServiceImpl(BirthdayMailSendRepository birthdayMailSendRepository, UserService userService) {
        this.birthdayMailSendRepository = birthdayMailSendRepository;
        this.userService = userService;
    }

    @Override
    public void generate(User user, Boolean isSuccessful) {
        BirthdayMailSend birthdayMailSend = new BirthdayMailSend();
        birthdayMailSend.setUser(user);
        birthdayMailSend.setDate(LocalDate.now());
        birthdayMailSend.setSuccessful(isSuccessful);
        birthdayMailSendRepository.save(birthdayMailSend);
    }

    @Override
    public List<User> getUsersToCongratulate() {
        List<Long> us = birthdayMailSendRepository.getUsersToCongratulate(LocalDate.now())
                .stream()
                .map(BigInteger::longValue)
                .collect(Collectors.toList());
        return new ArrayList<>(userService.getAllById(us));
    }

    @Override
    public List<User> getNotCelebratedUsers() {
        return getUsersToCongratulate().stream()
                .filter(u -> {
                    System.out.println("COUNT: " + getCelebrateAttemptsCount(u, LocalDate.now()));
                    return getCelebrateAttemptsCount(u, LocalDate.now()) == 5;
                })
                .collect(Collectors.toList());
    }

    private int getCelebrateAttemptsCount(User u, LocalDate now) {
        return birthdayMailSendRepository.getCelebrateAttemptsCount(u.getId(), now);
    }
}
