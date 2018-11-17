package by.itechart.web.controller;

import by.itechart.common.dto.UserDto;
import by.itechart.common.dto.UserFilter;
import by.itechart.common.entity.User;
import by.itechart.common.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

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

    @PostMapping
    public Long saveUser(@PathVariable long companyId, @RequestBody User user) {
        return userService.saveOrUpdateUser(user);
    }

    @GetMapping("/{userId}")
    public User getUser(@PathVariable long companyId, @PathVariable long userId) {
        return userService.getUser(userId);
    }

    @PutMapping("/{userId}")
    public Long editUser(@PathVariable long companyId, @PathVariable long userId, @RequestParam User user) {
        return userService.saveOrUpdateUser(user);
    }

    @ResponseStatus(HttpStatus.OK)
    @DeleteMapping("/{userId}")
    public void deleteUser(@PathVariable long companyId, @PathVariable long userId) {
        userService.deleteUser(userId);
    }
}
