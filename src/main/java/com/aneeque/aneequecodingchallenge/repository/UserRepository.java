package com.aneeque.aneequecodingchallenge.repository;

import com.aneeque.aneequecodingchallenge.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
