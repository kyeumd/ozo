package com.szsleedongkyeum.user.model.domain;

import com.szsleedongkyeum.utils.EncryptionUtil;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.time.LocalDateTime;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;

@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "USERS")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @Column(name = "creator_user_id")
    private String creatorUserId;
    @Column(name = "password")
    private String password;
    @Column(name = "name")
    private String name;
    @Column(name = "reg_no")
    private String regNo;
    @CreatedBy
    @Column(name = "created_by", nullable = false, updatable = false)
    private String createdBy;
    @CreatedDate
    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;
    @LastModifiedBy
    @Column(name = "updated_by", nullable = false)
    private String updatedBy;
    @LastModifiedDate
    @Column(name = "updated_at", nullable = false)
    private LocalDateTime updatedAt;

    private User(Long id, String userId, String password, String name, String regNo) {
        this.id = id;
        this.creatorUserId = userId;
        this.password = password;
        this.name = name;
        this.regNo = regNo;
        this.createdBy = "system";
        this.updatedBy = "system";
        this.createdAt = LocalDateTime.now();
        this.updatedAt = LocalDateTime.now();
    }

    public static User create(String userId, String password, String name, String regNo) {
        String encryptRegNo = EncryptionUtil.encrypt(regNo);
        return new User(null, userId, password, name, encryptRegNo);
    }

    public String getRegNoDecrypt() {
        return EncryptionUtil.decrypt(regNo);
    }
}
