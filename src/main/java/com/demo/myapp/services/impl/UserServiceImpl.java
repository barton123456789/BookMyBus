package com.demo.myapp.services.impl;

import com.demo.myapp.entity.User;
import com.demo.myapp.payloads.UserDto;
import com.demo.myapp.repository.UserRepo;
import com.demo.myapp.services.UserService;
import com.demo.myapp.exception.ResourceNotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public UserDto createUser(UserDto userDto) {
        User user = this.dtoToUser(userDto);
        User savedUser = this.userRepo.save(user);
        return this.userToDto(savedUser);
    }
    @Override
    public UserDto updateUser(UserDto userDto, Integer userId) {
        User user = this.userRepo.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User", "id", userId));
        user.setFull_Name(userDto.getFull_Name());
        user.setEmail(userDto.getEmail());
        user.setPassword(userDto.getPassword());
        user.setRole(userDto.getRole());
        User updatedUser = this.userRepo.save(user);
        UserDto userDto1 = this.userToDto(updatedUser);
        return userDto1;
    }

    @Override
    public UserDto getUserById(Integer userId) {
        User user = this.userRepo.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User", "Id", userId));
        return this.userToDto(user);
    }

    @Override
    @Transactional
    public void setUserInfoById(int user_id) {
        this.userRepo.setUserInfoById(user_id);
    }

    @Override
    public List<UserDto> getAllUsers() {
        List<User> users = this.userRepo.findAll();
        List<UserDto> userDtos = users.stream().map(user -> this.userToDto(user)).collect(Collectors.toList());
        return userDtos;
    }

    @Override
    public void deleteUser(Integer userId) {
        User user = this.userRepo.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User", "Id", userId));
        this.userRepo.delete(user);
    }

    @Override
    public User getUserByEmail(String email) {
        User user = this.userRepo.findByEmail(email);
        return user;
    }

    private User dtoToUser(UserDto userDto) {
        User user = this.modelMapper.map(userDto, User.class);
//        user.setUser_id(userDto.getUser_id());
//        user.setFull_Name(userDto.getFull_Name());
//        user.setEmail(userDto.getEmail());
//        user.setPassword(userDto.getPassword());
//        user.setGender(userDto.getGender());
//        user.setAge(userDto.getAge());
//        user.setRole(userDto.getRole());
        return user;
    }

    public UserDto userToDto(User user) {
        UserDto userDto = this.modelMapper.map(user, UserDto.class);
//        userDto.setUser_id(user.getUser_id());
//        userDto.setFull_Name(user.getFull_Name());
//        userDto.setEmail(user.getEmail());
//        userDto.setPassword(user.getPassword());
//        userDto.setGender(user.getGender());
//        userDto.setAge(user.getAge());
//        userDto.setRole(user.getRole());
        return userDto;
    }
}

