package com.nerdyprogrammer.audit;

import org.springframework.data.domain.AuditorAware;

import java.util.Optional;

public class AuditAwareImpl implements AuditorAware<String> {

    // This class is used to implement the AuditorAware interface
    // and provide the current user for auditing purposes.
    // You can implement the logic to get the current user from the security context or any other source.
    // For example, you can use Spring Security to get the current user.

    @Override
    public Optional<String> getCurrentAuditor() {
        return Optional.of("card-service"); // Replace with actual logic to get the current user
    }


}
