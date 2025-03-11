package com.clava1096.musicstreaming.services;


import com.clava1096.musicstreaming.mappers.TrackMapper;
import com.clava1096.musicstreaming.models.Track;
import com.clava1096.musicstreaming.models.dto.TrackDTO;
import com.clava1096.musicstreaming.models.dto.TrackSaveDTO;
import com.clava1096.musicstreaming.models.repositories.AlbumRepository;
import com.clava1096.musicstreaming.models.repositories.GenreRepository;
import com.clava1096.musicstreaming.models.repositories.TrackRepository;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.Value;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.LocalDate;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class TrackService {

    private final TrackRepository trackRepository;

    private final AlbumRepository albumRepository;

    private final GenreRepository genreRepository;

    private final TrackMapper trackMapper;

    public TrackDTO createTrack(TrackSaveDTO trackSaveDTO) {
        Track track = trackMapper.toTrack(trackSaveDTO);
        trackRepository.save(track);
        return trackMapper.toTrackDTO(track);
    }

    public TrackDTO getTrackById(UUID id){
        Track track = trackRepository.findById(id).orElse(null);
        return trackMapper.toTrackDTO(track);
    }

}
