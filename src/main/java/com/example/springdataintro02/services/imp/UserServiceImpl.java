package com.example.springdataintro02.services.imp;

import com.example.springdataintro02.entities.User;
import com.example.springdataintro02.repositories.UserRepository;
import com.example.springdataintro02.services.UserService;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }


    @Override
    public void addUser(User user) {
        userRepository.save(user);
    }
}
