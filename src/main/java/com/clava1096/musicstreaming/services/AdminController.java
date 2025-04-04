package com.clava1096.musicstreaming.services;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/admin/artists")
@RequiredArgsConstructor
public class AdminController {
    private final ArtistService artistService;

}
