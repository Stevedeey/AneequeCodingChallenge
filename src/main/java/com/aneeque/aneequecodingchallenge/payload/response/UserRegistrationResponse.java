package com.aneeque.aneequecodingchallenge.payload.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class UserRegistrationResponse {
    private String firstname;

    private  String lastname;

    private String email;

    private String userID;

    private String gender;

    private String dateOfBirth;

}
