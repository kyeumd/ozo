package com.szsleedongkyeum.taxation.model.domain;

import com.szsleedongkyeum.support.BaseEntity;
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
@Table(name = "USERS_TAXATION_DEDUCTIONS")
public class UsersTaxationDeductions extends BaseEntity {

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
    @Column(name = "pension_deduction")
    private BigDecimal pensionDeduction;
    @Column(name = "credit_card_deduction")
    private BigDecimal creditCardDeduction;

    private UsersTaxationDeductions(Long id, Long userId, int deductionYear, int deductionMonth, BigDecimal pensionDeduction,
        BigDecimal creditCardDeduction) {
        this.id = id;
        this.userId = userId;
        this.deductionYear = deductionYear;
        this.deductionMonth = deductionMonth;
        this.pensionDeduction = pensionDeduction;
        this.creditCardDeduction = creditCardDeduction;
    }

    public static UsersTaxationDeductions create(Long userId, int deductionYear, int deductionMonth, BigDecimal pensionDeduction,
        BigDecimal creditCardDeduction) {
        return new UsersTaxationDeductions(
            null,
            userId,
            deductionYear,
            deductionMonth,
            pensionDeduction,
            creditCardDeduction
        );
    }

    public void setPensionDeduction(BigDecimal pensionDeduction) {
        this.pensionDeduction = pensionDeduction;
    }

    public void setCreditCardDeduction(BigDecimal creditCardDeduction) {
        this.creditCardDeduction = creditCardDeduction;
    }
}
