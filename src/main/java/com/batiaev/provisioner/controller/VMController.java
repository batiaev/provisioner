package com.batiaev.provisioner.controller;

import com.batiaev.provisioner.model.user.User;
import com.batiaev.provisioner.model.vm.ProvisionRequest;
import com.batiaev.provisioner.model.vm.VirtualMachine;
import com.batiaev.provisioner.repo.UserRepository;
import com.batiaev.provisioner.repo.VMRepository;
import com.batiaev.provisioner.service.ProvisionerApi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.*;
import java.util.stream.StreamSupport;

import static java.lang.Long.MAX_VALUE;

@RestController
public class VMController {
    private final VMRepository vmRepository;
    private final UserRepository userRepository;
    private final ProvisionerApi provisioner;

    public VMController(@Autowired VMRepository vmRepository,
                        @Autowired UserRepository userRepository,
                        @Autowired ProvisionerApi provisioner) {
        this.vmRepository = vmRepository;
        this.userRepository = userRepository;
        this.provisioner = provisioner;
    }

    @PostMapping("/api/v1/vm/provision")
    Mono<VirtualMachine> provision(@RequestBody ProvisionRequest provisionRequest,
                                   UserDetails userDetails) {
        return provisioner.provision(provisionRequest);
    }

    @PostMapping("/api/v1/vm")
    Flux<VirtualMachine> getAllVM(@RequestParam("userId") Optional<UUID> userId,
                                  @RequestParam("all") boolean all,
                                  @RequestParam("limit") Optional<Long> limit,
                                  UserDetails currentUser) {
        Iterable<VirtualMachine> virtualMachines = loadVmList(userId, currentUser, all);
        return Flux.fromStream(StreamSupport
                .stream(virtualMachines.spliterator(), false)
                .limit(limit.orElse(MAX_VALUE)));
    }

    private Iterable<VirtualMachine> loadVmList(Optional<UUID> userId, UserDetails currentUser, boolean all) {
        if (userId.isPresent())
            return vmRepository.findByOwner(userId.get());

        User user = currentUser instanceof User
                ? (User) currentUser
                : userRepository.findByEmail(currentUser.getUsername()).orElseThrow();
        return all ? vmRepository.findAll() : vmRepository.findByOwner(user.getId());
    }
}
