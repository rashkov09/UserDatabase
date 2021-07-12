package com.example.springdataintro02.repositories;

import com.example.springdataintro02.entities.Town;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TownRepository extends JpaRepository<Town,Long> {
    Town getTownByNameAndCountry(String name, String country);
}
