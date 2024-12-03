package com.ozo.utils;

import static com.ozo.common.Error.ErrorCode.INTERNAL_API_SERVER_ERROR;

import java.time.Duration;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientResponseException;

@Slf4j
public class RestApiUtil<T> {

    private static final WebClient WEB_CLIENT = WebClient.builder().build();

    private RestApiUtil() {
    }

    public static <T, R> T postData(String endpoint, String apiKey, R requestBody, ParameterizedTypeReference<T> typeReference) {
        return WEB_CLIENT.post()
                         .uri(endpoint)
                         .header("X-API-KEY", apiKey)
                         .bodyValue(requestBody)
                         .retrieve()
                         .bodyToMono(typeReference)
                         .timeout(Duration.ofSeconds(20))
                         .doOnSuccess(response -> log.info("API 호출 성공: {}", response))
                         .doOnError(e -> {
                                 if (e instanceof WebClientResponseException ex) {
                                     log.error("API 호출 실패: {} - {}", ex.getStatusCode(), ex.getResponseBodyAsString());
                                 } else {
                                     log.error("API 호출 실패: {}", e.getMessage());
                                 }
                             }
                         )
                         .onErrorResume(e -> {
                             throw new RuntimeException(INTERNAL_API_SERVER_ERROR.getMessage(), e);
                         })
                         .block();
    }

}
