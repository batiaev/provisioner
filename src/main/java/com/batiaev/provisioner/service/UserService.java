package com.batiaev.provisioner.service;

import com.batiaev.provisioner.model.user.User;
import com.batiaev.provisioner.repo.UserRepository;
import com.batiaev.provisioner.repo.VMRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("userDetailsService")
@Transactional
public class UserService implements UserDetailsService {

    private final UserRepository userRepository;
    private final VMRepository vmRepository;

    public UserService(@Autowired UserRepository userRepository,
                       @Autowired VMRepository vmRepository) {
        this.userRepository = userRepository;
        this.vmRepository = vmRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        return userRepository.findByEmail(email).orElse(User.EMPTY);
    }

    @Transactional
    public void delete(User user) {
        userRepository.delete(user);
        vmRepository.deleteByOwner(user.getId());
    }

}
