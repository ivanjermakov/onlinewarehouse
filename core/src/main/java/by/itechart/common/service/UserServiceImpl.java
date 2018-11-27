package by.itechart.common.service;

import by.itechart.common.dto.UserDto;
import by.itechart.common.dto.UserFilter;
import by.itechart.common.entity.User;
import by.itechart.common.repository.UserRepository;
import by.itechart.common.utils.ObjectMapperUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;

@Service
@Transactional
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public Page<UserDto> getUsers(Long companyId, UserFilter userFilter, Pageable pageable) {
        Page<User> users = userRepository.findAll(UserPredicate.findFilter(companyId, userFilter), pageable);
        return users.map(user -> ObjectMapperUtils.map(user, UserDto.class));
    }

    @Override
    public Long saveOrUpdateUser(User user) {
        return userRepository.save(user).getId();
    }

    @Override
    public User getUser(Long userId) {
        return userRepository.findById(userId).orElse(null);
    }

    @Override
    public void deleteUser(Long userId) {
        userRepository.setDeleted(userId);
    }

    @Override
    public Set<User> getUsersWithBirthday(Long companyId, LocalDate birthDay) {
        return userRepository.getUsersWithBirthday(companyId, birthDay);
    }

    @Override
    public Set<User> getAllById(List<Long> id) {
        return userRepository.findAllById(id);
    }
}
