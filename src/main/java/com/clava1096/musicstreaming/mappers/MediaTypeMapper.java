package com.clava1096.musicstreaming.mappers;

import com.clava1096.musicstreaming.models.MediaType;
import com.clava1096.musicstreaming.models.dto.MediaTypeDTO;
import com.clava1096.musicstreaming.models.dto.MediaTypeSaveDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface MediaTypeMapper {

    MediaTypeDTO toMediaType(MediaType mediaType);

    @Mapping(target = "id", ignore = true)
    MediaType toMediaType(MediaTypeSaveDTO mediaTypeDTO);

}
