package com.batiaev.provisioner.model.vm;

import lombok.Builder;

@Builder
public class VirtualMachineParams {
    private final OperationSystem os;
    private final Ram ram;
    private final Disk hardDisk;
    private final int cpu;
}
