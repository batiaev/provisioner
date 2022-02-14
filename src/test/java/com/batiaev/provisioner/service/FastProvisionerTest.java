package com.batiaev.provisioner.service;

import com.batiaev.provisioner.model.user.User;
import com.batiaev.provisioner.model.vm.ProvisionRequest;
import com.batiaev.provisioner.model.vm.VirtualMachine;
import com.batiaev.provisioner.model.vm.VirtualMachineParams;
import com.batiaev.provisioner.repo.VMRepository;
import org.junit.jupiter.api.Test;

import static com.batiaev.provisioner.model.vm.OSType.MACOS;
import static com.batiaev.provisioner.model.vm.OperationSystem.os;
import static com.batiaev.provisioner.model.vm.Ram.gb;
import static com.batiaev.provisioner.model.vm.RequestState.CREATED;
import static java.time.Instant.now;
import static java.util.UUID.randomUUID;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;

class FastProvisionerTest {
    @Test
    void provision() {
        //given
        final var user = mock(User.class);
        final var repo = mock(VMRepository.class);
        final var vmDetails = VirtualMachineParams.builder()
                .cpu(4)
                .os(os(MACOS, "11.6.2"))
                .ram(gb(1))
                .build();
        final var request = ProvisionRequest.builder()
                .id(randomUUID())
                .requested(now())
                .owner(user)
                .state(CREATED)
                .vmDetails(vmDetails)
                .build();
        final var expected = VirtualMachine.builder()
                .params(vmDetails)
                .owner(user)
                .id(request.getId())
                .build();

        given(repo.save(any(VirtualMachine.class))).willReturn(expected);

        ProvisionerApi provisioner = new FastProvisioner(repo);

        //when
        final var vm = provisioner.provision(request);
        //then
        assertThat(vm.block()).isEqualTo(expected);
    }
}