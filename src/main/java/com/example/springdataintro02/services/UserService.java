package com.example.springdataintro02.services;

import com.example.springdataintro02.entities.User;

import java.time.LocalDateTime;
import java.util.List;

public interface UserService {
    boolean addUser(User user);

    boolean getLogin(String username, String password);


    List<User> getUsersByDomainName(String domain);

    List<User> getUsersByLoginDate(LocalDateTime criteria);

    void deleteUser(User user);

}
