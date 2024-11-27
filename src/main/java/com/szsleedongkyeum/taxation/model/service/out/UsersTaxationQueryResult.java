package com.szsleedongkyeum.taxation.model.service.out;

import java.math.BigDecimal;

public record UsersTaxationQueryResult(
    Long userId,
    BigDecimal totalIncome,
    BigDecimal taxCreditsAmount,
    BigDecimal deductionAmount
) {
}
