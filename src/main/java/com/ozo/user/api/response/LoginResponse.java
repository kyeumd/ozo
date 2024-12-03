package com.ozo.user.api.response;

public record LoginResponse(
    String userId,
    String accessToken
) {
}
