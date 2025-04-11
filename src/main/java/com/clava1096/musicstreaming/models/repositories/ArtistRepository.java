package com.clava1096.musicstreaming.models.repositories;

import com.clava1096.musicstreaming.models.Artist;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface ArtistRepository extends JpaRepository<Artist, UUID> {

    @Query("select e from Artist e where e.name like %:name% ")
    Optional<List<Artist>> searchByName(@Param("name") String name);

    @Query("SELECT a FROM Artist a WHERE a.id IN " +
            "(SELECT r.artist.id FROM ArtistRequest r " +
            "WHERE r.type = 'DELETION' AND r.status = 'PENDING')")
    List<Artist> findArtistsWithPendingDeletionRequests();

}
