package com.szsleedongkyeum.user.model.domain.type;

import static com.szsleedongkyeum.common.Error.ErrorCode.NOT_ALLOWED_USER;

import com.szsleedongkyeum.utils.EncryptionUtil;
import lombok.Getter;

@Getter
public enum AllowedUser {

    DONG_TAK("동탁", "921108-1582816"),
    GWAN_WOO("관우", "681108-1582816"),
    SON_GWUN("손권", "890601-2455116"),
    YOU_BE("유비", "790411-1656116"),
    JO_JO("조조", "810326-2715702"),
    ;

    private final String name;
    private final String decryptRegNo;

    AllowedUser(String name, String decryptRegNo) {
        this.name = name;
        this.decryptRegNo = decryptRegNo;
    }

    public static void isAllowedUser(String name, String regNo) {
        for (AllowedUser user : AllowedUser.values()) {
            String encryptRegNo = EncryptionUtil.encrypt(regNo);
            if (user.name.equals(name) && user.decryptRegNo.equals(encryptRegNo)) {
                return;
            }
        }
        throw new IllegalArgumentException(NOT_ALLOWED_USER.getMessage());
    }
}
