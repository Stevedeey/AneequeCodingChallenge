package com.aneeque.aneequecodingchallenge.service;

import com.aneeque.aneequecodingchallenge.dto.UserDto;
import com.aneeque.aneequecodingchallenge.exception.ApiBadRequestException;
import com.aneeque.aneequecodingchallenge.model.User;

import com.aneeque.aneequecodingchallenge.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;

import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
@Slf4j
public class UserServiceImpl implements UserService{


    private UserRepository userRepository;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDto registration(UserDto userDto) {

        if(userRepository.existsByEmail(userDto.getEmail())){
            throw new ApiBadRequestException("There exist a user this mail!!");
        }
        if(!userDto.getPassword().equals(userDto.getConfirmPassword())){
            throw new ApiBadRequestException("Your password does not match!!!");
        }

        if (!isValidEmail(userDto.getEmail())){
            throw new ApiBadRequestException("Error: invalid email");
        }

        if(!isValidPassword(userDto.getPassword())){
            throw new ApiBadRequestException("Error: Password not in the right format e.g @#aaAA123");
        }

        userDto.setUserID(UUID.randomUUID().toString());
        ModelMapper modelMapper = new ModelMapper();
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);


        User user = modelMapper.map(userDto, User.class);
        user.setEncryptedPassword(bCryptPasswordEncoder.encode(userDto.getPassword()));
        userRepository.save(user);

        UserDto returnObj = modelMapper.map(user, UserDto.class);

        return  returnObj;
    }

    private boolean isValidPassword(String password) {
        String regex = "^(([0-9]|[a-z]|[A-Z]|[@])*){8,20}$";

        // Compile the ReGex
        Pattern p = Pattern.compile(regex);
        if (password == null) {
            throw new ApiBadRequestException("Error: Password cannot be null");
        }
        Matcher m = p.matcher(password);
        return m.matches();
    }

    private boolean isValidEmail(String email) {
        String regex = "^(.+)@(\\w+)\\.(\\w+)$";

        // Compile the ReGex
        Pattern p = Pattern.compile(regex);
        if (email == null) {
            throw new ApiBadRequestException("Error: Email cannot be null");
        }
        Matcher m = p.matcher(email);
        return m.matches();
    }
}
