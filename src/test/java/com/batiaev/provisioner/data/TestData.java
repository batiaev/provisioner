package com.batiaev.provisioner.data;

import com.batiaev.provisioner.model.user.RegistrationRequest;
import com.batiaev.provisioner.model.user.User;

import java.util.UUID;

import static com.batiaev.provisioner.model.user.UserRole.MASTER;

public interface TestData {
    default RegistrationRequest registrationRequest() {
        return new RegistrationRequest("Anton", "anton@batiaev.com", "+447555555555", "Password123!", true);
    }

    default User user(RegistrationRequest request) {
        return User.builder()
                .id(UUID.randomUUID())
                .email(request.getEmail())
                .name(request.getName())
                .mobile(request.getMobile())
                .password(request.getPassword())
                .role(MASTER)
                .build();
    }

    default User user() {
        return user(registrationRequest());
    }
}
