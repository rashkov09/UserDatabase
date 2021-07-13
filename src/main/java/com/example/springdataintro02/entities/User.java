package com.example.springdataintro02.entities;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "users")

public class User extends BaseEntity {
    private String firstName;
    private String lastName;
    private String username;
//@Password(minLength = 4,maxLength = 30, containsDigit = true, containsLowercase = true, containsUppercase = true, containsSpecialSymbol = true, message = "Invalid password");
    @ValidPassword
    private String password;
    @ValidEmail
    private String email;
    private LocalDateTime registeredOn;
    private LocalDateTime lastTimeLoggedIn;
    private Integer age;
    private Town bornTown;
    private Town currentlyLivingTown;
    private Set<User> friends;
    private Set<PersonalAlbum> albums;
    private Boolean isDeleted;

    public User() {
    }

    public User(String firstName, String lastName, String username, String password, String email, LocalDateTime registeredOn, LocalDateTime lastTimeLoggedIn, Integer age, Town bornTown, Town currentlyLivingTown, Set<User> friends, Set<PersonalAlbum> albums,Boolean isDeleted) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.username = username;
        this.password = password;
        this.email = email;
        this.registeredOn = registeredOn;
        this.lastTimeLoggedIn = lastTimeLoggedIn;
        this.age = age;
        this.bornTown = bornTown;
        this.currentlyLivingTown = currentlyLivingTown;
        this.friends = friends;
        this.albums = albums;
        this.isDeleted = isDeleted;
    }



    public String getFullName() {
        return this.firstName+" "+this.lastName;
    }

    public void setFullName(String fullName) {
    }

    @Column(name = "first_name")
    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    @Column(name = "last_name")
    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }


    @Column(name = "username")
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Column(name = "password")

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }


    @Column(name = "email")
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Column(name = "registered_on")
    public LocalDateTime getRegisteredOn() {
        return registeredOn;
    }

    public void setRegisteredOn(LocalDateTime registeredOn) {
        this.registeredOn = registeredOn;
    }

    @Column(name = "last_time_logged_in")
    public LocalDateTime getLastTimeLoggedIn() {
        return lastTimeLoggedIn;
    }

    public void setLastTimeLoggedIn(LocalDateTime lastTimeLoggedIn) {
        this.lastTimeLoggedIn = lastTimeLoggedIn;
    }

    @Column(name = "age")
    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    @OneToOne
    public Town getBornTown() {
        return bornTown;
    }

    public void setBornTown(Town bornTown) {
        this.bornTown = bornTown;
    }

    @OneToOne

    public Town getCurrentlyLivingTown() {
        return currentlyLivingTown;
    }

    public void setCurrentlyLivingTown(Town currentlyLivingTown) {
        this.currentlyLivingTown = currentlyLivingTown;
    }

    @ManyToMany

    public Set<User> getFriends() {
        return friends;
    }

    public void setFriends(Set<User> friends) {
        this.friends = friends;
    }

    @OneToMany(mappedBy = "user")

    public Set<PersonalAlbum> getAlbums() {
        return albums;
    }

    public void setAlbums(Set<PersonalAlbum> albums) {
        this.albums = albums;
    }

    @Column(name="is_deleted")
    public Boolean getDeleted() {
        return isDeleted;
    }

    public void setDeleted(Boolean deleted) {
        isDeleted = deleted;
    }
}
