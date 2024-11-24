package com.szsleedongkyeum.security.domain.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class UserResponse {

    private Long id;
    private String userId;
    private String userName;

    private UserResponse(Long id, String userId, String userName) {
        this.id = id;
        this.userId = userId;
        this.userName = userName;
    }

    public static UserResponse from(UserDto userDto) {
        return new UserResponse(
            userDto.getId(),
            userDto.getUserId(),
            userDto.getName()
        );
    }
}
