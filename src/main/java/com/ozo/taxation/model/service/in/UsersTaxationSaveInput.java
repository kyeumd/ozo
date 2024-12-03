package com.ozo.taxation.model.service.in;

import java.util.List;
import java.util.Map;

public record UsersTaxationSaveInput(
    String totalIncome,
    String name,
    List<pensionData> pensions,
    CreditCardDeductions creditCardDeductions,
    String taxCredit
) {
    public record pensionData(
        String month,
        String amount
    ) {
    }

    public record CreditCardDeductions(
        List<Map<String, String>> month,
        int year
    ) {

    }
}
