package com.aneeque.aneequecodingchallenge.controller;

import com.aneeque.aneequecodingchallenge.payload.response.ListofUsersObject;
import com.aneeque.aneequecodingchallenge.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/user")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private ListofUsersObject listofUsersObject;

    @GetMapping("/get-all-users")
    public ResponseEntity<ListofUsersObject> getAllUsers(){
        var listOfUsers = userService.getAllUsers();
         listofUsersObject.setUserList(listOfUsers);
        return  ResponseEntity.ok(listofUsersObject);
    }
}
