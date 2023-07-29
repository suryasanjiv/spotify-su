package com.su.spotify.controller;

import com.su.spotify.client.spotify.SpotifyClient;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Log4j2
public class SpotifyController {

    private final SpotifyClient spotifyClient;


    @Autowired
    public SpotifyController(SpotifyClient spotifyClient) {
        this.spotifyClient = spotifyClient;
    }

    @GetMapping("/testing/{id}")
    public String getArtist(@PathVariable("id") String id) {
        log.info(() -> "testing lazy logging");
        return spotifyClient.getArtist(id).toString();
    }

}
