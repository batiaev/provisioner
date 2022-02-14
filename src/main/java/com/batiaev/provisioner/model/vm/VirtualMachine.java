package com.batiaev.provisioner.model.vm;

import com.batiaev.provisioner.model.user.User;
import lombok.Builder;

import java.util.UUID;

@Builder
public class VirtualMachine {
    private final UUID id;
    private final User owner;
    private final VirtualMachineParams params;
}
