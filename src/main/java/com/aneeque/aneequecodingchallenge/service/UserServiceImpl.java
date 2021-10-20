package com.aneeque.aneequecodingchallenge.service;

import com.aneeque.aneequecodingchallenge.dto.UserDto;
import com.aneeque.aneequecodingchallenge.exception.ApiBadRequestException;
import com.aneeque.aneequecodingchallenge.model.User;

import com.aneeque.aneequecodingchallenge.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;

import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

@Service
@Slf4j
@CacheConfig(cacheNames = "userCache")
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

    @Autowired
    private ModelMapper modelMapper;

    @CacheEvict(cacheNames = "users", allEntries = true)
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

    @Cacheable(cacheNames = "users")
    @Override
    public Set<UserDto> getAllUsers() {
        List<User> userList = userRepository.findAll();

        Set<UserDto> userDtoSet = userList.stream().map(user -> {
            UserDto userDto = modelMapper.map(user, UserDto.class);
            return userDto;
        }).collect(Collectors.toSet());

        return userDtoSet;
    }

    @Cacheable(cacheNames = "users", key = "#email", unless = "#result == null")
    @Override
    public User getUserByEmail(String email) {
        return userRepository.findUserByEmail(email);
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
