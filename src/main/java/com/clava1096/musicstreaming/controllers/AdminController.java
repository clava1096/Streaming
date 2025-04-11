package com.clava1096.musicstreaming.controllers;

import com.clava1096.musicstreaming.models.dto.ArtistRequestDTO;
import com.clava1096.musicstreaming.services.AdminService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Tag(name = "admin-controller")
@RequiredArgsConstructor
@RequestMapping("/admin")
public class AdminController {
    private final AdminService adminService;

    @GetMapping("/artist-requests")
    public ResponseEntity<List<ArtistRequestDTO>> getArtistRequests() {
        return ResponseEntity.ok(adminService.getArtistRequests());
    }

    @PostMapping("/approve-artist/{id}")
    public ResponseEntity<Void> approveArtist(
            @PathVariable Long id) {
        adminService.approveArtist(id);
        return ResponseEntity.ok().build();
    }

//    @GetMapping("/artists-to-delete")
//    public ResponseEntity<List<ArtistDTO>> getArtistsToDelete() {
//        return ResponseEntity.ok(adminService.getArtistsToDelete());
//    }
//
//    @DeleteMapping("/artists/{id}")
//    public ResponseEntity<Void> deleteArtist(
//            @PathVariable Long id) {
//        adminService.deleteArtist(id);
//        return ResponseEntity.noContent().build();
//    }
//
//    @DeleteMapping("/tracks/{id}")
//    public ResponseEntity<Void> softDeleteTrack(
//            @PathVariable Long id) {
//        adminService.softDeleteTrack(id);
//        return ResponseEntity.noContent().build();
//    }
}
