package com.batiaev.provisioner.model.vm;

import lombok.Builder;

import static com.batiaev.provisioner.model.vm.Version.version;

@Builder
public class OperationSystem {
    private final OSType type;
    private final Version version;

    public static OperationSystem os(OSType type, String version) {
        return OperationSystem.builder().type(type).version(version(version)).build();
    }
}
