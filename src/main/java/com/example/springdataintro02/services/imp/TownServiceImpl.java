package com.example.springdataintro02.services.imp;

import com.example.springdataintro02.entities.Town;
import com.example.springdataintro02.repositories.TownRepository;
import com.example.springdataintro02.services.TownService;
import org.springframework.stereotype.Service;

@Service
public class TownServiceImpl implements TownService {
    private final TownRepository townRepository;

    public TownServiceImpl(TownRepository townRepository) {
        this.townRepository = townRepository;
    }

    @Override
    public void addTown(Town town) {
        townRepository.save(town);
    }
}
