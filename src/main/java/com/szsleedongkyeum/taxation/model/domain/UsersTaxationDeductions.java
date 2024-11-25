package com.szsleedongkyeum.taxation.model.domain;

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
@Table(name = "USERS_TAXATION_DEDUCTIONS")
public class UsersTaxationDeductions {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @Column(name = "user_id")
    private Long userId;
    @Column(name = "deduction_type")
    private String deductionType;

    private UsersTaxationDeductions(Long id, Long userId, String deductionType) {
        this.id = id;
        this.userId = userId;
        this.deductionType = deductionType;
    }

    public static UsersTaxationDeductions create(Long userId, String deductionType) {
        return new UsersTaxationDeductions(
            null,
            userId,
            deductionType
        );
    }
}
