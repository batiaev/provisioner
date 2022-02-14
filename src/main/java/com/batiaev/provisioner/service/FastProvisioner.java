package com.batiaev.provisioner.service;

import com.batiaev.provisioner.model.vm.ProvisionRequest;
import com.batiaev.provisioner.model.vm.VirtualMachine;
import com.batiaev.provisioner.repo.VMRepository;
import reactor.core.publisher.Mono;

import java.util.Optional;
import java.util.concurrent.CompletableFuture;

import static java.util.Optional.of;
import static java.util.concurrent.CompletableFuture.completedFuture;

public class FastProvisioner implements ProvisionerApi {
    private final VMRepository vmRepository;

    public FastProvisioner(VMRepository vmRepository) {
        this.vmRepository = vmRepository;
    }

    @Override
    public Mono<VirtualMachine> provision(ProvisionRequest request) {
        return of(request.toVM())
                .map(vmRepository::save)
                .map(Mono::just)
                .orElseThrow();
    }
}
