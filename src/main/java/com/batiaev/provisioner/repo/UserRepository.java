package com.batiaev.provisioner.repo;

import com.batiaev.provisioner.model.user.User;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;
import java.util.UUID;

public interface UserRepository extends CrudRepository<User, UUID> {
    Optional<User> findByEmailOrMobile(String email, String mobile);
    Optional<User> findByEmail(String email);
}
