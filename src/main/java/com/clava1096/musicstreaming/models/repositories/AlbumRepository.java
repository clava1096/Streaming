package com.clava1096.musicstreaming.models.repositories;

import com.clava1096.musicstreaming.models.Album;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface AlbumRepository extends JpaRepository<Album, Long> {
    Album findByName(String name);

    Album findByArtist(String artist); ///

    Album findByAlbumId(UUID albumId);

    boolean existsByName(String name);

    boolean deleteByName(String name); // нужны права на удаление редактирование треков и/или альбома

}
