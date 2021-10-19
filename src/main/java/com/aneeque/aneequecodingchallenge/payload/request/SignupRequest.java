package com.aneeque.aneequecodingchallenge.payload.request;



import lombok.Data;

import javax.persistence.Column;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
public class SignupRequest {


    @NotNull(message = "first name cannot be empty")
    private String firstname;

    @NotNull(message = "last name cannot be empty")
    private String lastname;

    @NotBlank(message = "email cannot be empty")
    @Size(max = 50)
    @Email(message = "must be email")
    private String email;

    private String gender;

    @Column(name = "date_of_birth")
    private String dateOfBirth;

    @NotBlank
    @Size(min = 6, max = 40)
    private String password;

    @NotNull(message = "password cannot be empty")
    @Size(min = 6, max = 40)
    private String confirmPassword;
}
