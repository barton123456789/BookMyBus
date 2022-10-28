package com.demo.myapp.controller;

import com.demo.myapp.entity.User;
import com.demo.myapp.payloads.Response;
import com.demo.myapp.payloads.UserDto;
import com.demo.myapp.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/app/users")
@CrossOrigin(origins = "http://localhost:3000")
public class UserController {
    @Autowired
    private UserService userservice;

    @PostMapping("/")
    public ResponseEntity<UserDto> createUser(@Valid @RequestBody UserDto userdto) {
        UserDto createuserdto = this.userservice.createUser(userdto);
        return new ResponseEntity<>(createuserdto, HttpStatus.CREATED);
    }

    @PutMapping("/{userId}/")
    public ResponseEntity<UserDto> updateUser(@Valid @RequestBody UserDto userdto, @PathVariable Integer userId) {
        UserDto updateUser = this.userservice.updateUser(userdto, userId);
        return ResponseEntity.ok(updateUser);
    }

    @DeleteMapping("/{userId}")
    public ResponseEntity<Response> deleteUser(@PathVariable("userId") Integer uid) {
        this.userservice.deleteUser(uid);
        return new ResponseEntity(new Response("user deleted successfully", true), HttpStatus.OK);
    }

    @GetMapping("/")
    public ResponseEntity<List<UserDto>> getAllUsers() {
        return ResponseEntity.ok(this.userservice.getAllUsers());
    }

    @GetMapping("/{userId}/")
    public UserDto getSingleUsers(@PathVariable Integer userId) {
        UserDto dto = this.userservice.getUserById(userId);
        return dto;
    }

//    @GetMapping("/userid")
//    public userdto getSingleUsersWithRequestParam(@RequestParam("userid") Integer userId) {
//        userdto dto = this.userservice.getUserById(userId);
//        return dto;
//    }

    @GetMapping(value = "/{email}")
    public User getUserByEmail(@PathVariable("email") String email) {
        User user = this.userservice.getUserByEmail(email);
        return user;

    }

    @PutMapping("/change_role")
    public void setUserInfoById(@RequestParam int userId) {
        this.userservice.setUserInfoById(userId);
    }
}




