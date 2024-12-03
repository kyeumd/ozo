package com.ozo.taxation.model.service;

import com.ozo.taxation.infra.UserTaxationRepository;
import com.ozo.taxation.infra.UsersTaxationDeductionsRepository;
import com.ozo.taxation.model.domain.UsersTaxation;
import com.ozo.taxation.model.domain.UsersTaxationDeductions;
import com.ozo.taxation.model.service.out.UsersTaxationQueryResult;
import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserTaxationQueryService {

    private final UserTaxationRepository userTaxationRepository;
    private final UsersTaxationDeductionsRepository usersTaxationDeductionsRepository;

    public UsersTaxationQueryResult getUserTaxation(Long userId, int year) {
        Optional<UsersTaxation> usersTaxationOptional = userTaxationRepository.findByUserId(userId);
        List<UsersTaxationDeductions> usersTaxationDeductions = usersTaxationDeductionsRepository.findAllByUserIdAndDeductionYear(userId, year);
        UsersTaxation usersTaxation = usersTaxationOptional.orElseThrow(() -> new IllegalStateException("소득 정보가 없습니다. 소득 정보 조회 후 이용 가능합니다."));

        BigDecimal totalDeduction = calculateTotalDeduction(usersTaxationDeductions);
        return new UsersTaxationQueryResult(userId, usersTaxation.getTotalIncome(), usersTaxation.getTaxCreditsAmount(), totalDeduction);
    }

    private BigDecimal calculateTotalDeduction(List<UsersTaxationDeductions> usersTaxationDeductions) {
        return usersTaxationDeductions.stream().map(taxation -> {
                BigDecimal pension = taxation.getPensionDeduction() != null ? taxation.getPensionDeduction() : BigDecimal.ZERO;
                BigDecimal credit = taxation.getCreditCardDeduction() != null ? taxation.getCreditCardDeduction() : BigDecimal.ZERO;
                return pension.add(credit);
            }
        ).reduce(BigDecimal.ZERO, BigDecimal::add);
    }
}
