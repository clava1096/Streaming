package com.clava1096.musicstreaming.models.dto;

import com.clava1096.musicstreaming.models.enumpack.RequestStatus;
import com.clava1096.musicstreaming.models.enumpack.RequestType;
import lombok.*;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ArtistRequestDTO {
    private Long id;
    private RequestType type;
    private RequestStatus status;
    private Long userId;
    private UUID artistId;  // Для DELETION
    private String reason;  // Для DELETION
    private String additionalInfo;  // Для PROMOTION
    private LocalDateTime createdAt;
}
