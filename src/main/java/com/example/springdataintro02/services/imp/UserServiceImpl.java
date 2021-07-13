package com.example.springdataintro02.services.imp;

import com.example.springdataintro02.entities.User;
import com.example.springdataintro02.repositories.UserRepository;
import com.example.springdataintro02.services.UserService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }


    @Override
    public boolean addUser(User user) {
        try {
            userRepository.save(user);
            return true;
        }catch (Exception e){
            return false;
        }
    }

    @Override
    public boolean getLogin(String username, String password) {
        User user = userRepository.getUserByUsernameAndPassword(username,password);
        if (user == null){
            return false;
        }
        user.setLastTimeLoggedIn(LocalDateTime.now());
        userRepository.save(user);
        return true;
    }

    @Override
    public List<User> getUsersByDomainName(String domain) {
       return userRepository.getUsersByEmailEndingWith(domain);
    }

}
