package com.nerdyprogrammer.loanservice.service.impl;

import com.nerdyprogrammer.loanservice.constants.LoanConstants;
import com.nerdyprogrammer.loanservice.dto.LoanDto;
import com.nerdyprogrammer.loanservice.entity.Loan;
import com.nerdyprogrammer.loanservice.exception.LoanAlreadyExistsException;
import com.nerdyprogrammer.loanservice.exception.ResourceNotFoundException;
import com.nerdyprogrammer.loanservice.mapper.LoanMapper;
import com.nerdyprogrammer.loanservice.repo.LoanRepo;
import com.nerdyprogrammer.loanservice.service.ILoanService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Random;

/**
 * @author Naveen Kumar
 * @Date 5/28/2025
 */
@Service
@RequiredArgsConstructor
public class LoanServiceImpl implements ILoanService {


    private LoanRepo loanRepo;

    /**
     * @param mobileNumber - Mobile Number of the Customer
     */
    @Override
    public void createLoan(String mobileNumber) {
        Optional<Loan> optionalLoan = loanRepo.findByMobileNumber(mobileNumber);
        if (optionalLoan.isPresent()) {
            throw new LoanAlreadyExistsException("Loan already registered with given mobileNumber " + mobileNumber);
        }
        loanRepo.save(createNewLoan(mobileNumber));
    }

    /**
     * @param mobileNumber - Mobile Number of the Customer
     * @return the new loan details
     */
    private Loan createNewLoan(String mobileNumber) {
        Loan newLoan = new Loan();
        long randomLoanNumber = 100000000000L + new Random().nextInt(900000000);
        newLoan.setLoanNumber(Long.toString(randomLoanNumber));
        newLoan.setMobileNumber(mobileNumber);
        newLoan.setLoanType(LoanConstants.HOME_LOAN);
        newLoan.setTotalLoanAmount(LoanConstants.NEW_LOAN_LIMIT);
        newLoan.setAmountPaid(0);
        newLoan.setOutstandingAmount(LoanConstants.NEW_LOAN_LIMIT);
        return newLoan;
    }

    /**
     * @param mobileNumber - Input mobile Number
     * @return Loan Details based on a given mobileNumber
     */
    @Override
    public LoanDto getLoanDetails(String mobileNumber) {
        Loan Loan = loanRepo.findByMobileNumber(mobileNumber).orElseThrow(
                () -> new ResourceNotFoundException("Loan", "mobileNumber", mobileNumber)
        );
        return LoanMapper.mapToLoanDto(Loan, new LoanDto());
    }

    /**
     * @param LoanDto - LoanDto Object
     * @return boolean indicating if the update of loan details is successful or not
     */
    @Override
    public boolean updateLoan(LoanDto LoanDto) {
        Loan Loan = loanRepo.findByLoanNumber(LoanDto.getLoanNumber()).orElseThrow(
                () -> new ResourceNotFoundException("Loan", "LoanNumber", LoanDto.getLoanNumber()));
        LoanMapper.mapToLoan(LoanDto, Loan);
        loanRepo.save(Loan);
        return true;
    }

    /**
     * @param mobileNumber - Input MobileNumber
     * @return boolean indicating if the delete of loan details is successful or not
     */
    @Override
    public boolean deleteLoan(String mobileNumber) {
        Loan Loan = loanRepo.findByMobileNumber(mobileNumber).orElseThrow(
                () -> new ResourceNotFoundException("Loan", "mobileNumber", mobileNumber)
        );
        loanRepo.deleteById(Loan.getLoanId());
        return true;
    }
}
