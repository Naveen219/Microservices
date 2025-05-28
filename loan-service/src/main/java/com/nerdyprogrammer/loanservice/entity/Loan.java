package com.nerdyprogrammer.loanservice.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

/**
 * @author Naveen Kumar
 * @Date 5/28/2025
 */
@Entity
@Data
public class Loan extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long loanId;

    private String mobileNumber;

    private String loanNumber;

    private String loanType;

    private int totalLoanAmount;

    private int amountPaid;

    private int outstandingAmount;
}
