package com.clava1096.musicstreaming.models.repositories;

import com.clava1096.musicstreaming.models.Genre;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GenreRepository extends JpaRepository<Genre, Integer> {
    Genre findByName(String name);

    boolean existsByName(String name);


}
