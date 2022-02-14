package com.batiaev.provisioner.service;

import com.batiaev.provisioner.model.vm.ProvisionRequest;
import com.batiaev.provisioner.model.vm.VirtualMachine;
import com.batiaev.provisioner.repo.VMRepository;
import lombok.Data;
import org.springframework.scheduling.annotation.Scheduled;
import reactor.core.publisher.Mono;

import java.util.LinkedList;
import java.util.Objects;
import java.util.Queue;
import java.util.concurrent.CompletableFuture;

import static java.lang.Math.min;
import static java.util.Optional.of;
import static java.util.stream.IntStream.range;

@Deprecated(forRemoval = true)
public class DelayedProvisioner implements ProvisionerApi {
    private final int provisioningSize;
    private final VMRepository vmRepository;
    private final Queue<Item> requestQueue = new LinkedList<>();

    public DelayedProvisioner(int provisioningSize,
                              VMRepository vmRepository) {
        this.provisioningSize = provisioningSize;
        this.vmRepository = vmRepository;
    }

    @Override
    public Mono<VirtualMachine> provision(ProvisionRequest request) {
        final var response = new CompletableFuture<VirtualMachine>();
        final var item = new Item(request, response);
        requestQueue.add(item);
        return Mono.fromFuture(item.response);
    }

    @Scheduled(fixedDelayString = "${provisioning.delay}")
    public void process() {
        range(0, min(provisioningSize, requestQueue.size()))
                .mapToObj(v -> requestQueue.poll())
                .filter(Objects::nonNull)
                .forEach(this::processItem);
    }

    private void processItem(Item item) {
        of(item.request)
                .map(ProvisionRequest::toVM)
                .map(vmRepository::save)
                .map(item.response::complete);
    }

    @Data
    private static class Item {
        private final ProvisionRequest request;
        private final CompletableFuture<VirtualMachine> response;
    }
}
