package com.example.springdataintro02.services;

import com.example.springdataintro02.entities.Town;

public interface TownService {

    void addTown(Town town);

    Town getTownByName(String bornTownName);
}
