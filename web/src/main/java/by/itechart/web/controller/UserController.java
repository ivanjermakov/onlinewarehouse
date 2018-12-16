package by.itechart.web.controller;

import by.itechart.common.dto.AuthorityDto;
import by.itechart.common.dto.CreateUserDto;
import by.itechart.common.dto.UserDto;
import by.itechart.common.dto.UserFilter;
import by.itechart.common.entity.User;
import by.itechart.common.repository.PieChartData;
import by.itechart.common.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/companies/{companyId}/users")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public Page<UserDto> getUsersList(@PathVariable long companyId,
                                      UserFilter userFilter,
                                      Pageable pageable) {
        return userService.getUsers(companyId, userFilter, pageable);
    }

    @GetMapping("/role-statistics")
    public List<PieChartData> getUserRolesStatistics(@PathVariable long companyId) {
        return userService.getUserRoleStatistics(companyId);
    }

    @PostMapping
    public Long saveUser(@PathVariable long companyId, @RequestBody CreateUserDto createUserDto) {
        return userService.saveUser(companyId, createUserDto);
    }

    @GetMapping("/{userId}")
    public UserDto getUser(@PathVariable long companyId, @PathVariable long userId) {
        return userService.getUser(userId, companyId);
    }

    @PutMapping("/{userId}")
    public Long editUser(@PathVariable long companyId, @PathVariable long userId, @RequestParam User user) {
        return userService.saveUser(user);
    }

    @PutMapping("/{userId}/authorities")
    public Long changeUserAuthorities(@PathVariable long companyId,
                                      @PathVariable long userId,
                                      @RequestBody List<AuthorityDto> authorities) {
        return userService.changeUserAuthorities(userId, companyId, authorities);
    }

    @PostMapping("/{userId}/change-enabled")
    public Long changeEnabledValue(@PathVariable long companyId,
                                   @PathVariable long userId) {
        return userService.changeEnabledValue(userId, companyId);
    }

    @PostMapping("/{userId}/set-deleted")
    public Long setDeleted(@PathVariable long companyId,
                           @PathVariable long userId) {
        return userService.setDeleted(userId, companyId);
    }

    @PostMapping("/{userId}/reset-password")
    public Long resetPassword(@PathVariable long companyId,
                              @PathVariable long userId) {
        return userService.resetPassword(userId, companyId);
    }


    @GetMapping("/validate-username")
    public Boolean validateUsername(String username) {
        return userService.canUseUsername(username);
    }
}
