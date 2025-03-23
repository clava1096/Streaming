package com.clava1096.musicstreaming.services;

import com.clava1096.musicstreaming.mappers.ArtistMapper;
import com.clava1096.musicstreaming.models.Artist;
import com.clava1096.musicstreaming.models.dto.ArtistDTO;
import com.clava1096.musicstreaming.models.dto.ArtistSaveDTO;
import com.clava1096.musicstreaming.models.repositories.AlbumRepository;
import com.clava1096.musicstreaming.models.repositories.ArtistRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@RequiredArgsConstructor
public class ArtistService {
    private final ArtistRepository artistRepository;

    private final ArtistMapper artistMapper;

    @Autowired
    private final AlbumRepository albumRepository;

    @Transactional
    public ArtistDTO createArtist(ArtistSaveDTO artistSaveDTO) {
        Artist artist = artistMapper.toArtist(artistSaveDTO);
        artistRepository.save(artist);
        return artistMapper.toArtistDTO(artist);
    }

    @Transactional(readOnly = true)
    public ArtistDTO deleteArtistByName(String name) {
        Artist artist = artistRepository.findByName(name);
        artistRepository.delete(artist);
        return artistMapper.toArtistDTO(artist);
    }

}
