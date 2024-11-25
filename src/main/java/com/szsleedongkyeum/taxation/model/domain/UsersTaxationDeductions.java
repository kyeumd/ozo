package com.szsleedongkyeum.taxation.model.domain;

import com.szsleedongkyeum.taxation.model.domain.type.DeductionType;
import com.szsleedongkyeum.taxation.model.domain.type.DeductionTypeConverter;
import jakarta.persistence.Column;
import jakarta.persistence.Convert;
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
    @Convert(converter = DeductionTypeConverter.class)
    private DeductionType deductionType;

    private UsersTaxationDeductions(Long id, Long userId, DeductionType deductionType) {
        this.id = id;
        this.userId = userId;
        this.deductionType = deductionType;
    }

    public static UsersTaxationDeductions create(Long userId, String deductionType) {
        return new UsersTaxationDeductions(
            null,
            userId,
            DeductionType.of(deductionType)
        );
    }
}
