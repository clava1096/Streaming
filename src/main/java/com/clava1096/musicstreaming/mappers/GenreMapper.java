package com.clava1096.musicstreaming.mappers;

import com.clava1096.musicstreaming.models.Genre;
import com.clava1096.musicstreaming.models.dto.GenreDTO;
import com.clava1096.musicstreaming.models.dto.GenreSaveDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;

import java.util.List;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface GenreMapper {
    GenreDTO toGenreDTO(Genre genre);

    @Mapping(target = "id", ignore = true)
    Genre toGenre(GenreSaveDTO genreDTO);

    @Mapping(target = "id", ignore = true)

    List<GenreDTO> toGenreDTO(List<Genre> genres);
}
