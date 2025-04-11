package com.clava1096.musicstreaming.models.dto;

import com.clava1096.musicstreaming.models.enumpack.RequestStatus;
import com.clava1096.musicstreaming.models.enumpack.RequestType;
import lombok.*;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ArtistRequestDTO {
    private Long id;
    private RequestType type;
    private RequestStatus status;
    private Long userId;
    private Long artistId;
    private String reason;
    private String additionalInfo;
    private LocalDateTime createdAt;
}
