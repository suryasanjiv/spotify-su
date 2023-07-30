package com.su.spotify.controller;

import com.su.spotify.service.spotify.SpotifySearchService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Log4j2
public class SpotifyController {

    private final SpotifySearchService spotifySearchService;


    @Autowired
    public SpotifyController(SpotifySearchService spotifySearchService) {
        this.spotifySearchService = spotifySearchService;
    }

    @GetMapping("/testing/{artist}/{track}")
    public String getTrack(@PathVariable("artist") String artist, String track) {
        return spotifySearchService.searchTrack(artist, track).toString();
    }

    @PostMapping("/testing/{name}")
    public String createPlaylist(@PathVariable("name") String name) {
        throw new UnsupportedOperationException("Testing");
    }

}
