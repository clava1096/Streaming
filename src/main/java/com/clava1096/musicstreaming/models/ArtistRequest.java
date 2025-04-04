package com.clava1096.musicstreaming.models;

import com.clava1096.musicstreaming.models.enumpack.RequestStatus;
import com.clava1096.musicstreaming.models.enumpack.RequestType;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;


@Entity
@Table(name = "artist_requests")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ArtistRequest {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "artist_id")
    private Artist artist;  // Заполняется, если запрос связан с артистом (для удаления)

    @Enumerated(EnumType.STRING)
    private RequestType type;  // PROMOTION или DELETION

    @Enumerated(EnumType.STRING)
    private RequestStatus status;  // PENDING, APPROVED, REJECTED

    private String reason;  // Причина (для DELETION)
    private String additionalInfo;  // Доп. данные (для PROMOTION)

    @CreationTimestamp
    private LocalDateTime createdAt;
}
