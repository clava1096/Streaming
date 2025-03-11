package com.clava1096.musicstreaming.models.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDate;

@Getter
@AllArgsConstructor
public class MediaTypeSaveDTO {
    @Schema(description = "Название формата записи", example = "mp4")
    @JsonProperty(value = "name", defaultValue = "mp4", required = true)
    private String name;

    @Schema(description = "Дата создания формата записи", example = "2019-08-06")
    @JsonProperty(value = "createdAt", defaultValue = "2019-08-06", required = true)
    private LocalDate createdAt;
}
