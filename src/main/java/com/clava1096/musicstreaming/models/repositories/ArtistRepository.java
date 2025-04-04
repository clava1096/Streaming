package com.clava1096.musicstreaming.models.repositories;

import com.clava1096.musicstreaming.models.Artist;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface ArtistRepository extends JpaRepository<Artist, UUID> {

    @Query("select e from Artist e where e.name like %:name% ")
    Optional<List<Artist>> searchByName(@Param("name") String name);
}
