package com.batiaev.provisioner.model.user;

import lombok.Builder;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Set;
import java.util.UUID;

@Data
@Builder
public class User implements UserDetails {
    public final static User EMPTY = new User(null, null, new Email("foo@bar.com"), null, null, null);
    private final UUID id;
    private final String name;
    private final Email email;
    private final PhoneNumber mobile;
    private final Password password;
    private final UserRole role;

    public boolean validatePassword(String password) {
        return this.password.getValue().equals(password);
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Set.of(role);
    }

    @Override
    public String getUsername() {
        return email.getValue();
    }

    @Override
    public String getPassword() {
        return password.getValue();
    }

    @Override
    public boolean isAccountNonExpired() {
        return false;
    }

    @Override
    public boolean isAccountNonLocked() {
        return false;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return false;
    }

    public boolean isEnabled() {
        return true;
    }
}
