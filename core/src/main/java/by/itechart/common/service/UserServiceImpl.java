package by.itechart.common.service;

import by.itechart.common.dto.UserDto;
import by.itechart.common.dto.UserFilter;
import by.itechart.common.entity.User;
import by.itechart.common.repository.UserRepository;
import by.itechart.common.utils.ObjectMapperUtils;
import by.itechart.exception.NotFoundEntityException;
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

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public Page<UserDto> getUsers(Long companyId, UserFilter userFilter, Pageable pageable) {
        return userRepository.findAll(UserPredicate.findFilter(companyId, userFilter), pageable)
                .map(user -> ObjectMapperUtils.map(user, UserDto.class));
    }

    @Override
    public Long saveOrUpdateUser(User user) {
        return userRepository.save(user).getId();
    }

    @Override
    public User getUser(Long userId) {
        return userRepository.findById(userId).orElseThrow(() -> new NotFoundEntityException("User"));
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
