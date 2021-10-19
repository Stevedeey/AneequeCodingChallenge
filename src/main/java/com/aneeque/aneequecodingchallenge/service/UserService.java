package com.aneeque.aneequecodingchallenge.service;

import com.aneeque.aneequecodingchallenge.dto.UserDto;
import com.aneeque.aneequecodingchallenge.model.User;
import com.aneeque.aneequecodingchallenge.payload.request.SignupRequest;

import java.util.Set;

public interface UserService {

    UserDto registration(UserDto userDto);
    Set<UserDto> getAllUsers();

}
