package com.clava1096.musicstreaming.controllers;

import com.clava1096.musicstreaming.models.dto.GenreDTO;
import com.clava1096.musicstreaming.models.dto.GenreSaveDTO;
import com.clava1096.musicstreaming.models.dto.TrackDTO;
import com.clava1096.musicstreaming.services.GenreService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@Tag(name = "genre-controller")
@RestController
@RequiredArgsConstructor
@RequestMapping("/genre")
public class GenreController {

    private final GenreService service;

    @Operation(summary = "Создать жанр")
    @ApiResponse(responseCode = "201", description = "В случае успешного выполнения",
    content = @Content(schema = @Schema(implementation = GenreDTO.class)))
    @PostMapping("/genre-upload")
    public ResponseEntity<?> createGenre(@Parameter(description = "Product to add cannot null or empty.",
                                             required = true, schema = @Schema(implementation = GenreSaveDTO.class))
                                         @RequestBody GenreSaveDTO genreSaveDTO) {
        return new ResponseEntity<>(service.createGenre(genreSaveDTO),HttpStatus.CREATED);
    }

    @Operation(summary = "Получить жанр по идентификатору")
    @ApiResponse(responseCode = "200", description = "В случае успешного выполнения",
            content = @Content(schema = @Schema(implementation = TrackDTO.class)))
    @GetMapping("/{id}")
    public ResponseEntity<?> findGenreById(@PathVariable("id") UUID genreId) {
        return new ResponseEntity<>(service.getGenreById(genreId), HttpStatus.OK);
    }

}
