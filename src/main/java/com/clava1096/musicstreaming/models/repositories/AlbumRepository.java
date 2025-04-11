package com.clava1096.musicstreaming.models.repositories;

import com.clava1096.musicstreaming.models.Album;
import com.clava1096.musicstreaming.models.Artist;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface AlbumRepository extends JpaRepository<Album, Long> {
    Album findByTitle(String title);

    Album findByArtist(Artist artist);

    Album findById(UUID albumId);

    boolean existsByTitle(String title);

    boolean deleteByTitle(String title);

}
