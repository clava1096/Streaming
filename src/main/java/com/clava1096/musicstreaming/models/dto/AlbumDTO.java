package com.clava1096.musicstreaming.models.dto;

import com.clava1096.musicstreaming.models.enumpack.AlbumType;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.ZonedDateTime;
import java.util.UUID;

@Schema(description = "Альбом")
@Getter
@AllArgsConstructor
public class AlbumDTO {
    @Schema(description = "Идентификатор альбома", example = "8e262c04-a090-11e8-98d0-529269fb1459")
    @JsonProperty(value = "id", defaultValue = "8e262c04-a090-11e8-98d0-529269fb1459", required = true)
    private UUID id;

    @Schema(description = "Заголовок альбома", example = "The first album")
    @JsonProperty(value = "title", defaultValue = "The first album", required = true)
    private String title;

    @Schema(description = "Тип альбома", example = "SINGLE")
    @JsonProperty(value = "albumType", defaultValue = "SINGLE", required = true)
    private AlbumType albumType;

    @Schema(description = "Дата создания альбома", example = "2019-08-06T16:30:00Z")
    @JsonProperty(value = "createdAt", defaultValue = "2019-08-06T16:30:00Z", required = true)
    private ZonedDateTime createdAt;

    @Schema(description = "Артист")
    @JsonProperty(value = "artist", required = true)
    private ArtistDTO artist;
}
