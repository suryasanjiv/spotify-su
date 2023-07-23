package com.su.spotify.client

import com.su.spotify.SpotifyScraperApplication
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.TestPropertySource
import spock.lang.Specification

@TestPropertySource(locations = "classpath:application-test.yaml")
@SpringBootTest(classes = SpotifyScraperApplication.class)
class SpotifyClientSpec extends Specification {

    @Autowired
    private SpotifyClient spotifyClient

    def "GetArist"() {

        when: ""
        def result = spotifyClient.getArtist("0TnOYISbd1XYRBk9myaseg")

        then: ""
        result
    }
}