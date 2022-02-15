package com.batiaev.provisioner.repo.converters;

import com.batiaev.provisioner.model.vm.Disk;
import com.batiaev.provisioner.model.vm.DiskType;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

@Converter
public class DiskConverter implements AttributeConverter<Disk, String> {
    @Override
    public String convertToDatabaseColumn(Disk attribute) {
        return attribute.toString();
    }

    @Override
    public Disk convertToEntityAttribute(String dbData) {
        String[] split = dbData.split(" ");
        return new Disk(DiskType.valueOf(split[0]), Long.valueOf(split[1]));
    }
}