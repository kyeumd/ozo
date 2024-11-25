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
@Table(name = "USERS_TAXATION_CREDIT_CARD_DEDUCTIONS")
public class UsersTaxationCreditCardDeductions {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @Column(name = "user_id")
    private Long userId;
    @Column(name = "deduction_year")
    private int deductionYear;
    @Column(name = "deduction_month")
    private int deductionMonth;
    @Column(name = "amount")
    private BigDecimal amount;

    private UsersTaxationCreditCardDeductions(Long id, Long userId, int deductionYear, int deductionMonth, BigDecimal amount) {
        this.id = id;
        this.userId = userId;
        this.deductionYear = deductionYear;
        this.deductionMonth = deductionMonth;
        this.amount = amount;
    }

    public static UsersTaxationCreditCardDeductions create(Long userId, int deductionYear, int deductionMonth, BigDecimal amount) {
        return new UsersTaxationCreditCardDeductions(
            null,
            userId,
            deductionYear,
            deductionMonth,
            amount
        );
    }
}
