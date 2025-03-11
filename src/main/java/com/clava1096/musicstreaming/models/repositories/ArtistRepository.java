package com.clava1096.musicstreaming.models.repositories;

import com.clava1096.musicstreaming.models.Artist;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ArtistRepository extends JpaRepository<Artist, Long> {
    Artist findByName(String name);

    Artist findByArtistId(Long artistId);

    boolean existsByArtistName(String artistName);

    boolean deleteByName(String name);
}
