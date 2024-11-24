package com.szsleedongkyeum.user.model.domain;

import com.szsleedongkyeum.utils.EncryptionUtil;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "USERS")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @Column(name = "user_id")
    private String userId;
    @Column(name = "password")
    private String password;
    @Column(name = "name")
    private String name;
    @Column(name = "reg_no")
    private String regNo;

    private User(Long id, String userId, String password, String name, String regNo) {
        this.id = id;
        this.userId = userId;
        this.password = password;
        this.name = name;
        this.regNo = regNo;
    }

    public static User create(String userId, String password, String name, String regNo) {
        String encryptRegNo = EncryptionUtil.encrypt(regNo);
        return new User(null, userId, password, name, encryptRegNo);
    }

    public String getRegNoDecrypt() {
        return EncryptionUtil.decrypt(regNo);
    }
}
