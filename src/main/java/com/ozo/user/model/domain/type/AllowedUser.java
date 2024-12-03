package com.ozo.user.model.domain.type;

import static com.ozo.common.Error.ErrorCode.NOT_ALLOWED_USER;

import com.ozo.utils.EncryptionUtil;
import lombok.Getter;

@Getter
public enum AllowedUser {

    DONG_TAK("동탁", "zErsYCDjfQ1kf959khrR4A=="),
    GWAN_WOO("관우", "3uW9s4CLyiudcmdnanpwig=="),
    SON_GWUN("손권", "SFB7tG6ZCFVhejjkoJFXYw=="),
    YOU_BE("유비", "/JNkPbfJkV639DCfhPK1Vg=="),
    JO_JO("조조", "iUePgbmF0nRy6jepOSU/TA=="),
    ;

    private final String name;
    private final String encryptRegNo;

    AllowedUser(String name, String decryptRegNo) {
        this.name = name;
        this.encryptRegNo = decryptRegNo;
    }

    public static void isAllowedUser(String name, String regNo) {
        for (AllowedUser user : AllowedUser.values()) {
            String encryptRegNo = EncryptionUtil.encrypt(regNo);
            if (user.name.equals(name) && user.encryptRegNo.equals(encryptRegNo)) {
                return;
            }
        }
        throw new IllegalArgumentException(NOT_ALLOWED_USER.getMessage());
    }

    public String getDecryptRegNo() {
        return EncryptionUtil.decrypt(encryptRegNo);
    }
}
