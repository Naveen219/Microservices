package com.nerdyprogrammer.loanservice.repo;

import com.nerdyprogrammer.loanservice.entity.Loan;
import org.hibernate.sql.ast.tree.expression.JdbcParameter;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * @author Naveen Kumar
 * @Date 5/28/2025
 */
@Repository
public interface LoanRepo extends JpaRepository<Loan, Long> {

    Optional<Loan> findByMobileNumber(String mobileNumber);

    Optional<Loan> findByLoanNumber(String loanNumber);

}
