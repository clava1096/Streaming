package com.clava1096.musicstreaming.models;

import com.clava1096.musicstreaming.models.enumpack.GenreDirection;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.util.UUID;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "genre")
public class Genre {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "genre_id", nullable = false)
    private UUID id;

    @Column(name = "name", nullable = false, unique = true)
    private String name;

    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    @Column(name = "created_at", nullable = false)
    @CreatedDate
    private LocalDate createdAt;

    @Column(name = "genre_direction", nullable = false)
    @Enumerated(EnumType.STRING)
    private GenreDirection genreDirection;
}
