package by.itechart.web.controller;

import by.itechart.common.entity.Address;
import by.itechart.common.entity.User;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.awt.print.Pageable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/company")
public class UserController {

    @GetMapping("/{companyId}/users")
    public List<User> getUsersList(@PathVariable long companyId, @RequestParam(required = false) Pageable pageable) {
        ArrayList<User> users = new ArrayList<>();
        //get users by pageable and return page. Pageable = ?page=1&size=5 for example
        for (int i = 0; i < 10; i++) {
            users.add(createUser(i));
        }
        return users;
    }

    @PostMapping("/{companyId}/users")
    public Long saveUser(@PathVariable long companyId, @RequestBody User user) {
        //save user and return id
        Long userId = new Long(15);
        return userId;
    }

    @GetMapping("/{companyId}/user/{userId}")
    public User getUser(@PathVariable long companyId, @PathVariable long userId) {
        //get user
        User user = createUser(userId);
        return user;
    }

    @PutMapping("/{companyId}/user/{userId}")
    public Long editUser(@PathVariable long companyId, @PathVariable long userId, @RequestParam User user) {
        //edit user
        return userId;
    }

    @DeleteMapping("/{companyId}/user/{userId}")
    public ResponseEntity deleteUser(@PathVariable long companyId, @PathVariable long userId) {
        //delete user
        return ResponseEntity.ok(null);
    }

    private User createUser(long i) {
        Address address = new Address();
        address.setCountry("country");
        address.setLocality("locality");
        address.setRegion("region");
        User user = new User();
        user.setFirstName("name_" + i);
        user.setLastName("last_name");
        user.setBirth(LocalDate.now());
        user.setEmail("email@email.com");
        user.setAddress(address);
        return user;
    }
}
