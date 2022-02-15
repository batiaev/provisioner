package com.batiaev.provisioner.repo.converters;

import com.batiaev.provisioner.model.user.Password;
import com.batiaev.provisioner.model.user.UserRole;

import javax.persistence.AttributeConverter;

public class UserRoleConverter implements AttributeConverter<UserRole, String> {
    @Override
    public String convertToDatabaseColumn(UserRole attribute) {
        return attribute.name();
    }

    @Override
    public UserRole convertToEntityAttribute(String dbData) {
        return UserRole.valueOf(dbData);
    }
}