package com.clava1096.musicstreaming.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import java.util.List;
import java.util.UUID;

@Entity
@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(exclude = "albums")
@Table(name = "ARTISTS")
public class Artist {

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    @Column(name = "artist_id")
    private UUID id;


    private String genre;

    private String name;

    private String description;

    @JsonIgnore
    @OneToMany(mappedBy = "artist", fetch = FetchType.LAZY)
    private List<Album> albums;
}
