package com.clava1096.musicstreaming.services;


import com.clava1096.musicstreaming.mappers.GenreMapper;
import com.clava1096.musicstreaming.models.Genre;
import com.clava1096.musicstreaming.models.dto.GenreDTO;
import com.clava1096.musicstreaming.models.dto.GenreSaveDTO;
import com.clava1096.musicstreaming.models.repositories.GenreRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Slf4j
@Service
@RequiredArgsConstructor
public class GenreService {

    private final GenreRepository genreRepository;

    private final GenreMapper genreMapper;

    public GenreDTO createGenre(GenreSaveDTO genreSaveDTO) {
        Genre genre = genreMapper.toGenre(genreSaveDTO);
        genreRepository.save(genre);
        return genreMapper.toGenreDTO(genre);
    }

    public GenreDTO getGenreById(UUID id) {
        Genre genre = genreRepository.findById(id).orElse(null);
        return genreMapper.toGenreDTO(genre);
    }
}
