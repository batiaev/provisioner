package com.batiaev.provisioner.service;

import com.batiaev.provisioner.model.vm.ProvisionRequest;
import com.batiaev.provisioner.model.vm.VirtualMachine;
import reactor.core.publisher.Mono;

public interface ProvisionerApi {
    Mono<VirtualMachine> provision(ProvisionRequest request);
}
