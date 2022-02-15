package com.batiaev.provisioner.repo.converters;

import com.batiaev.provisioner.model.user.Email;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

@Converter
public class EmailConverter implements AttributeConverter<Email, String> {
    @Override
    public String convertToDatabaseColumn(Email attribute) {
        return attribute.getValue();
    }

    @Override
    public Email convertToEntityAttribute(String dbData) {
        return new Email(dbData);
    }
}