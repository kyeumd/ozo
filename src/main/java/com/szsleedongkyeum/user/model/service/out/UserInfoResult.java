package com.szsleedongkyeum.user.model.service.out;

import com.szsleedongkyeum.user.model.domain.User;

public record UserInfoResult(
    String userId,
    String name,
    String regNo
) {

    public static UserInfoResult from(User user) {
        return new UserInfoResult(user.getCreatorUserId(), user.getName(), user.getRegNoDecrypt());
    }
}
