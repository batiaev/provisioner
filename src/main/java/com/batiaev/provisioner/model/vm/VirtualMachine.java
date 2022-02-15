package com.batiaev.provisioner.model.vm;

import com.batiaev.provisioner.model.user.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import java.util.UUID;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class VirtualMachine {
    @Id
    private UUID id;
    @ManyToOne
    private User owner;
    private VirtualMachineParams params;
}
