package com.aneeque.aneequecodingchallenge;

import com.aneeque.aneequecodingchallenge.dto.UserDto;
import com.aneeque.aneequecodingchallenge.service.UserService;
import com.aneeque.aneequecodingchallenge.service.UserServiceImpl;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class  AneequeCodingChallengeApplication {

    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }


    public static void main(String[] args) {
        SpringApplication.run(AneequeCodingChallengeApplication.class, args);


    }

}
