package com.clava1096.musicstreaming.controllers;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/files")
@RequiredArgsConstructor
@Slf4j
public class TestController {

    @GetMapping("/test2")
    public String test() {
        return "test";
    }

}
