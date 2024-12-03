package com.ozo.taxation.model.domain;

import com.ozo.support.BaseEntity;
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
@Table(name = "USERS_TAXATION")
public class UsersTaxation extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @Column(name = "user_id")
    private Long userId;
    @Column(name = "name")
    private String name;
    @Column(name = "total_income")
    private BigDecimal totalIncome;
    @Column(name = "tax_credits_amount")
    private BigDecimal taxCreditsAmount;

    private UsersTaxation(Long id, Long userId, String name, BigDecimal totalIncome, BigDecimal taxCreditsAmount) {
        this.id = id;
        this.userId = userId;
        this.name = name;
        this.totalIncome = totalIncome;
        this.taxCreditsAmount = taxCreditsAmount;
    }

    public static UsersTaxation create(Long userId, String name, BigDecimal totalIncome, BigDecimal taxCreditsAmount) {
        return new UsersTaxation(
            null,
            userId,
            name,
            totalIncome,
            taxCreditsAmount
        );
    }
}
