package com.szsleedongkyeum.taxation.model.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

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
import java.util.Map;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class UsersTaxationServiceTest {

    @Autowired
    private UsersTaxationService usersTaxationService;

    @Autowired
    private UserTaxationRepository userTaxationRepository;

    @Autowired
    private UsersTaxationPensionDeductionsRepository usersTaxationPensionDeductionsRepository;

    @Autowired
    private UsersTaxationCreditCardDeductionsRepository usersTaxationCreditCardDeductionsRepository;

    @Test
    @Transactional
    @DisplayName("공제_정보를_저장한다.")
    void saveTaxation_ShouldSaveDataCorrectly() {
        Long userId = 1L;
        String userName = "동탁";

        UsersTaxationSaveInput.CreditCardDeductions creditCardDeductions =
            new UsersTaxationSaveInput.CreditCardDeductions(
                List.of(
                    Map.of("1", "1000"),
                    Map.of("2", "2000"),
                    Map.of("3", "3000")
                ),
                2023
            );

        UsersTaxationSaveInput taxationInfo = new UsersTaxationSaveInput(
            "50000",
            "동탁",
            List.of(
                new UsersTaxationSaveInput.pensionData("2024-10", "1000"),
                new UsersTaxationSaveInput.pensionData("2024-08", "2000")
            ),
            creditCardDeductions,
            "3000"
        );

        usersTaxationService.saveTaxation(userId, userName, taxationInfo);

        UsersTaxation savedTaxation = userTaxationRepository.findById(userId).orElseThrow();
        assertEquals(userId, savedTaxation.getUserId());
        assertEquals(new BigDecimal("50000"), savedTaxation.getTotalIncome());
        assertEquals(new BigDecimal("3000"), savedTaxation.getTaxCreditsAmount());

        List<UsersTaxationPensionDeductions> savedPensions =
            usersTaxationPensionDeductionsRepository.findAllByUserId(userId);
        assertEquals(2, savedPensions.size());
        assertTrue(savedPensions.stream().anyMatch(pd ->
            pd.getYearMonth().equals("2024-10") && pd.getAmount().equals(new BigDecimal("1000"))
        ));
        assertTrue(savedPensions.stream().anyMatch(pd ->
            pd.getYearMonth().equals("2024-08") && pd.getAmount().equals(new BigDecimal("2000"))
        ));

        List<UsersTaxationCreditCardDeductions> savedCreditCardDeductions =
            usersTaxationCreditCardDeductionsRepository.findAllByUserId(userId);
        assertEquals(3, savedCreditCardDeductions.size());
        assertTrue(savedCreditCardDeductions.stream().anyMatch(ccd ->
            ccd.getDeductionYear() == 2023 && ccd.getDeductionMonth() == 1 && ccd.getAmount().equals(new BigDecimal("1000"))
        ));
        assertTrue(savedCreditCardDeductions.stream().anyMatch(ccd ->
            ccd.getDeductionYear() == 2023 && ccd.getDeductionMonth() == 2 && ccd.getAmount().equals(new BigDecimal("2000"))
        ));
        assertTrue(savedCreditCardDeductions.stream().anyMatch(ccd ->
            ccd.getDeductionYear() == 2023 && ccd.getDeductionMonth() == 3 && ccd.getAmount().equals(new BigDecimal("3000"))
        ));
    }

}