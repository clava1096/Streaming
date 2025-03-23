package com.clava1096.musicstreaming.controllers;

import com.clava1096.musicstreaming.models.dto.ArtistDTO;
import com.clava1096.musicstreaming.models.dto.ArtistSaveDTO;
import com.clava1096.musicstreaming.services.ArtistService;
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
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@Slf4j
@RestController
@Tag(name = "artist-controller")
@RequiredArgsConstructor
@RequestMapping("/artists")
public class ArtistController {
    private final ArtistService artistService;

    @Operation(summary = "Добавление артиста")
    @ApiResponse(responseCode = "201", description = "В случае успешного выполнения",
    content = @Content(schema = @Schema(implementation = ArtistDTO.class)))
    @PostMapping("/artist-create")
    public ResponseEntity<?> createArtist(
            @Parameter(description = "Product to add cannot null or empty.", required = true, schema = @Schema(implementation = ArtistSaveDTO.class))
            @Valid
            @RequestBody ArtistSaveDTO artistSaveDTO) {
        log.info(artistSaveDTO.toString());
        return new ResponseEntity<>(artistService.createArtist(artistSaveDTO), HttpStatus.CREATED);
    }
}
