package com.clava1096.musicstreaming.models.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ArtistPromotionRequestDTO {
    @NotNull(message = "Id пользователя не может быть равен 0")
    private Long userId;

    @Size(max = 500, message = "Additional info must be less than 500 characters")
    private String additionalInfo;
}
