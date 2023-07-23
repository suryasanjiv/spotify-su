package com.su.spotify.client;

import feign.RequestInterceptor;
import feign.RequestTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;

@Configuration
public class FeignClientConfiguration {

    @Value("${spotify.oauth.url}")
    private String spotifyOauthUrl;

    @Value("${auth}")
    private String auth;

    @Bean
    public RequestInterceptor requestInterceptor() {
        return this::applyOAuth2Interceptor;
    }

    @SuppressWarnings("unchecked")
    private void applyOAuth2Interceptor(RequestTemplate template) {
        RestTemplate oauth = new RestTemplate();

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        headers.set("Authorization", "Basic " + auth);

        MultiValueMap<String, String> map = new LinkedMultiValueMap<>();
        map.add("grant_type", "client_credentials");

        HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<>(map, headers);

        //TODO add better error handling
        String token = (String) ((HashMap<String, Object>) oauth.postForObject(spotifyOauthUrl, request, Object.class)).get("access_token");
        template.header("Authorization", "Bearer " + token);
    }

}
