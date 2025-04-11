package com.clava1096.musicstreaming.controllers;

import com.clava1096.musicstreaming.models.dto.ArtistDTO;
import com.clava1096.musicstreaming.models.dto.ArtistPromotionRequestDTO;
import com.clava1096.musicstreaming.models.dto.ArtistRequestDTO;
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
import org.springframework.web.bind.annotation.*;

import java.util.UUID;


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
    @PostMapping
    public ResponseEntity<?> createArtist(
            @Parameter(description = "Добавляет артиста", required = true, schema = @Schema(implementation = ArtistSaveDTO.class))
            @Valid @RequestBody ArtistSaveDTO artistSaveDTO) {
        log.info(artistSaveDTO.toString());
        return new ResponseEntity<>(artistService.createArtist(artistSaveDTO), HttpStatus.CREATED);
    }

    @Operation(summary = "Добавление артиста")
    @ApiResponse(responseCode = "200", description = "В случае успешного выполнения",
            content = @Content(schema = @Schema(implementation = ArtistDTO.class)))
    @GetMapping("/{id}")
    public ResponseEntity<?> getArtistById(
            @Parameter(description = "Возвращает артиста по id", required = true, schema = @Schema(implementation = ArtistSaveDTO.class))
            @PathVariable UUID id
    ){
        return new ResponseEntity<>(artistService.getArtistById(id), HttpStatus.OK);
    }

    @Operation(summary = "Получить список артистов по имени")
    @ApiResponse(responseCode = "200", description = "В случае успешного выполнения",
            content = @Content(schema = @Schema(allOf = ArtistDTO.class)))
    @GetMapping("/artists-by-name/{name}")
    public ResponseEntity<?> getArtistByName(@PathVariable("name") String artistName) {
        return new ResponseEntity<>(artistService.getArtistByName(artistName), HttpStatus.OK);
    }

    @Operation(summary = "Создать запрос на повышение до артиста")
    @ApiResponse(responseCode = "201", description = "Запрос успешно создан")
    @PostMapping("/promote")
    public ResponseEntity<?> createPromotionRequest(
            @RequestBody @Valid ArtistPromotionRequestDTO artistPromotionRequestDTO
    ) {
        ArtistRequestDTO createdRequest = artistService.createPromotionRequest(artistPromotionRequestDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdRequest);
    }

}
