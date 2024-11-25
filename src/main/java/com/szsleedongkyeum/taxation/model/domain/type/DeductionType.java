package com.szsleedongkyeum.taxation.model.domain.type;

import java.util.Arrays;
import lombok.Getter;

@Getter
public enum DeductionType {
    PENSION_DEDUCTION("국민연금", "국민연금"),
    CREDIT_CARD_DEDUCTION("신용카드소득공제", "신용카드소득공제");

    private final String type;
    private final String description;

    DeductionType(String type, String description) {
        this.type = type;
        this.description = description;
    }

    public static DeductionType of(String type) {
        return Arrays.stream(values()).filter(t -> type.equals(t.type))
                     .findAny()
                     .orElseThrow(() -> new IllegalArgumentException("공제 타입을 찾을 수 없습니다."));
    }
}
