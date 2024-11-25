package com.szsleedongkyeum.taxation.model.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.math.BigDecimal;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "USERS_TAXATION_PENSION_DEDUCTIONS")
public class UsersTaxationPensionDeductions {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @Column(name = "user_id")
    private Long userId;
    @Column(name = "year_month")
    private String yearMonth;
    @Column(name = "amount")
    private BigDecimal amount;

    private UsersTaxationPensionDeductions(Long id, Long userId, String yearMonth, BigDecimal amount) {
        this.id = id;
        this.userId = userId;
        this.yearMonth = yearMonth;
        this.amount = amount;
    }

    public static UsersTaxationPensionDeductions create(Long userId, String yearMonth, BigDecimal amount) {
        return new UsersTaxationPensionDeductions(
            null,
            userId,
            yearMonth,
            amount
        );
    }
}
