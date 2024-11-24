package com.szsleedongkyeum.user.api.response;

public record LoginResponse(
    String userId,
    String token
) {
}
