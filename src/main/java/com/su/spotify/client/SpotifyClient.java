package com.su.spotify.client;


import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(value = "spotify", url = "${spotify.client.url}")
public interface SpotifyClient {

    @GetMapping("/v1/artists/{id}")
    ResponseEntity<Object> getArtist(@PathVariable("id") String id);


}
