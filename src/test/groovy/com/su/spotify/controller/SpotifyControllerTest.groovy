package com.su.spotify.controller

import com.su.spotify.SpotifyApplication
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.TestPropertySource
import spock.lang.Specification

@TestPropertySource(locations = "classpath:application-test.yaml")
@SpringBootTest(classes = SpotifyApplication.class)
class SpotifyControllerTest extends Specification {

    @Autowired(required = true)
    private SpotifyController spotifyController

    def "GetName"() {
        when: ""
        def actualResult = spotifyController.getArtist("0TnOYISbd1XYRBk9myaseg")

        then: ""
        actualResult
    }

}
