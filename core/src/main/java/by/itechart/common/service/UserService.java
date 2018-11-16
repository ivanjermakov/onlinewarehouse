package by.itechart.common.service;

import by.itechart.common.dto.UserDto;
import by.itechart.common.dto.UserFilter;
import by.itechart.common.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface UserService {

    Page<UserDto> getUsers(Long companyId, UserFilter userFilter, Pageable pageable);

    Long saveOrUpdateUser(User user);

    User getUser(Long userId);

    void deleteUser(Long userId);
}
