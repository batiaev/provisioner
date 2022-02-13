# VM Provisioner

Backend Java code challenge (Spring/Hibernate/Any Database)

## Code Exercise:
Build a REST server where users can sign-up and can request for VM provisioning.

## Technical requirements:
- Expose REST endpoints with a Spring boot application
- Use postgres as the database

## Application Requirement:

- API for user signup - Fields: Name, email-address, mobile no, password, role. Email and mobile no, role must be unique.
  Role can be master and non-master. Master accounts can view details of all user VMs while the non-master accounts can view only their own vm details. Master account can delete other user accounts.
- API to obtain JWT token based on credentials
- API for Requesting VM provisioning with following details: OS, RAM, Hard-disk and CPU cores.
- API for displaying list of VMs requested by particular user.
- API to list the top 'n' VMs by Memory for logged in user
- API to delete the user account. If account gets deleted, all registered vms must be deleted also
- API to list the top 'n' VMs by memory across all users in system
- Raise Proper Exception and generate logs if API fails to execute (may be invalid input or checks for numeric / alpha value)
- Every API call must be authenticated using the JWT token in the request header. (except sign-up API)
- Prepare Postman collection for testing REST end-points above.
