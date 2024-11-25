package com.szsleedongkyeum.taxation.model.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.math.BigDecimal;
import java.time.LocalDate;
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
    @Column(name = "deduction_id")
    private Long deductionId;
    @Column(name = "month")
    private LocalDate month;
    @Column(name = "amount")
    private BigDecimal amount;

    private UsersTaxationPensionDeductions(Long id, Long deductionId, LocalDate month, BigDecimal amount) {
        this.id = id;
        this.deductionId = deductionId;
        this.month = month;
        this.amount = amount;
    }

    public static UsersTaxationPensionDeductions create(Long deductionId, LocalDate month, BigDecimal amount) {
        return new UsersTaxationPensionDeductions(
            null,
            deductionId,
            month,
            amount
        );
    }
}
