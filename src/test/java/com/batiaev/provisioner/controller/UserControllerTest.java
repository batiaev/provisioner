package com.batiaev.provisioner.controller;

import com.batiaev.provisioner.data.TestData;
import com.batiaev.provisioner.model.user.JwtToken;
import com.batiaev.provisioner.model.user.User;
import com.batiaev.provisioner.repo.UserRepository;
import com.batiaev.provisioner.service.Sha256Hasher;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.security.core.GrantedAuthority;

import java.util.*;

import static com.batiaev.provisioner.model.user.UserRole.MASTER;
import static java.util.Optional.empty;
import static java.util.Optional.of;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

class UserControllerTest implements TestData {
    private UserController controller;
    private UserRepository repository;

    @BeforeEach
    void setUp() {
        repository = mock(UserRepository.class);
        controller = new UserController(repository, new Sha256Hasher());
    }

    @Test
    void signUpSuccessfully() {
        //given
        final var request = registrationRequest();
        final var expected = user(request);
        given(repository.findByEmailOrMobile(anyString(), anyString())).willReturn(empty());
        given(repository.save(any(User.class))).willReturn(expected);

        //when
        final var user = controller.signUp(request).block();

        //then
        assertThat(user.getEmail()).isEqualTo(expected.getEmail());
        assertThat(user.getName()).isEqualTo(expected.getName());
        assertThat(user.getMobile()).isEqualTo(expected.getMobile());
        assertThat(user.getPassword()).isEqualTo(expected.getPassword());
        assertThat(user.getRole()).isEqualTo(expected.getRole());
    }

    @Test
    void signUpDuplicateEmail() {
        //given
        final var request = registrationRequest();
        final var expected = user(request);
        given(repository.findByEmailOrMobile(eq(request.getEmail().getValue()), anyString())).willReturn(of(expected));
        given(repository.save(any(User.class))).willReturn(expected);

        //when
        //then
        assertThatThrownBy(() -> controller.signUp(request)).isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void signUpDuplicatePhoneNumber() {
        //given
        final var request = registrationRequest();
        final var expected = user(request);
        given(repository.findByEmailOrMobile(anyString(), eq(request.getMobile().getValue()))).willReturn(of(expected));
        given(repository.save(any(User.class))).willReturn(expected);

        //when
        //then
        assertThatThrownBy(() -> controller.signUp(request)).isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void signIn() {
        //given
        final var expected = new JwtToken("token");
        //when
        final var token = controller.signIn("anton@batiaev.com", "Password123!").block();
        //then
        assertThat(token).isEqualTo(expected);
    }

    @Test
    void deleteUser() {
        //given
        UUID userId = UUID.randomUUID();
        final var currentUser = user();
        final var user = user();
        given(repository.findById(userId)).willReturn(of(user));
        //when
        controller.deleteUser(userId, currentUser);
        //then
        verify(repository).delete(any(User.class));
    }
}