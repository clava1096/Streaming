package com.clava1096.musicstreaming.services;

import com.clava1096.musicstreaming.mappers.MediaTypeMapper;
import com.clava1096.musicstreaming.models.MediaType;
import com.clava1096.musicstreaming.models.dto.MediaTypeDTO;
import com.clava1096.musicstreaming.models.dto.MediaTypeSaveDTO;
import com.clava1096.musicstreaming.models.repositories.MediaTypeRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
@Slf4j
public class MediaTypeService {

    private final MediaTypeRepository mediaTypeRepository;

    private final MediaTypeMapper mediaTypeMapper;

    public MediaTypeDTO createMediaType(MediaTypeSaveDTO mediaTypeSaveDTO) {
        MediaType mediaType = mediaTypeMapper.toMediaType(mediaTypeSaveDTO);
        mediaTypeRepository.save(mediaType);
        return mediaTypeMapper.toMediaType(mediaType);
    }

    public MediaTypeDTO getMediaType(UUID id) {
        MediaType mediaType = mediaTypeRepository.findById(id);
        return mediaTypeMapper.toMediaType(mediaType);
    }
}
