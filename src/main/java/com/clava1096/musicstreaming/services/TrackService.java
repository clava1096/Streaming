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

import java.io.File;
import java.util.List;
import java.util.UUID;

@Slf4j
@Service
@RequiredArgsConstructor
public class TrackService {

    private final String uploadDir = "/audio/";

    private final TrackRepository trackRepository;

    private final AlbumRepository albumRepository;

    private final GenreRepository genreRepository;

    private final TrackMapper trackMapper;

    public TrackDTO createTrack(TrackSaveDTO trackSaveDTO) {
        String finalUploadDir = uploadDir + trackSaveDTO.getAuthor() + "/"; // album null?
        File uploadPath = new File(finalUploadDir);
        if (!uploadPath.exists()) {
            uploadPath.mkdirs();
        }
        log.info(uploadPath.getAbsolutePath());
        trackSaveDTO.setFile(uploadPath.getAbsolutePath());
        Track track = trackMapper.toTrack(trackSaveDTO);
        trackRepository.save(track);
        return trackMapper.toTrackDTO(track);
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
