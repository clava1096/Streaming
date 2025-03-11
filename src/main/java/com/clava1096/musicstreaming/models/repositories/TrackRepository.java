package com.clava1096.musicstreaming.models.repositories;

import com.clava1096.musicstreaming.models.Track;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface TrackRepository extends JpaRepository<Track, UUID> {

    Track findByName(String name);

    boolean existsByName(String name);

}
