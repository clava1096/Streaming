package com.clava1096.musicstreaming.services;

import com.clava1096.musicstreaming.mappers.AlbumMapper;
import com.clava1096.musicstreaming.models.Album;
import com.clava1096.musicstreaming.models.dto.AlbumDTO;
import com.clava1096.musicstreaming.models.dto.AlbumSaveDTO;
import com.clava1096.musicstreaming.models.repositories.AlbumRepository;
import com.clava1096.musicstreaming.models.repositories.TrackRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AlbumService {
    private final AlbumRepository albumRepository;
    private final AlbumMapper albumMapper;

    @Autowired
    private final ArtistService artistService; // найти по псевдониму все альбомы

    @Autowired
    private final TrackRepository trackRepository; // вначале нужно удалить треки, ищем треки по id альбома

    @Transactional
    public AlbumDTO createAlbum(AlbumSaveDTO albumSaveDTO) {
        Album album = albumMapper.toAlbum(albumSaveDTO);
        albumRepository.save(album);
        return albumMapper.ToAlbumDTO(album);
    }


    public AlbumDTO updateAlbum(AlbumDTO albumDTO) {
        Album album = albumRepository.findByAlbumId(albumDTO.getId());
        if (albumMapper.ToAlbumDTO(album).equals(albumDTO)) {
            return albumMapper.ToAlbumDTO(album);
        }
        albumMapper.updateAlbum(album, albumDTO);
        albumRepository.save(album);
        return albumMapper.ToAlbumDTO(album);
    }

    @Transactional(readOnly = true)
    public AlbumDTO getAlbumById(long id) {
        Album album = albumRepository.findById(id).orElse(null);
        return albumMapper.ToAlbumDTO(album);
    }

    @Transactional(readOnly = true)
    public AlbumDTO getAlbumByName(String name) {
        Album album = albumRepository.findByName(name);
        return albumMapper.ToAlbumDTO(album);
    }

    @Transactional(readOnly = true)
    public AlbumDTO deleteAlbum(long id) {
        Album album = albumRepository.findById(id).orElse(null);
        if (album != null) {
            albumRepository.delete(album);
        }
        return albumMapper.ToAlbumDTO(album);
    }

    /// ?? cringe generate by idea
    public List<AlbumDTO> getAllAlbums() {
        return albumRepository.findAll().stream().map(albumMapper::ToAlbumDTO).collect(Collectors.toList());
    }
}
