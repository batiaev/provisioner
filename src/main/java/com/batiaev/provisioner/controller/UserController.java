package com.batiaev.provisioner.controller;

import com.batiaev.provisioner.model.user.JwtToken;
import com.batiaev.provisioner.model.user.RegistrationRequest;
import com.batiaev.provisioner.model.user.User;
import com.batiaev.provisioner.model.user.UserRole;
import com.batiaev.provisioner.repo.UserRepository;
import com.batiaev.provisioner.service.Hasher;
import com.batiaev.provisioner.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

import javax.transaction.Transactional;
import java.util.Optional;
import java.util.UUID;

import static java.lang.String.format;

@RequestMapping("/api/v1/users")
@RestController
public class UserController {
    private final UserRepository userRepository;
    private final UserService userService;
    private final Hasher hasher;

    public UserController(@Autowired UserRepository userRepository,
                          @Autowired UserService userService,
                          @Autowired Hasher hasher) {
        this.userRepository = userRepository;
        this.userService = userService;
        this.hasher = hasher;
    }

    @GetMapping("/sign-up")
    Mono<User> signUp(@RequestBody RegistrationRequest registrationRequest) {
        final var existing = userRepository.findByEmailOrMobile(
                registrationRequest.getEmail().getValue(),
                registrationRequest.getMobile().getValue());
        if (existing.isPresent())
            throw new IllegalArgumentException("User with this email or phone number already exist");
        return Mono.just(userRepository.save(registrationRequest.toUser(hasher)));
    }

    @GetMapping("/sign-in")
    Mono<JwtToken> signIn(@RequestParam("login") String login,//FIXME use Auth header with basic auth
                          @RequestParam("password") String password) {
        return userRepository.findByEmailOrMobile(login, login)
                .filter(u -> u.validatePassword(password))
                .map(this::createToken)
                .map(Mono::just)
                .orElseThrow(() -> new IllegalArgumentException("Invalid login or password"));
    }

    private JwtToken createToken(User u) {
        return new JwtToken(u.getEmail().getValue());//FIXME
    }

    @Transactional
    @DeleteMapping("/{id}")
    void deleteUser(@PathVariable("id") UUID userId,
                    UserDetails currentUser) {
        if (currentUser.getAuthorities().contains(UserRole.MASTER)) {
            Optional<User> user = userRepository.findById(userId);
            if (user.isPresent()) {
                userService.delete(user.get());
            } else {
                throw new IllegalArgumentException("user not found!");
            }
        } else {
            throw new IllegalArgumentException(format("User %s not allowed to delete users", currentUser.getUsername()));
        }
    }
}
