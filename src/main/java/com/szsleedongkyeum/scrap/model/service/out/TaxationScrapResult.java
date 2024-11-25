package com.szsleedongkyeum.scrap.model.service.out;


import com.fasterxml.jackson.annotation.JsonProperty;
import com.szsleedongkyeum.taxation.model.service.in.UsersTaxationSaveInput;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public record TaxationScrapResult(
    String status,
    Data data,
    @JsonProperty("errors") Errors error
) {

    public TaxationScrapResult(String status, Data data, Errors error) {
        this.status = status;
        this.data = data;
        this.error = error;
    }

    public record Data(
        @JsonProperty("종합소득금액")
        String totalIncome,
        @JsonProperty("이름")
        String name,
        @JsonProperty("소득공제")
        Deductions deductions
    ) {
    }

    public record Deductions(
        @JsonProperty("국민연금")
        List<Pension> pension,
        @JsonProperty("신용카드소득공제")
        CreditCardDeduction creditCardDeduction,
        @JsonProperty("세액공제")
        String taxCredit
    ) {
    }

    public record Pension(
        @JsonProperty("월")
        String month,
        @JsonProperty("공제액")
        String amount
    ) {
    }

    public record CreditCardDeduction(
        @JsonProperty("month")
        List<Map<String, String>> month,
        @JsonProperty("year")
        int year
    ) {
    }

    public record Errors(
        String code,
        String message,
        String validations
    ) {
    }

    public UsersTaxationSaveInput convertSaveInput() {
        List<UsersTaxationSaveInput.pensionData> pensions = this.data().deductions().pension().stream()
                                                                .map(p -> new UsersTaxationSaveInput.pensionData(p.month(),
                                                                    p.amount()))
                                                                .collect(Collectors.toList());

        Map<String, String> creditCardDeductions = this.data().deductions().creditCardDeduction().month().stream()
                                                       .flatMap(map -> map.entrySet().stream())
                                                       .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));

        return new UsersTaxationSaveInput(
            this.data().totalIncome(),
            this.data().name(),
            pensions,
            creditCardDeductions,
            this.data().deductions().taxCredit()
        );
    }
}
