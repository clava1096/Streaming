package com.clava1096.musicstreaming.models.repositories;

import com.clava1096.musicstreaming.models.MediaType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface MediaTypeRepository extends JpaRepository<MediaType, Long> {
    MediaType findByName(String name);

    MediaType findById(UUID id);
}
