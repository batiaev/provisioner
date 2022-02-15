package com.batiaev.provisioner.repo.converters;

import com.batiaev.provisioner.model.vm.OSType;
import com.batiaev.provisioner.model.vm.OperationSystem;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

import static com.batiaev.provisioner.model.vm.OperationSystem.os;

@Converter
public class OperationSystemConverter implements AttributeConverter<OperationSystem, String> {
    @Override
    public String convertToDatabaseColumn(OperationSystem attribute) {
        return attribute.toString();
    }

    @Override
    public OperationSystem convertToEntityAttribute(String dbData) {
        String[] split = dbData.split(" ");
        return os(OSType.valueOf(split[0]), split[1]);
    }
}