package com.batiaev.provisioner.model.user;

import com.batiaev.provisioner.service.Hasher;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

import java.util.UUID;

@Getter
@ToString
@EqualsAndHashCode
public class RegistrationRequest {
    private final String name;
    private final Email email;
    private final PhoneNumber mobile;
    private final Password password;
    private final UserRole role;

    @JsonCreator
    public RegistrationRequest(@JsonProperty(value = "name", required = true) String name,
                               @JsonProperty(value = "email", required = true) String email,
                               @JsonProperty(value = "mobile", required = true) String mobile,
                               @JsonProperty(value = "password", required = true) String password,
                               @JsonProperty(value = "master", required = true) boolean master) {
        this.name = name;
        this.email = new Email(email);
        this.mobile = new PhoneNumber(mobile);
        this.password = new Password(password);
        this.role = master ? UserRole.MASTER : UserRole.USER;
    }

    public User toUser(Hasher hasher) {
        return User.builder()
                .id(UUID.randomUUID())
                .name(name)
                .email(email)
                .mobile(mobile)
                .password(new Password(hasher.hash(password.getValue())))
                .role(role)
                .build();
    }
}
