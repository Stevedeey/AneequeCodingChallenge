package com.aneeque.aneequecodingchallenge.service;

import com.aneeque.aneequecodingchallenge.dto.UserDto;
import com.aneeque.aneequecodingchallenge.model.User;
import com.aneeque.aneequecodingchallenge.payload.request.SignupRequest;

public interface UserService {

    UserDto registration(UserDto userDto);

}
