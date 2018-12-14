package by.itechart.web.controller;

import by.itechart.common.dto.UserActivationDto;
import by.itechart.common.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class ActivationController {

    private UserService userService;

    @Autowired
    public ActivationController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/activate/{code}")
    public UserActivationDto activateUser(@PathVariable String code) {
        return userService.activateUser(code);
    }

    @PostMapping("/reset-password")
    public void resetPassword(@RequestBody UserActivationDto user) {
        userService.setPassword(user);
    }
}
