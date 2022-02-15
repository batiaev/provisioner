package com.batiaev.provisioner.repo;

import com.batiaev.provisioner.model.vm.VirtualMachine;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.UUID;

@Repository
public interface VMRepository extends CrudRepository<VirtualMachine, UUID> {
    default Collection<VirtualMachine> findByOwner(UUID userId) {
        return findByOwnerOrderByParams_Ram(userId);
    }

    Collection<VirtualMachine> findByOwnerOrderByParams_Ram(UUID userId);

    void deleteByOwner(UUID userId);
}
