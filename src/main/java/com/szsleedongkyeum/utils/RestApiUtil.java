package com.szsleedongkyeum.utils;

import static com.szsleedongkyeum.common.Error.ErrorCode.INTERNAL_API_SERVER_ERROR;

import java.time.Duration;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientResponseException;

@Slf4j
@RequiredArgsConstructor
public class RestApiUtil<T> {

    private final WebClient webClient;

    public RestApiUtil(WebClient.Builder webClientBuilder) {
        this.webClient = webClientBuilder
            .build();
    }

    public T fetchData(String endpoint, ParameterizedTypeReference<T> typeReference) {
        return webClient.get()
                        .uri(endpoint)
                        .retrieve()
                        .bodyToMono(typeReference) // 제네릭 타입으로 응답 처리
                        .timeout(Duration.ofSeconds(20))
                        .doOnSuccess(response -> log.info("API 호출 성공: {}", response))
                        .doOnError(e -> {
                            if (e instanceof WebClientResponseException ex) {
                                log.error("API 호출 실패: {} - {}", ex.getStatusCode(), ex.getResponseBodyAsString());
                            } else {
                                log.error("API 호출 실패: {}", e.getMessage());
                            }
                        })
                        .onErrorResume(e -> {
                            throw new RuntimeException(INTERNAL_API_SERVER_ERROR.getMessage(), e);
                        })
                        .block();
    }
}
