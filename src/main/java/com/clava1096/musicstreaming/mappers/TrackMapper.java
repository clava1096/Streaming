package com.clava1096.musicstreaming.mappers;

import com.clava1096.musicstreaming.models.Track;
import com.clava1096.musicstreaming.models.dto.TrackDTO;
import com.clava1096.musicstreaming.models.dto.TrackSaveDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import org.mapstruct.MappingTarget;

import java.util.List;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface TrackMapper {
        TrackDTO toTrackDTO(Track track);

        Track toTrack(TrackSaveDTO trackDTO);

        List<TrackDTO> toTrackDTOs(List<Track> tracks);

        @Mapping(target = "id", ignore = true)
        void updateTrack(Track track, @MappingTarget TrackDTO trackDTO);
}
