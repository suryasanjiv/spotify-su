package com.su.spotify.client;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import feign.RequestInterceptor;
import feign.RequestTemplate;
import lombok.Builder;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.util.Optional;

//TODO do we need this class?
@Configuration
public class FeignClientConfiguration {

    @SuppressWarnings("unused")
    @Value("${spotify.oauth.url}")
    private String spotifyOauthUrl;

    @SuppressWarnings("unused")
    @Value("${auth}")
    private String auth;

    @SuppressWarnings("unused")
    @Bean
    public RequestInterceptor requestInterceptor() {
        return this::applyOAuth2Interceptor;
    }

    private void applyOAuth2Interceptor(RequestTemplate template) {
        RestTemplate oauth = new RestTemplate();

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        headers.set("Authorization", "Basic " + auth);

        MultiValueMap<String, String> map = new LinkedMultiValueMap<>();
        map.add("grant_type", "client_credentials");

        HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<>(map, headers);

        String token = Optional.ofNullable(oauth.postForObject(spotifyOauthUrl, request, OAuthResponseDto.class))
                .map(OAuthResponseDto::getAccessToken)
                .orElseThrow(() -> new IllegalStateException("OAuth2 token has not been successfully acquired."));
        template.header("Authorization", "Bearer " + token);
    }

    @Getter
    @Builder(toBuilder = true)
    @JsonDeserialize(builder = OAuthResponseDto.OAuthResponseDtoBuilder.class)
    public static class OAuthResponseDto {

        @JsonProperty("access_token")
        private String accessToken;
        @JsonProperty("token_type")
        private String tokenType;
        @JsonProperty("expires_in")
        private String expiresIn;

    }

}
