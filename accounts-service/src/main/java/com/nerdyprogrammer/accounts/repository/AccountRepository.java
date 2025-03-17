package com.nerdyprogrammer.accounts.repository;

import com.nerdyprogrammer.accounts.entity.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Repository
public interface AccountRepository extends JpaRepository<Account, Long> {

    Optional<Account> findByCustomerId(Long customerId);

    // modifying annotation indicates that the method is modifying the record
    // transactional annotation indicates that the method is atomic. In other words,
    // no partial executions are allowed
    @Transactional
    @Modifying
    void deleteByCustomerId(Long customerId);
}
