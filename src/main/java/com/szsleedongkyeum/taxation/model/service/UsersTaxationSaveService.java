package com.szsleedongkyeum.taxation.model.service;

import com.szsleedongkyeum.taxation.infra.UserTaxationRepository;
import com.szsleedongkyeum.taxation.infra.UsersTaxationDeductionsRepository;
import com.szsleedongkyeum.taxation.model.domain.UsersTaxation;
import com.szsleedongkyeum.taxation.model.domain.UsersTaxationDeductions;
import com.szsleedongkyeum.taxation.model.service.in.UsersTaxationSaveInput;
import jakarta.transaction.Transactional;
import java.math.BigDecimal;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UsersTaxationSaveService {

    private final UserTaxationRepository userTaxationRepository;
    private final UsersTaxationDeductionsRepository usersTaxationDeductionsRepository;

    @Transactional
    public void saveTaxation(Long userId, String userName, UsersTaxationSaveInput taxationInfo) {
        UsersTaxation usersTaxation = UsersTaxation.create(userId, userName, new BigDecimal(taxationInfo.totalIncome()),
            new BigDecimal(taxationInfo.taxCredit()));

        Map<String, UsersTaxationDeductions> deductionMap = new HashMap<>();

        pensionDeductionMap(userId, taxationInfo, deductionMap);

        creditCardDeductionMap(userId, taxationInfo, deductionMap);

        List<UsersTaxationDeductions> usersTaxationDeductions = deductionMap.values().stream()
                                                                            .sorted(Comparator.comparing(UsersTaxationDeductions::getDeductionMonth))
                                                                            .toList();
        userTaxationRepository.save(usersTaxation);
        usersTaxationDeductionsRepository.saveAll(usersTaxationDeductions);
    }

    private void pensionDeductionMap(Long userId, UsersTaxationSaveInput taxationInfo, Map<String, UsersTaxationDeductions> deductionMap) {
        for (var pension : taxationInfo.pensions()) {
            int year = Integer.parseInt(pension.month().substring(0, 4));
            int month = Integer.parseInt(pension.month().substring(5, 7));
            String key = year + "-" + month;

            deductionMap.computeIfAbsent(key, k -> UsersTaxationDeductions.create(userId, year, month, null, null))
                        .setPensionDeduction(parseAmount(pension.amount()));
        }
    }

    private void creditCardDeductionMap(Long userId, UsersTaxationSaveInput taxationInfo, Map<String, UsersTaxationDeductions> deductionMap) {
        for (var monthMap : taxationInfo.creditCardDeductions().month()) {
            for (var entry : monthMap.entrySet()) {
                int month = Integer.parseInt(entry.getKey());
                int year = taxationInfo.creditCardDeductions().year();
                String key = year + "-" + month;

                deductionMap.computeIfAbsent(key, k -> UsersTaxationDeductions.create(userId, year, month, null, null))
                            .setCreditCardDeduction(parseAmount(entry.getValue()));
            }
        }
    }

    private BigDecimal parseAmount(String amount) {
        return new BigDecimal(amount.replace(",", ""));
    }
}
