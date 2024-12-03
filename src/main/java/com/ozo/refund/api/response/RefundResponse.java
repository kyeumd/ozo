package com.ozo.refund.api.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;

public record RefundResponse(
    @JsonProperty("결정세액") String finalTaxAmount
) {

    public static RefundResponse from(BigDecimal amount) {
        DecimalFormat formatter = new DecimalFormat("#,##0");
        String finalTaxAmount = formatter.format(amount.setScale(0, RoundingMode.HALF_UP));
        return new RefundResponse(finalTaxAmount);
    }

}
