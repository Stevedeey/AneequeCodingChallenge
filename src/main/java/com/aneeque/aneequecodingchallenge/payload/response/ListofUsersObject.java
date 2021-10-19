package com.aneeque.aneequecodingchallenge.payload.response;

import com.aneeque.aneequecodingchallenge.dto.UserDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.stereotype.Component;

import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Component
public class ListofUsersObject {

    private Set<UserDto> userList;
}
