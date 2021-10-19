package com.aneeque.aneequecodingchallenge.controller;

import com.aneeque.aneequecodingchallenge.dto.UserDto;
import com.aneeque.aneequecodingchallenge.payload.request.SignupRequest;
import com.aneeque.aneequecodingchallenge.payload.response.UserRegistrationResponse;
import com.aneeque.aneequecodingchallenge.service.UserService;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("api/auth")
public class UserAuthController {

    @Autowired
    private UserService userService;

    @PostMapping("/signup")
    public ResponseEntity<UserRegistrationResponse> signUpUser(@Valid @RequestBody SignupRequest signupRequest){

        ModelMapper modelMapper = new ModelMapper();
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
        UserDto userDto =  modelMapper.map(signupRequest, UserDto.class);

        UserDto user =  userService.registration(userDto);

        UserRegistrationResponse userRegistrationResponse = modelMapper.map(userDto, UserRegistrationResponse.class);


        return new ResponseEntity<>(userRegistrationResponse, HttpStatus.CREATED);
    }


}
