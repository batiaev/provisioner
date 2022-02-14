package com.batiaev.provisioner.model.vm;

import com.batiaev.provisioner.model.user.User;
import lombok.Builder;
import lombok.Data;

import java.time.Instant;
import java.util.UUID;

import static java.time.Instant.now;

@Data
@Builder
public class ProvisionRequest {
    @Builder.Default
    private final UUID id = UUID.randomUUID();
    @Builder.Default
    private final RequestState state = RequestState.CREATED;
    @Builder.Default
    private final Instant requested = now();
    private final User owner;
    private final VirtualMachineParams vmDetails;

    public VirtualMachine toVM() {
        return VirtualMachine.builder()
                .id(id)
                .params(vmDetails)
                .owner(owner)
                .build();
    }
}
