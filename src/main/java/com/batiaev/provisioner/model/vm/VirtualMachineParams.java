package com.batiaev.provisioner.model.vm;

import com.batiaev.provisioner.repo.converters.DiskConverter;
import com.batiaev.provisioner.repo.converters.OperationSystemConverter;
import com.batiaev.provisioner.repo.converters.RamConverter;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Convert;
import javax.persistence.Embeddable;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Embeddable
public class VirtualMachineParams {
    @Convert(converter = OperationSystemConverter.class)
    private OperationSystem os;
    @Convert(converter = RamConverter.class)
    private Ram ram;
    @Convert(converter = DiskConverter.class)
    private Disk hardDisk;
    private int cpu;
}
