package com.szsleedongkyeum.taxation.model.service;

import com.szsleedongkyeum.taxation.infra.UserTaxationRepository;
import com.szsleedongkyeum.taxation.infra.UsersTaxationCreditCardDeductionsRepository;
import com.szsleedongkyeum.taxation.infra.UsersTaxationPensionDeductionsRepository;
import com.szsleedongkyeum.taxation.model.domain.UsersTaxation;
import com.szsleedongkyeum.taxation.model.domain.UsersTaxationCreditCardDeductions;
import com.szsleedongkyeum.taxation.model.domain.UsersTaxationPensionDeductions;
import com.szsleedongkyeum.taxation.model.service.in.UsersTaxationSaveInput;
import jakarta.transaction.Transactional;
import java.math.BigDecimal;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UsersTaxationService {

    private final UserTaxationRepository userTaxationRepository;
    private final UsersTaxationPensionDeductionsRepository usersTaxationPensionDeductionsRepository;
    private final UsersTaxationCreditCardDeductionsRepository usersTaxationCreditCardDeductionsRepository;

    @Transactional
    public void saveTaxation(Long userId, String userName, UsersTaxationSaveInput taxationInfo) {
        UsersTaxation usersTaxation = UsersTaxation.create(userId, userName, new BigDecimal(taxationInfo.totalIncome()),
            new BigDecimal(taxationInfo.taxCredit()));
        List<UsersTaxationPensionDeductions> pensionDeductions = taxationInfo.pensions().stream()
                                                                             .map(pensionData ->
                                                                                 UsersTaxationPensionDeductions.create(
                                                                                     userId,
                                                                                     pensionData.month(),
                                                                                     new BigDecimal(pensionData.amount().replace(",", ""))
                                                                                 )
                                                                             )
                                                                             .toList();
        int creditCardDeductionYear = taxationInfo.creditCardDeductions().year();
        List<UsersTaxationCreditCardDeductions> creditCardDeductions = taxationInfo.creditCardDeductions().month().stream()
                                                                                   .flatMap(monthMap -> monthMap.entrySet().stream())
                                                                                   .map(
                                                                                       entry -> UsersTaxationCreditCardDeductions.create(
                                                                                           userId,
                                                                                           creditCardDeductionYear,
                                                                                           Integer.parseInt(entry.getKey()),
                                                                                           new BigDecimal(entry.getValue().replace(",", ""))
                                                                                       )
                                                                                   ).toList();
        userTaxationRepository.save(usersTaxation);
        usersTaxationPensionDeductionsRepository.saveAll(pensionDeductions);
        usersTaxationCreditCardDeductionsRepository.saveAll(creditCardDeductions);
    }
}
