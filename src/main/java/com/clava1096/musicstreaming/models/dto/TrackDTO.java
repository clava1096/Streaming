package com.clava1096.musicstreaming.models.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.ZonedDateTime;
import java.util.UUID;

@Schema(description = "Аудиозапись")
@Getter
@AllArgsConstructor
public class TrackDTO {
    @Schema(description = "Идентификатор аудиозаписи", example = "8e262c04-a090-11e8-98d0-529269fb1459")
    @JsonProperty(value = "id", defaultValue = "8e262c04-a090-11e8-98d0-529269fb1459", required = true)
    private UUID id;

    @Schema(description = "Название аудиозаписи", example = "First memory type")
    @JsonProperty(value = "name", required = true)
    private String name;

    @Schema(description = "Автор аудиозаписи", example = "ASAP")
    @JsonProperty("author")
    private String author;

    @Schema(description = "Дата создания аудиозаписи", example = "2019-08-06T16:30:00Z")
    @JsonProperty(value = "createdAt", defaultValue = "2019-08-06T16:30:00Z", required = true)
    private ZonedDateTime createdAt;

    @Schema(description = "Длина аудиозаписи в миллисекундах", example = "300000")
    @JsonProperty("milliseconds")
    private Integer milliseconds;

    @Schema(description = "Размер аудиозаписи в байтах", example = "30000")
    @JsonProperty("bytes")
    private Integer bytes;

    @Schema(description = "Альбом")
    @JsonProperty(value = "album", required = true)
    private AlbumDTO album;

    @Schema(description = "Формат аудиозаписи")
    @JsonProperty(value = "mediaType", required = true)
    private MediaTypeDTO mediaType;

    @Schema(description = "Жанр аудиозаписи")
    @JsonProperty(value = "genre", required = true)
    private GenreDTO genre;
}
