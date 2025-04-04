package com.clava1096.musicstreaming.models.dto;

import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.util.UUID;

@Data
public class ArtistDeletionRequestDTO {
    @NotNull
    private UUID artistId;
    private String reason;  // Почему хочет удалиться
}
