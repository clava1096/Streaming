package com.clava1096.musicstreaming.models;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.format.annotation.DateTimeFormat;

import java.math.BigDecimal;
import java.time.ZonedDateTime;
import java.util.UUID;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "track")
public class Track {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "track_id", nullable = false)
    private UUID id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "author")
    private String author;

    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    @Column(name = "created_at", nullable = false)
    @CreatedDate
    private ZonedDateTime createdAt;

    @Column(name = "milliseconds")
    private Integer milliseconds;

    @Column(name = "bytes")
    private Integer bytes;

    // ключ к файлу на сервере s3
    @Column(name = "file_path")
    private String filePath;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(
            name = "album_id",
            referencedColumnName = "album_id",
            foreignKey = @ForeignKey(name = "fk_track_album_id"),
            nullable = false
    )
    private Album album;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(
            name = "genre_id",
            referencedColumnName = "genre_id",
            foreignKey = @ForeignKey(name = "fk_track_genre_id"),
            nullable = false
    )
    private Genre genre;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(
            name = "media_type_id",
            referencedColumnName = "media_type_id",
            foreignKey = @ForeignKey(name = "fk_track_media_type_id"),
            nullable = false
    )
    private MediaType mediaType;

}
