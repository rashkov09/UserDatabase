package com.example.springdataintro02.controller;

import com.example.springdataintro02.entities.*;
import com.example.springdataintro02.services.PersonalAlbumService;
import com.example.springdataintro02.services.PictureService;
import com.example.springdataintro02.services.TownService;
import com.example.springdataintro02.services.UserService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
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
        Scanner sc = new Scanner(System.in);
        while (true) {
            System.out.println("0. EXIT\n1. Register user\n2. Add town\n3. Login\n4. Search users by domain\n5. Remove inactive user ");
            int action = Integer.parseInt(sc.nextLine());
            if (action == 0){
                break;
            }
            switch (action) {
                case 1 -> registerUser(sc);
                case 2 -> addTown(sc);
                case 3 -> login(sc);
                case 4 -> searchUsersByDomain(sc);
                case 5 -> removeInactiveUsers(sc);
                default -> System.out.println("Wrong input, please try again.");
            }
        }

    }

    private void removeInactiveUsers(Scanner sc) {
        System.out.print("Enter date criteria (dd/MM/yyyy): ");
        try {
            int[] dateData = Arrays.stream(sc.nextLine().split("/")).mapToInt(Integer::parseInt).toArray();
            LocalDateTime criteria = LocalDateTime.of(dateData[2], dateData[1], dateData[0], 0, 0);
            List<User> markedForRemoval = userService.getUsersByLoginDate(criteria);
            markedForRemoval.forEach(userService::deleteUser);
            System.out.printf("Number of users removed: %d\n", markedForRemoval.size());
        }catch (Exception e){
            System.out.println("Incorrect date value!");
        }

    }


    private void searchUsersByDomain(Scanner sc) {
        System.out.print("Please enter domain: ");
        String domain = sc.nextLine();
        List<User> users = userService.getUsersByDomainName(domain);
        if (users.size() > 0) {
            users
                    .stream()
                    .map(user -> String.format("%s %s", user.getUsername(), user.getEmail()))
                    .forEach(System.out::println);
        } else {
            System.out.printf("No users found with email domain %s\n",domain);
        }
    }

    private void addTown(Scanner sc) {
        System.out.print("Town name:");
        String name = sc.nextLine();
        Town town = getIfTownExistsElseAdd(sc,name);
        System.out.println("Town added successfully!");

    }

    private void registerUser(Scanner sc) {
        System.out.print("Enter first name: ");
        String firstName = sc.nextLine();
        System.out.print("Enter last name: ");
        String lastName = sc.nextLine();
        System.out.print("Enter username: ");
        String username = sc.nextLine();
        String password = "";
        while (true) {
            System.out.print("Enter valid password: ");
            password = sc.nextLine();
            if (passwordValidator.isValid(password)) {
                break;
            } else {
                System.out.println(passwordValidator.getMessage());
            }
        }
        String email = "";
        while (true) {
            System.out.print("Enter valid email: ");
            email = sc.nextLine();
            if (emailValidator.isValid(email)) {
                break;
            } else {
                Arrays.stream(emailValidator.getMessage().split(",")).forEach(System.out::println);
            }
        }
        System.out.print("Enter age: ");
        int age = Integer.parseInt(sc.nextLine());
        System.out.print("Born in town: ");
        String bornTownName = sc.nextLine();
        Town bornTown = getIfTownExistsElseAdd(sc, bornTownName);
        System.out.print("Current living in town: ");
        String livingTownName = sc.nextLine();
        Town livingTown = getIfTownExistsElseAdd(sc, livingTownName);
        User user = new User(firstName, lastName, username
                , password, email, LocalDateTime.now(),LocalDateTime.of(1960,1,1,0,0)
                , age, bornTown, livingTown,
                new HashSet<User>(), new HashSet<PersonalAlbum>(),false);
        if(userService.addUser(user)){
            System.out.println("User added successfully");
            userService.getLogin(user.getUsername(),user.getPassword());
        }else {
            System.out.println("Something went wrong, please try again.");
        }

    }

    private void login(Scanner sc) {
        System.out.print("Username: ");
        String username = sc.nextLine();
        System.out.print("Password: ");
        String password = sc.nextLine();
        if (userService.getLogin(username,password)){
            System.out.println("Logged in successfully!");
            while (true){
                System.out.print("0. LOGOUT\n1. Show friends\n2. Add friend\n3. Create album\n4. Open album\n");
                int action = Integer.parseInt(sc.nextLine());
                if (action == 0){
                    break;
                }
            }
        }else {
            System.out.println("Wrong username or password! Please try again.");
        }
    }

    private Town getIfTownExistsElseAdd(Scanner sc, String bornTownName) {
        Town town = townService.getTownByName(bornTownName);
        if (town == null) {
            System.out.print("Enter country: ");
            String country = sc.nextLine();
            Town newTown = new Town(bornTownName, country);
            townService.addTown(newTown);
            return newTown;
        }
        return town;
    }
}
