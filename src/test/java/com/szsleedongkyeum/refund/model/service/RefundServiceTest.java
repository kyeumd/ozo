package com.szsleedongkyeum.refund.model.service;

import static org.junit.jupiter.api.Assertions.assertEquals;

import com.szsleedongkyeum.refund.model.domain.type.TaxBracket;
import java.math.BigDecimal;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class RefundServiceTest {
    @ParameterizedTest
    @CsvSource({
        "10000000, 600000",
        "20000000, 1740000",
        "60000000, 8640000",
        "100000000, 19560000",
        "200000000, 56060000",
        "400000000, 134060000",
        "700000000, 258060000",
        "1200000000, 474060000"
    })
    @DisplayName("결정_세액을_계산한다.")
    void testCalculateTax(String income, String expectedTax) {
        BigDecimal taxableIncome = new BigDecimal(income);
        BigDecimal expected = new BigDecimal(expectedTax);

        TaxBracket actualBracket = TaxBracket.findBracket(taxableIncome);
        BigDecimal actualTax = actualBracket.calculateTax(taxableIncome);

        assertEquals(expected, actualTax);
    }
}