package com.fintech.loanManagement.services;

import com.fintech.loanManagement.entity.Loan;
import com.fintech.loanManagement.request.CreateLoanRequest;
import com.fintech.loanManagement.response.LoanResponse;

import java.util.List;

public interface LoanService {
    LoanResponse applyForLoan(CreateLoanRequest dto);

    LoanResponse getLoanById(Long id);

    List<Loan> getLoansForUser(Long customerId);
}
