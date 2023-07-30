package com.su.spotify.controller

import com.su.spotify.SpotifyApplication
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.TestPropertySource
import spock.lang.Specification

@TestPropertySource(locations = "classpath:application-test.yaml")
@SpringBootTest(classes = SpotifyApplication.class)
class SpotifyControllerSpec extends Specification {

    @Autowired(required = true)
    private SpotifyController spotifyController

    def "GetName"() {
        when: ""
        def actualResult = spotifyController.getTrack("quevedo", "columbia")

        then: ""
        actualResult
    }

}
