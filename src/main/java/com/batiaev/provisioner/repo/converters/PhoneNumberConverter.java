package com.batiaev.provisioner.repo.converters;

import com.batiaev.provisioner.model.user.PhoneNumber;

import javax.persistence.AttributeConverter;

public class PhoneNumberConverter implements AttributeConverter<PhoneNumber, String> {
    @Override
    public String convertToDatabaseColumn(PhoneNumber attribute) {
        return attribute.getValue();
    }

    @Override
    public PhoneNumber convertToEntityAttribute(String dbData) {
        return new PhoneNumber(dbData);
    }
}