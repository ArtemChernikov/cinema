package ru.cinema.client;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.Nullable;
import org.springframework.web.client.HttpStatusCodeException;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Map;

public class BaseClient {
    protected final RestTemplate rest;

    public BaseClient(RestTemplate rest) {
        this.rest = rest;
    }

    protected ResponseEntity<Object> get(String path, @Nullable Map<String, Object> parameters) {
        return makeAndSendRequest(HttpMethod.GET, path, parameters);
    }

    protected ResponseEntity<Object> get(String path) {
        return makeAndSendRequest(HttpMethod.GET, path, null);
    }

    private <T> ResponseEntity<Object> makeAndSendRequest(HttpMethod method, String path,
                                                          @Nullable Map<String, Object> parameters) {
        HttpEntity<T> requestEntity = new HttpEntity<>(defaultHeaders());

        ResponseEntity<Object> serverResponse;

        try {
            if (parameters != null) {
                serverResponse = rest.exchange(path, method, requestEntity, Object.class, parameters);
            } else {
                serverResponse = rest.exchange(path, method, requestEntity, Object.class);
            }
        } catch (HttpStatusCodeException e) {
            return ResponseEntity.status(e.getStatusCode()).body(e.getResponseBodyAsByteArray());
        }
        return prepareGatewayResponse(serverResponse);
    }

    private HttpHeaders defaultHeaders() {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);
        httpHeaders.setAccept(List.of(MediaType.APPLICATION_JSON));
        return httpHeaders;
    }

    private static ResponseEntity<Object> prepareGatewayResponse(ResponseEntity<Object> response) {
        if (response.getStatusCode().is2xxSuccessful()) {
            return response;
        }

        ResponseEntity.BodyBuilder responseBuilder = ResponseEntity.status(response.getStatusCode());

        if (response.hasBody()) {
            return responseBuilder.body(response.getBody());
        }

        return responseBuilder.build();
    }
}
