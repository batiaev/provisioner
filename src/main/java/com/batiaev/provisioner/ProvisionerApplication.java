package com.batiaev.provisioner;

import com.batiaev.provisioner.repo.VMRepository;
import com.batiaev.provisioner.service.FastProvisioner;
import com.batiaev.provisioner.service.Hasher;
import com.batiaev.provisioner.service.ProvisionerApi;
import com.batiaev.provisioner.service.Sha256Hasher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.security.NoSuchAlgorithmException;

@SpringBootApplication
public class ProvisionerApplication {

    public static void main(String[] args) {
        SpringApplication.run(ProvisionerApplication.class, args);
    }

//    @Bean
//    ProvisionerApi provisioner(@Value("${provisioning.size}") int provisioningSize,
//                               @Autowired VMRepository vmRepository) {
//        return new DelayedProvisioner(provisioningSize, vmRepository);
//    }

    @Bean
    ProvisionerApi provisioner(@Autowired VMRepository vmRepository) {
        return new FastProvisioner(vmRepository);
    }

    @Bean
    Hasher hasher() {
        return new Sha256Hasher();
    }
}
