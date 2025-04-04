package com.clava1096.musicstreaming.services;


import com.clava1096.musicstreaming.mappers.TrackMapper;
import com.clava1096.musicstreaming.models.Track;
import com.clava1096.musicstreaming.models.dto.TrackDTO;
import com.clava1096.musicstreaming.models.dto.TrackSaveDTO;
import com.clava1096.musicstreaming.models.repositories.AlbumRepository;
import com.clava1096.musicstreaming.models.repositories.GenreRepository;
import com.clava1096.musicstreaming.models.repositories.TrackRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Slf4j
@Service
@RequiredArgsConstructor
public class TrackService {

    private final TrackRepository trackRepository;

    private final AlbumRepository albumRepository;

    private final GenreRepository genreRepository;

    private final TrackMapper trackMapper;

    public TrackDTO createTrackMetadata(TrackSaveDTO trackSaveDTO) {
        Track track = trackMapper.toTrack(trackSaveDTO);
        trackRepository.save(track);
        return trackMapper.toTrackDTO(track);
    }

    public String uploadTrack(TrackSaveDTO trackSaveDTO) {
        return null;
    }

    public TrackDTO getTrackById(UUID id){
        Track track = trackRepository.findById(id).orElse(null);
        return trackMapper.toTrackDTO(track);
    }

    public List<TrackDTO> getAllTracks(){
        List<Track> tracks = trackRepository.findAll();
        return trackMapper.toTrackDTOs(tracks);
    }

}
