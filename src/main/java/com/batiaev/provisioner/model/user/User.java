package com.batiaev.provisioner.model.user;

import com.batiaev.provisioner.repo.converters.EmailConverter;
import com.batiaev.provisioner.repo.converters.PasswordConverter;
import com.batiaev.provisioner.repo.converters.PhoneNumberConverter;
import com.batiaev.provisioner.repo.converters.UserRoleConverter;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Collection;
import java.util.Set;
import java.util.UUID;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class User implements UserDetails {
    public final static User EMPTY = new User(null, null, new Email("foo@bar.com"), null, null, null);
    @Id
    private UUID id;
    private String name;
    @Convert(converter = EmailConverter.class)
    private Email email;
    @Convert(converter = PhoneNumberConverter.class)
    private PhoneNumber mobile;
    @Convert(converter = PasswordConverter.class)
    private Password password;
    @Convert(converter = UserRoleConverter.class)
    private UserRole role;

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
