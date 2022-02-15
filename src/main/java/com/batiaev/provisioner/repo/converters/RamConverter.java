package com.batiaev.provisioner.repo.converters;

import com.batiaev.provisioner.model.vm.Ram;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

@Converter
public class RamConverter implements AttributeConverter<Ram, Long> {
    @Override
    public Long convertToDatabaseColumn(Ram attribute) {
        return attribute.getValue();
    }

    @Override
    public Ram convertToEntityAttribute(Long dbData) {
        return new Ram(dbData);
    }
}