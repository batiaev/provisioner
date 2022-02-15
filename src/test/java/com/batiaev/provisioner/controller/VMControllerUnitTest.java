package com.batiaev.provisioner.controller;

import com.batiaev.provisioner.data.TestData;
import org.junit.jupiter.api.Test;

class VMControllerUnitTest implements TestData {

    /**
     * POST /api/v1/vm/provision
     * req.2 API for Requesting VM provisioning with following details: OS, RAM, Hard-disk and CPU cores.
     */
    @Test
    void should_provision() {
    }

    /**
     * GET /api/v1/vm?userId={id}
     * req.3 API for displaying list of VMs requested by particular user.
     */
    @Test
    void should_getAllVMForUser() {
    }
    /**
     * GET /api/v1/vm?limit={n}
     * req.4 API to list the top 'n' VMs by Memory for logged in user
     */
    @Test
    void should_getTopNVmByMemForCurrentUser() {
    }
    /**
     * GET /api/v1/vm?limit={n}&all=true
     * req.6 API to list the top 'n' VMs by memory across all users in system
     */
    @Test
    void should_getTopNVmByMemInTheSystem() {
    }
}