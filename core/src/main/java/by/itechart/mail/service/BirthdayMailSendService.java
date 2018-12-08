package by.itechart.mail.service;

import by.itechart.common.entity.User;
import by.itechart.mail.entity.BirthdayMailTemplate;

import java.util.List;

public interface BirthdayMailSendService {

    void generate(User user, Boolean isSuccessful);

    /**
     * @return set of users that wasn't congratulated today
     */
    List<User> getUsersToCongratulate();

    List<User> getNotCelebratedUsers();
}
