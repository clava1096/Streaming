package com.clava1096.musicstreaming.controllers;

import com.clava1096.musicstreaming.models.dto.AlbumDTO;
import com.clava1096.musicstreaming.models.dto.AlbumSaveDTO;
import com.clava1096.musicstreaming.services.AlbumService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@Tag(name = "album-controller")
@RestController
@RequiredArgsConstructor
@RequestMapping("/albums")
public class AlbumController {
    private final AlbumService service;

    @Operation(summary = "Добавление альбома")
    @ApiResponse(responseCode = "201", description = "В случае успешного выполнения",
    content = @Content(schema = @Schema(implementation = AlbumDTO.class)))
    @PostMapping("/album-upload")
    public ResponseEntity<AlbumDTO> addAlbum(@RequestBody AlbumSaveDTO albumSaveDTO) {
        return new ResponseEntity<>(service.createAlbum(albumSaveDTO), HttpStatus.CREATED);
    }

    @Operation(summary = "Получить альбом по индентификатору")
    @ApiResponse(responseCode = "200", description = "В случае успешного выполнения",
    content = @Content(schema = @Schema(implementation = AlbumDTO.class)))
    @PostMapping("/{id}")
    public ResponseEntity<AlbumDTO> getAlbumById(@PathVariable long id) {
        return new ResponseEntity<>(service.getAlbumById(id), HttpStatus.OK);
    }
}
