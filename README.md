[![Run in Postman](https://run.pstmn.io/button.svg)](https://god.gw.postman.com/run-collection/19595341-a0c0b8b2-f1de-44e2-9ec3-bbf19e68214f?action=collection%2Ffork&collection-url=entityId%3D19595341-a0c0b8b2-f1de-44e2-9ec3-bbf19e68214f%26entityType%3Dcollection%26workspaceId%3Dcbb5f2a7-665d-41f3-830c-ac8fdd9986e3)

[![Build](https://github.com/batiaev/provisioner/actions/workflows/gradle.yml/badge.svg?branch=main)](https://github.com/batiaev/provisioner/actions/workflows/gradle.yml)

# Provisioner

## Build (gradle)
`./gradlew clean build`

## Run
`./gradlew bootRun`

# API
1. `GET /api/v1/users/sign-in` API for user signup - Fields: Name, email-address, mobile no, password, role. Email and mobile no, role must be unique. Role can be master and non-master. Master accounts can view details of all user VMs while the non-master accounts can view only their own vm details. Master account can delete other user accounts.
   - `UserControllerTest#should_signUp`
2. `GET /api/v1/users/sign-in` API to obtain JWT token based on credentials
    - `UserControllerTest#should_signIn`
3. `POST /api/v1/vm/provision` API for Requesting VM provisioning with following details: OS, RAM, Hard-disk and CPU cores.
   - `VMControllerTest#should_provision`
4. `GET /api/v1/vm?userId={id}` API for displaying list of VMs requested by particular user.
    - `VMControllerTest#should_getAllVMForUser`
5. `GET /api/v1/vm?limit={n}` API to list the top 'n' VMs by Memory for logged in user
    - `VMControllerTest#should_getTopNVmByMemForCurrentUser`
6. `DELETE /api/v1/users/{userId}` API to delete the user account. If account gets deleted, all registered vms must be deleted also
   - `UserControllerTest#should_deleteUser`
7. `GET /api/v1/vm?limit={n}&all=true` API to list the top 'n' VMs by memory across all users in system
   - `VMControllerTest#should_getTopNVmByMemInTheSystem`

## Author
Anton Batiaev <anton@batiaev.com>

