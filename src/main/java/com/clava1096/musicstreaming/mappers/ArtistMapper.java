package com.clava1096.musicstreaming.mappers;

import com.clava1096.musicstreaming.models.Artist;
import com.clava1096.musicstreaming.models.dto.ArtistDTO;
import com.clava1096.musicstreaming.models.dto.ArtistSaveDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import org.mapstruct.MappingTarget;

import java.util.List;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface ArtistMapper {
    ArtistDTO toArtistDTO(Artist artist);

    @Mapping(target = "id", ignore = true) //bc we dont want to override id
    @Mapping(target = "albums", ignore = true)
    Artist toArtist(ArtistSaveDTO artist);

    List<ArtistDTO> toArtistDTOs(List<Artist> artists);

    @Mapping(target = "id", ignore = true) //bc we dont want to override id
    @Mapping(target = "albums", ignore = true)
    void updateArtist(@MappingTarget Artist artist, ArtistDTO artistDTO);
}
