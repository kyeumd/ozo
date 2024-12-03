package com.ozo.refund.model.service;

import com.ozo.refund.model.domain.type.TaxBracket;
import java.math.BigDecimal;
import java.math.RoundingMode;
import org.springframework.stereotype.Service;

@Service
public class RefundService {

    public BigDecimal calculateRefund(BigDecimal taxableIncome, BigDecimal deductionAmount, BigDecimal taxCreditsAmount) {
        //과세 표준 계산
        BigDecimal taxBase = taxableIncome.subtract(deductionAmount).setScale(0, RoundingMode.HALF_UP);

        //산출세액 계산
        TaxBracket bracket = TaxBracket.findBracket(taxBase);
        BigDecimal calculateTax = bracket.calculateTax(taxBase);

        //결정세액 계산
        return calculateTax.subtract(taxCreditsAmount);
    }
}
