package com.clava1096.musicstreaming.models.repositories;

import com.clava1096.musicstreaming.models.Genre;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface GenreRepository extends JpaRepository<Genre, UUID> {
    Genre findByName(String name);

    boolean existsByName(String name);


}
