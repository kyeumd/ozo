package com.ozo.security.domain.dto;

import com.ozo.user.model.domain.User;
import java.io.Serializable;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class UserDto implements Serializable {

    private Long id;
    private String userId;
    private String name;
    private String password;

    private UserDto(Long id, String userId, String name, String password) {
        this.id = id;
        this.userId = userId;
        this.name = name;
        this.password = password;
    }

    public static UserDto from(User user) {
        return new UserDto(
            user.getId(),
            user.getCreatorUserId(),
            user.getName(),
            user.getPassword()
        );
    }
}
