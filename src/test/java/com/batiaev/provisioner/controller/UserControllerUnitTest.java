package com.batiaev.provisioner.controller;

import com.batiaev.provisioner.data.TestData;
import com.batiaev.provisioner.model.user.JwtToken;
import com.batiaev.provisioner.model.user.User;
import com.batiaev.provisioner.repo.UserRepository;
import com.batiaev.provisioner.repo.VMRepository;
import com.batiaev.provisioner.service.Sha256Hasher;
import com.batiaev.provisioner.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


import static java.util.Optional.empty;
import static java.util.Optional.of;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;

class UserControllerUnitTest implements TestData {
    private UserController controller;
    private UserRepository repository;
    private UserService userService;
    private VMRepository vmRepository;

    @BeforeEach
    void setUp() {
        repository = mock(UserRepository.class);
        vmRepository = mock(VMRepository.class);
        userService = new UserService(repository, vmRepository);
        controller = new UserController(repository, userService, new Sha256Hasher());
    }

    /**
     * req.0 API for user signup
     */
    @Test
    void should_signUp() {
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
    void should_fail_signUp_duplicateEmail() {
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
    void should_fail_signUp_duplicatePhoneNumber() {
        //given
        final var request = registrationRequest();
        final var expected = user(request);
        given(repository.findByEmailOrMobile(anyString(), eq(request.getMobile().getValue()))).willReturn(of(expected));
        given(repository.save(any(User.class))).willReturn(expected);

        //when
        //then
        assertThatThrownBy(() -> controller.signUp(request)).isInstanceOf(IllegalArgumentException.class);
    }

    /**
     * req.1 API to obtain JWT token based on credentials
     */
    @Test
    void should_signIn() {
        //given
        final var expectedUser = user();
        final var expected = new JwtToken(expectedUser.getUsername());
        given(repository.findByEmailOrMobile(anyString(), anyString())).willReturn(of(expectedUser));
        //when
        final var token = controller.signIn("anton@batiaev.com", "Password123!").block();
        //then
        assertThat(token).isEqualTo(expected);
    }

    /**
     * req.5 API to delete the user account. If account gets deleted, all registered vms must be deleted also
     */
    @Test
    void should_deleteUser() {
        //given
        final var user = user();
        final var userId = user.getId();
        final var currentUser = user();
        given(repository.findById(userId)).willReturn(of(user));
        //when
        controller.deleteUser(userId, currentUser);
        //then
        verify(repository).delete(any(User.class));
        verify(vmRepository).deleteByOwner(userId);
    }
}