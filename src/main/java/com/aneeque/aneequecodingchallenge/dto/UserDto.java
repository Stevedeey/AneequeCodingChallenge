package com.aneeque.aneequecodingchallenge.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.Column;
import java.io.Serializable;


@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class UserDto implements Serializable {

    private String firstname;

    private  String lastname;

    private String email;

    private String password;

    private String confirmPassword;

    private String userID;

    private String gender;

    private String dateOfBirth;


}
