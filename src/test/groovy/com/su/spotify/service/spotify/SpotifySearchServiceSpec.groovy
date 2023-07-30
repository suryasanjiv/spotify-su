package com.su.spotify.service.spotify

import com.su.spotify.client.spotify.SpotifyClient
import spock.lang.Specification

class SpotifySearchServiceSpec extends Specification {
    def "search track"() {
        given: ""
        SpotifyClient spotifyClient = Mock(SpotifyClient) {
            it.search("artist:quevedo track:columbia", "track") >> ""
        }
        SpotifySearchService searchService = new SpotifySearchService(spotifyClient)

        when: ""
        searchService.searchTrack("quevedo", "columbia")

        then: ""
        1 * spotifyClient.search("artist:quevedo track:columbia", "track")
    }
}
