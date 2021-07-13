package com.example.springdataintro02.repositories;

import com.example.springdataintro02.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    User getUserByUsernameAndPassword(String username, String password);
    List<User> getUsersByEmailEndingWith(String domain);
    List<User> getUserByLastTimeLoggedInBefore(LocalDateTime localDateTime);
//    List<User> getUserByIsDeletedEquals(Boolean criteria);

}
