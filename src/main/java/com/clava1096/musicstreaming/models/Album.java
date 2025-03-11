package com.clava1096.musicstreaming.models;

import com.clava1096.musicstreaming.models.enumpack.AlbumType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.ZonedDateTime;
import java.util.UUID;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Album {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "album_id", nullable = false)
    private UUID id;

    @Column(name = "title", nullable = false)
    private String title;

    @Column(name = "album_type", nullable = false)
    @Enumerated(EnumType.STRING)
    private AlbumType albumType;

    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    @Column(name = "created_at", nullable = false)
    @CreatedDate
    private ZonedDateTime createdAt;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(
            name = "artist_id",
            referencedColumnName = "artist_id",
            foreignKey = @ForeignKey(name = "fk_album_artist_id"),
            nullable = false
    )
    private Artist artist;
}
