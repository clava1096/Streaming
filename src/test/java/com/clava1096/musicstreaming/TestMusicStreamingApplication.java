package com.clava1096.musicstreaming;

import org.springframework.boot.SpringApplication;

public class TestMusicStreamingApplication {

    public static void main(String[] args) {
        SpringApplication.from(MusicStreamingApplication::main).with(TestcontainersConfiguration.class).run(args);
    }

}
