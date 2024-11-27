package com.szsleedongkyeum.refund.model.service;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.math.BigDecimal;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class RefundServiceTest {

    @ParameterizedTest
    @CsvSource({
        "20000000, 1000000, 200000, 1390000",
        "50000000, 10000000, 500000, 4240000",
        "70000000, 15000000, 1000000, 6440000",
        "120000000, 20000000, 1500000, 18060000",
        "200000000, 50000000, 3000000, 34060000",
        "400000000, 100000000, 5000000, 89060000",
        "15000000, 1000000, 100000, 740000",
        "10000000, 500000, 50000, 520000"
    })
    @DisplayName("결정_세액을_계산한다.")
    void testCalculateFinalTax(String totalIncomeStr, String incomeDeductionStr, String taxDeductionStr, String expectedFinalTaxStr) {
        BigDecimal totalIncome = new BigDecimal(totalIncomeStr);
        BigDecimal incomeDeduction = new BigDecimal(incomeDeductionStr);
        BigDecimal taxDeduction = new BigDecimal(taxDeductionStr);
        BigDecimal expectedFinalTax = new BigDecimal(expectedFinalTaxStr);
        RefundService refundService = new RefundService();

        BigDecimal actualResultTax = refundService.calculateRefund(totalIncome, incomeDeduction, taxDeduction);

        assertEquals(expectedFinalTax, actualResultTax);
    }
}