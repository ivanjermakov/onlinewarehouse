package by.itechart.common.service;

import by.itechart.common.dto.AuthorityDto;
import by.itechart.common.dto.CreateUserDto;
import by.itechart.common.dto.UserDto;
import by.itechart.common.dto.UserFilter;
import by.itechart.common.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import java.time.LocalDate;
import java.util.List;
import java.util.Set;

@Validated
public interface UserService {

    Page<UserDto> getUsers(Long companyId, UserFilter userFilter, Pageable pageable);

    //    TODO UserDto
    Long saveUser(@Valid User user);

    Long saveUser(long companyId, CreateUserDto createUserDto);

    User getUser(Long userId);

    UserDto getUser(Long userId, Long companyId);

    Set<User> getUsersWithBirthday(Long companyId, LocalDate birthDay);

    Set<User> getAllById(List<Long> id);

    Boolean canUseUsername(String username);

    Long changeUserAuthorities(Long userId, Long companyId, List<AuthorityDto> authorities);

    Long changeEnabledValue(long userId, long companyId);

    Long setDeleted(long userId, long companyId);

    Long resetPassword(long userId, long companyId);

    boolean activateUser(String code);
}
