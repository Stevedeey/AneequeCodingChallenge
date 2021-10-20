package com.aneeque.aneequecodingchallenge.controller;

import com.aneeque.aneequecodingchallenge.dto.UserDto;
import com.aneeque.aneequecodingchallenge.model.User;
import com.aneeque.aneequecodingchallenge.payload.request.LoginRequest;
import com.aneeque.aneequecodingchallenge.payload.request.SignupRequest;
import com.aneeque.aneequecodingchallenge.payload.response.UserRegistrationResponse;
import com.aneeque.aneequecodingchallenge.service.UserService;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.Optional;

@RestController
@RequestMapping("api/auth")
public class UserAuthController {

    @Autowired
    private UserService userService;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @PostMapping("/login")
    public ResponseEntity<?> login(HttpSession session, @Valid @RequestBody LoginRequest loginRequest){
        Optional<User> user = Optional.ofNullable(userService.getUserByEmail(loginRequest.getEmail()));
        var encryptedPassword = user.get().getEncryptedPassword();
        var logreq = loginRequest.getPassword();

        if (user.isPresent() && user.get().getEncryptedPassword().equals(loginRequest.getPassword())) {
            session.setAttribute("user", user.get());
            HttpHeaders headers = new HttpHeaders();
            headers.add("Set-Cookie", user.get().toString());
            return ResponseEntity.ok().headers(headers).body(user.get());
        } else return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);

    }

    @PostMapping("/signup")
    public ResponseEntity<UserRegistrationResponse> signUpUser(@Valid @RequestBody SignupRequest signupRequest){

        ModelMapper modelMapper = new ModelMapper();
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
        UserDto userDto =  modelMapper.map(signupRequest, UserDto.class);
        userService.registration(userDto);

        UserRegistrationResponse userRegistrationResponse = modelMapper.map(userDto, UserRegistrationResponse.class);


        return new ResponseEntity<>(userRegistrationResponse, HttpStatus.CREATED);
    }




}
