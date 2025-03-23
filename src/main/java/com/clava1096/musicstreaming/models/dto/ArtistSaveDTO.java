package com.clava1096.musicstreaming.models.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDate;

@Getter
@AllArgsConstructor
public class ArtistSaveDTO {
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

    @Override
    public String toString() {
        return "ArtistSaveDTO{" +
                "name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", pseudonym='" + pseudonym + '\'' +
                ", birthDate=" + birthDate +
                '}';
    }
}
