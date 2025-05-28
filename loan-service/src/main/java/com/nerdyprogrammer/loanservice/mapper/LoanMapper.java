package com.nerdyprogrammer.loanservice.mapper;

import com.nerdyprogrammer.loanservice.dto.LoanDto;
import com.nerdyprogrammer.loanservice.entity.Loan;

/**
 * @author Naveen Kumar
 * @Date 5/28/2025
 */
public class LoanMapper {
    public static LoanDto mapToLoanDto(Loan loan, LoanDto loanDto) {
        loanDto.setLoanNumber(loan.getLoanNumber());
        loanDto.setLoanType(loan.getLoanType());
        loanDto.setMobileNumber(loan.getMobileNumber());
        loanDto.setTotalLoanAmount(loan.getTotalLoanAmount());
        loanDto.setAmountPaid(loan.getAmountPaid());
        loanDto.setOutstandingAmount(loan.getOutstandingAmount());
        return loanDto;
    }
    public static Loan mapToLoan(LoanDto loansDto, Loan loan) {
        loan.setLoanNumber(loansDto.getLoanNumber());
        loan.setLoanType(loansDto.getLoanType());
        loan.setMobileNumber(loansDto.getMobileNumber());
        loan.setAmountPaid(loansDto.getTotalLoanAmount());
        loan.setAmountPaid(loansDto.getAmountPaid());
        loan.setOutstandingAmount(loansDto.getOutstandingAmount());
        return loan;
    }
}
