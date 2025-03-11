package com.clava1096.musicstreaming.mappers;

import com.clava1096.musicstreaming.models.Album;
import com.clava1096.musicstreaming.models.dto.AlbumDTO;
import com.clava1096.musicstreaming.models.dto.AlbumSaveDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import org.mapstruct.MappingTarget;

import java.util.List;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface AlbumMapper {

    AlbumDTO ToAlbumDTO(Album album);

    @Mapping(target = "id", ignore = true) //bc we dont want to override id
    Album toAlbum(AlbumSaveDTO albumDTO);

    @Mapping(target = "id", ignore = true) //bc we dont want to override id
    List<AlbumDTO> toAlbumDTOs(List<Album> albums);

    @Mapping(target = "id", ignore = true) //bc we dont want to override id
    void updateAlbum(@MappingTarget Album album, AlbumDTO albumDTO);

}
