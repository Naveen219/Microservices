package com.nerdyprogrammer.loanservice.audit;

import org.springframework.data.domain.AuditorAware;

import java.util.Optional;

/**
 * @author Naveen Kumar
 * @Date 5/28/2025
 */
public class AuditAwareImpl implements AuditorAware<String> {
    /**
     * Returns the current auditor's username.
     * @return
     */
    @Override
    public Optional<String> getCurrentAuditor() {
        return Optional.of("loan-service"); // Replace with actual logic to get the current user
    }
}
