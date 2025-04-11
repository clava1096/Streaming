package com.clava1096.musicstreaming.controllers;

import com.clava1096.musicstreaming.models.dto.MediaTypeDTO;
import com.clava1096.musicstreaming.models.dto.MediaTypeSaveDTO;
import com.clava1096.musicstreaming.services.MediaTypeService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@Tag(name = "media-type-controller")
@RequiredArgsConstructor
@RequestMapping("/media-type")
public class MediaTypeController {
    private final MediaTypeService mediaTypeService;

    @Operation(summary = "Добавление медиа-типа")
    @ApiResponse(responseCode = "201", description = "В случае успешного выполнения",
            content = @Content(schema = @Schema(implementation = MediaTypeSaveDTO.class)))
    @PostMapping
    public ResponseEntity<?> createMediaType(
            @Parameter(description = "Добавляет медиа-типа", required = true, schema = @Schema(implementation = MediaTypeDTO.class))
            @Valid @RequestBody MediaTypeSaveDTO mediaTypeSaveDTO) {
        return new ResponseEntity<>(mediaTypeService.createMediaType(mediaTypeSaveDTO), HttpStatus.CREATED);
    }
}
