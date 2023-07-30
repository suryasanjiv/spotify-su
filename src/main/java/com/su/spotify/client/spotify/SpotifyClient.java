package com.su.spotify.client.spotify;


import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(value = "spotify", url = "${spotify.client.url}")
public interface SpotifyClient {

    @GetMapping("/v1/artists/{id}")
    ResponseEntity<Object> getArtist(@PathVariable("id") String id);

    @GetMapping("/v1/search")
    ResponseEntity<Object> search(@RequestParam("q") String query, @RequestParam("type") String type);


}
