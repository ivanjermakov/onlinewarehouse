package by.itechart.web.controller;

import by.itechart.common.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ActivationController {

    private UserService userService;

    @Autowired
    public ActivationController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/login/{code}")
    public String activateUser(@PathVariable String code) {
        boolean isActivated = userService.activateUser(code);

        System.out.println(isActivated);
        return null;
    }
}
