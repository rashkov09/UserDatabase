package com.example.springdataintro02.controller;

import com.example.springdataintro02.entities.*;
import com.example.springdataintro02.services.PersonalAlbumService;
import com.example.springdataintro02.services.PictureService;
import com.example.springdataintro02.services.TownService;
import com.example.springdataintro02.services.UserService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Scanner;

@Component
public class CommandLineRunnerImpl implements CommandLineRunner {
    private final UserService userService;
    private final TownService townService;
    private final PictureService pictureService;
    private final PersonalAlbumService personalAlbumService;
    private final PasswordConstraintValidator passwordValidator;
    private final EmailConstraintValidator emailValidator;


    public CommandLineRunnerImpl(UserService userService, TownService townService, PictureService pictureService, PersonalAlbumService personalAlbumService, PasswordConstraintValidator validator, EmailConstraintValidator emailValidator) {
        this.userService = userService;
        this.townService = townService;
        this.pictureService = pictureService;
        this.personalAlbumService = personalAlbumService;
        this.passwordValidator = validator;
        this.emailValidator = emailValidator;

    }

    @Override
    public void run(String... args) throws Exception {
        Town town = new Town("Sofia", "Bulgaria");
        townService.addTown(town);
        User user = new User("Info", "Infov", "todor03", "t0Dor@rrr"
                , "--info@abv.bg", LocalDateTime.now(), LocalDateTime.now(), 32, town, town, new HashSet<>(), new HashSet<>());

        if(passwordValidator.isValid(user.getPassword())){
            if (emailValidator.isValid(user.getEmail())) {
                userService.addUser(user);
            } else {
                System.out.println(emailValidator.getMessage());
            }
        } else {
            System.out.println(passwordValidator.getMessage());
        }


    }
}
