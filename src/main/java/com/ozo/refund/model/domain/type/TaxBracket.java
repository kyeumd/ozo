package com.ozo.refund.model.domain.type;

import java.math.BigDecimal;
import java.math.RoundingMode;

public enum TaxBracket {
    BRACKET_1(BigDecimal.ZERO, new BigDecimal("14000000"), BigDecimal.ZERO, new BigDecimal("0.06")),
    BRACKET_2(new BigDecimal("14000000"), new BigDecimal("50000000"), new BigDecimal("840000"), new BigDecimal("0.15")),
    BRACKET_3(new BigDecimal("50000000"), new BigDecimal("88000000"), new BigDecimal("6240000"), new BigDecimal("0.24")),
    BRACKET_4(new BigDecimal("88000000"), new BigDecimal("150000000"), new BigDecimal("15360000"), new BigDecimal("0.35")),
    BRACKET_5(new BigDecimal("150000000"), new BigDecimal("300000000"), new BigDecimal("37060000"), new BigDecimal("0.38")),
    BRACKET_6(new BigDecimal("300000000"), new BigDecimal("500000000"), new BigDecimal("94060000"), new BigDecimal("0.40")),
    BRACKET_7(new BigDecimal("500000000"), new BigDecimal("1000000000"), new BigDecimal("174060000"), new BigDecimal("0.42")),
    BRACKET_8(new BigDecimal("1000000000"), null, new BigDecimal("384060000"), new BigDecimal("0.45"));

    private final BigDecimal startLimit;
    private final BigDecimal limit;
    private final BigDecimal baseTax;
    private final BigDecimal rate;

    TaxBracket(BigDecimal startLimit, BigDecimal limit, BigDecimal baseTax, BigDecimal rate) {
        this.startLimit = startLimit;
        this.limit = limit;
        this.baseTax = baseTax;
        this.rate = rate;
    }

    public static TaxBracket findBracket(BigDecimal taxableIncome) {
        for (TaxBracket bracket : values()) {
            if (bracket.limit == null || taxableIncome.compareTo(bracket.limit) <= 0) {
                return bracket;
            }
        }
        throw new IllegalArgumentException("No tax bracket found for taxable income: " + taxableIncome);
    }

    public BigDecimal calculateTax(BigDecimal taxableIncome) {
        BigDecimal excessAmount = taxableIncome.subtract(startLimit);
        excessAmount = excessAmount.compareTo(BigDecimal.ZERO) > 0 ? excessAmount : BigDecimal.ZERO;
        BigDecimal excessTax = excessAmount.multiply(rate).setScale(0, RoundingMode.HALF_UP);
        return baseTax.add(excessTax).setScale(0, RoundingMode.HALF_UP);
    }
}