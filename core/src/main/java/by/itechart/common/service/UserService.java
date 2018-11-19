package by.itechart.common.service;

import by.itechart.common.dto.UserDto;
import by.itechart.common.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;

public interface UserService {

    Page<UserDto> getUsers(Long companyId, Pageable pageable);

    Long saveOrUpdateUser(User user);

    User getUser(Long userId);

    void deleteUser(Long userId);

    Set<User> getUsersWithBirthday(Long companyId, LocalDate birthDay);

    Set<User> getAllById(List<Long> id);
}
