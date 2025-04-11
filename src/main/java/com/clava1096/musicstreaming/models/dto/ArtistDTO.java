package com.clava1096.musicstreaming.models.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDate;
import java.util.UUID;

@Schema(description = "Артист")
@Getter
@AllArgsConstructor
@Builder
public class ArtistDTO {

    @Schema(description = "Идентификатор артиста", example = "8e262c04-a090-11e8-98d0-529269fb1459")
    @JsonProperty(value = "id", defaultValue = "8e262c04-a090-11e8-98d0-529269fb1459", required = true)
    private UUID id;

    @Schema(description = "Имя артиста", example = "Luke")
    @JsonProperty(value = "name", defaultValue = "Luke", required = true)
    private String name;

    @Schema(description = "Фамилия артиста", example = "Barrras")
    @JsonProperty(value = "surname", required = true)
    private String surname;

    @Schema(description = "Псевдоним артиста", example = "ABc1")
    @JsonProperty(value = "pseudonym", required = true)
    private String pseudonym;

    @Schema(description = "День рождения артиста", example = "1985-10-07")
    @JsonProperty(value = "birthDate", required = true, defaultValue = "1985-10-07")
    private LocalDate birthDate;
}
