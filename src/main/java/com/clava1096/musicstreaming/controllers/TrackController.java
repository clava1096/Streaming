package com.clava1096.musicstreaming.controllers;

import com.clava1096.musicstreaming.models.dto.TrackDTO;
import com.clava1096.musicstreaming.models.dto.TrackSaveDTO;
import com.clava1096.musicstreaming.services.TrackService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.UUID;

@Slf4j
@Tag(name = "track-controller")
@RestController
@RequiredArgsConstructor
@RequestMapping("/tracks")
public class TrackController {
    private final TrackService service;

    @Operation(summary = "Получить аудиозапись по идентификатору")
    @ApiResponse(responseCode = "200", description = "В случае успешного выполнения",
            content = @Content(schema = @Schema(implementation = TrackDTO.class)))
    @GetMapping("/{id}")
    public ResponseEntity<?> getTrackById(@PathVariable("id") UUID trackId) {
        log.info("Request to display a album with ID {}", trackId);
        return new ResponseEntity<>(service.getTrackById(trackId), HttpStatus.OK);
    }

    @Operation(summary = "Загрузка трека")
    @ApiResponse(responseCode = "200", description = "В случае успешного выполнения",
            content = @Content(schema = @Schema(implementation = TrackDTO.class)))
    @PostMapping("/upload")
    public ResponseEntity<?> uploadTrack(
            @Parameter(description = "Product to add cannot null or empty.", required = true, schema = @Schema(implementation = TrackSaveDTO.class))
            @RequestParam("file") MultipartFile file,
            @RequestBody TrackSaveDTO trackSaveDTO) {
        log.info("Request to display a album with name of file {}", file.getOriginalFilename());
        return new ResponseEntity<>(service.createTrack(trackSaveDTO),HttpStatus.OK);
    }
}
