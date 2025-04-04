package com.clava1096.musicstreaming.controllers;

import com.clava1096.musicstreaming.models.dto.TrackDTO;
import com.clava1096.musicstreaming.models.dto.TrackSaveDTO;
import com.clava1096.musicstreaming.services.TrackService;
import com.clava1096.musicstreaming.services.s3.S3Service;
import com.clava1096.musicstreaming.utils.ProjectConstants;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.net.URI;
import java.util.UUID;

@Slf4j
@Tag(name = "track-controller")
@RestController
@RequiredArgsConstructor
@RequestMapping("/tracks")
public class TrackController {
    private final TrackService service;

    private final S3Service s3Service;

    @Operation(summary = "Получить аудиозапись по идентификатору")
    @ApiResponse(responseCode = "200", description = "В случае успешного выполнения",
            content = @Content(schema = @Schema(implementation = TrackDTO.class)))
    @GetMapping("/{id}")
    public ResponseEntity<?> getTrackById(@PathVariable("id") UUID trackId) {
        log.info("Request to display a album with ID {}", trackId);
        return new ResponseEntity<>(service.getTrackById(trackId), HttpStatus.OK);
    }


    @Operation(summary = "Загрузка метаданных трека")
    @ApiResponse(responseCode = "201", description = "В случае успешного выполнения возвращает ссылку для загрузки трека",
            content = @Content(schema = @Schema(implementation = TrackDTO.class)))
    @PostMapping("/track")
    public ResponseEntity<?> createTrackMetadata(
            @Parameter(description = "Метаданные трека",
                    required = true, schema = @Schema(implementation = TrackSaveDTO.class))
            @Valid @RequestBody TrackSaveDTO trackSaveDTO) {
        TrackDTO track = service.createTrackMetadata(trackSaveDTO);
        return ResponseEntity
                .created(URI.create("/api/tracks/" + track.getId()))
                .body(track);
    }

    @PostMapping("/{trackId}/upload")
    public ResponseEntity<?> uploadTrackFile(
            @PathVariable UUID trackId,
            @RequestParam("file") MultipartFile file
    ) throws Exception {
        s3Service.uploadTrack(trackId, ProjectConstants.DEFAULT_BUCKET, file);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/{id}/stream-url")
    public ResponseEntity<?> getTrackStreamUrl(@PathVariable("id") UUID trackId) throws Exception {
        String url = s3Service.generateTrackUrl(trackId);
        return ResponseEntity.ok(url);
    }


    @Operation(summary = "Получить все аудиозаписи")
    @ApiResponse(responseCode = "200", description = "В случае успешного выполнения",
            content = @Content(schema = @Schema(implementation = TrackDTO.class)))
    @GetMapping("/all-tracks")
    public ResponseEntity<?> getAllTracks() {
        return new ResponseEntity<>(service.getAllTracks(), HttpStatus.OK);
    }

}
