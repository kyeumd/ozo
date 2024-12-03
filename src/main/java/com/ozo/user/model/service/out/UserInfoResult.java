package com.ozo.user.model.service.out;

import com.ozo.user.model.domain.User;

public record UserInfoResult(
    Long userId,
    String userCreatorId,
    String name,
    String regNo
) {

    public static UserInfoResult from(User user) {
        return new UserInfoResult(user.getId(), user.getCreatorUserId(), user.getName(), user.getRegNoDecrypt());
    }
}
