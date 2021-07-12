package com.example.springdataintro02.repositories;

import com.example.springdataintro02.entities.PersonalAlbum;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PersonalAlbumRepository extends JpaRepository<PersonalAlbum,Long> {
}
