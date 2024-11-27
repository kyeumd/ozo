package com.szsleedongkyeum.refund.application;

import com.szsleedongkyeum.refund.model.service.RefundService;
import com.szsleedongkyeum.security.service.AuthenticationUserService;
import com.szsleedongkyeum.taxation.model.service.UserTaxationQueryService;
import com.szsleedongkyeum.taxation.model.service.out.UsersTaxationQueryResult;
import java.math.BigDecimal;
import java.time.Year;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RefundFacade {

    private final UserTaxationQueryService userTaxationQueryService;
    private final RefundService refundService;

    public BigDecimal calculateRefund() {
        Long userId = AuthenticationUserService.getUserId();
        UsersTaxationQueryResult userTaxation = userTaxationQueryService.getUserTaxation(userId, Year.now().getValue() - 1);
        return refundService.calculateRefund(userTaxation.totalIncome(), userTaxation.deductionAmount(), userTaxation.taxCreditsAmount());
    }
}
