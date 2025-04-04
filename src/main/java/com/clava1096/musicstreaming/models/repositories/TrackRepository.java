package com.clava1096.musicstreaming.models.repositories;

import com.clava1096.musicstreaming.models.Track;
import org.jetbrains.annotations.NotNull;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;
import java.util.UUID;

public interface TrackRepository extends JpaRepository<Track, UUID> {

    Track findByName(String name);

    boolean existsByName(String name);
}
