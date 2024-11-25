package com.szsleedongkyeum.taxation.model.domain.type;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter
public class DeductionTypeConverter implements AttributeConverter<DeductionType, String> {

    @Override
    public String convertToDatabaseColumn(DeductionType deductionType) {
        return deductionType.getType();
    }

    @Override
    public DeductionType convertToEntityAttribute(String dbData) {
        return DeductionType.of(dbData);
    }
}
