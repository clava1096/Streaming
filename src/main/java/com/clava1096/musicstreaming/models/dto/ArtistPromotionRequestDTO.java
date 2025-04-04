package com.clava1096.musicstreaming.models.dto;

import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.util.UUID;

@Data
public class ArtistPromotionRequestDTO {
    @NotNull
    private UUID userId;
    private String additionalInfo;  // Портфолио, ссылки и т.д.
}
