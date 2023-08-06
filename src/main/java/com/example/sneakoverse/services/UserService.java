package com.example.sneakoverse.services;

import com.example.sneakoverse.entity.User;
import com.example.sneakoverse.pojo.UserPojo;

import java.io.IOException;
import java.util.List;
import java.util.Optional;


public interface UserService {
    UserPojo save(UserPojo userPojo) throws IOException;

    List<User> fetchAll();

    Optional<User> fetchById(Integer id);

    void deleteById(Integer id);

    void sendEmail();

    User fatchByEmail(String email);

    void update (UserPojo userPojo) throws IOException;
}
