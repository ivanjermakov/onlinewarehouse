package by.itechart.web.controller;

import by.itechart.common.dto.UserDto;
import by.itechart.common.entity.Address;
import by.itechart.common.entity.User;
import by.itechart.common.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

@RestController
@RequestMapping("/companies/{companyId}/users")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public Page<UserDto> getUsersList(@PathVariable long companyId, Pageable pageable) {
//        ArrayList<User> users = new ArrayList<>();
//        //get users by pageable and return page. Pageable = ?page=1&size=5 for example
//        for (int i = 0; i < 10; i++) {
//            users.add(createUser(i));
//        }
//        return users;
        return userService.getUsers(companyId, pageable);
    }

    @PostMapping
    public Long saveUser(@PathVariable long companyId, @RequestBody User user) {
        //save user and return id
//        Long userId = new Long(15);
//        return userId;
        return userService.saveOrUpdateUser(user);
    }

    @GetMapping("/{userId}")
    public User getUser(@PathVariable long companyId, @PathVariable long userId) {
        //get user
//        User user = createUser(userId);
//        return user;
        return userService.getUser(userId);
    }

    @PutMapping("/{userId}")
    public Long editUser(@PathVariable long companyId, @PathVariable long userId, @RequestParam User user) {
        //edit user
//        return userId;
        return userService.saveOrUpdateUser(user);
    }

    @ResponseStatus(HttpStatus.OK)
    @DeleteMapping("/{userId}")
    public void deleteUser(@PathVariable long companyId, @PathVariable long userId) {
        //delete user
        userService.deleteUser(userId);
    }

    private User createUser(long i) {
        Address address = new Address();
        address.setCountry("country");
        address.setLocality("locality");
        address.setRegion("region");
        User user = new User();
        user.setFirstname("name_" + i);
        user.setLastname("last_name");
        user.setBirth(LocalDate.now());
        user.setEmail("email@email.com");
        user.setAddress(address);
        return user;
    }
}
