package com.szsleedongkyeum.refund.model.service;

import com.szsleedongkyeum.refund.model.domain.type.TaxBracket;
import java.math.BigDecimal;
import org.springframework.stereotype.Service;

@Service
public class RefundService {

    public BigDecimal calculateRefund(BigDecimal taxableIncome) {
        TaxBracket bracket = TaxBracket.findBracket(taxableIncome);
        return bracket.calculateTax(taxableIncome);
    }
}
