package com.szsleedongkyeum.refund.model.service;

import com.szsleedongkyeum.refund.model.domain.type.TaxBracket;
import java.math.BigDecimal;
import org.springframework.stereotype.Service;

@Service
public class RefundService {

    public BigDecimal calculateRefund(BigDecimal taxableIncome, BigDecimal deductionAmount, BigDecimal taxCreditsAmount) {
        //과세 표준 계산
        BigDecimal taxBase = taxCreditsAmount.subtract(deductionAmount);

        //산출세액 계산
        TaxBracket bracket = TaxBracket.findBracket(taxableIncome);
        BigDecimal calculateTax = bracket.calculateTax(taxableIncome);

        //결정세액 계산
        return calculateTax.subtract(taxCreditsAmount);
    }
}
