package com.batiaev.provisioner.repo.converters;

import com.batiaev.provisioner.model.user.Password;

import javax.persistence.AttributeConverter;

public class PasswordConverter implements AttributeConverter<Password, String> {
    @Override
    public String convertToDatabaseColumn(Password attribute) {
        return attribute.getValue();
    }

    @Override
    public Password convertToEntityAttribute(String dbData) {
        return new Password(dbData);
    }
}