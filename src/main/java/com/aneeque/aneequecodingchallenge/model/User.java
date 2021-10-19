package com.aneeque.aneequecodingchallenge.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@Entity
@Table(name = "users")
@AllArgsConstructor
@NoArgsConstructor
public class User extends BaseModel{

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;


    @Column(nullable = false, length = 50)
    private String firstname;

    @Column(nullable = false, length = 50)
    private  String lastname;

    @Column(nullable = false, length = 120, unique = true)
    private String email;

    @Column(nullable = false, unique = true)
    private String userID;

    @Column(nullable = false, unique = true)
    private String encryptedPassword;

    private String gender;

    @Column(name = "date_of_birth")
    private String dateOfBirth;

}
