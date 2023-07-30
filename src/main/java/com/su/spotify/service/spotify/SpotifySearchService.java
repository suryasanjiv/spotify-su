package com.su.spotify.service.spotify;

import com.su.spotify.client.spotify.SpotifyClient;
import org.springframework.stereotype.Service;

@Service
public class SpotifySearchService {

    private final SpotifyClient spotifyClient;

    public SpotifySearchService(SpotifyClient spotifyClient) {
        this.spotifyClient = spotifyClient;
    }

    public Object searchTrack(String artist, String track) {
        return spotifyClient.search(String.format("artist:%s track:%s", artist, track), "track");
    }
}
