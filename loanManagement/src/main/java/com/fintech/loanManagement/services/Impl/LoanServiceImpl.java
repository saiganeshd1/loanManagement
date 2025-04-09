package com.fintech.loanManagement.services.Impl;

import com.fintech.loanManagement.entity.Loan;
import com.fintech.loanManagement.enums.LoanStatus;
import com.fintech.loanManagement.exceptions.LoanException;
import com.fintech.loanManagement.repository.UserRepo;
import com.fintech.loanManagement.repository.LoanRepo;
import com.fintech.loanManagement.request.CreateLoanRequest;
import com.fintech.loanManagement.response.LoanResponse;
import com.fintech.loanManagement.services.LoanService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Service
public class LoanServiceImpl implements LoanService {

    @Autowired
    LoanRepo loanRepo;

    @Autowired
    UserRepo userRepo;

    @Override
    public LoanResponse applyForLoan(CreateLoanRequest dto) {
        userRepo.findById(dto.userId)
                .orElseThrow(() -> new LoanException(false,"User not found"));

        Loan loan = setLoanDetails(dto);
        loan = loanRepo.save(loan);

        LoanResponse res = new LoanResponse();
        BeanUtils.copyProperties(loan,res);
        return res;
    }

    private static Loan setLoanDetails(CreateLoanRequest dto) {
        Date startDate = new Date();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(startDate);
        calendar.add(Calendar.MONTH, dto.getTermInMonths()); // add months
        Date endDate = calendar.getTime();

        Loan loan = new Loan();
        BeanUtils.copyProperties(dto,loan);
        loan.setStartDate(new Date());
        loan.setEndDate(endDate);
        loan.setRemainingAmount(dto.getAmount());
        loan.setStatus(LoanStatus.APPROVED.getStatus());
        return loan;
    }

    @Override
    public LoanResponse getLoanById(Long id) {
        Loan loan = loanRepo.findById(id).orElseThrow(() -> new LoanException(false,"Loan not found"));
        LoanResponse res = new LoanResponse();
        BeanUtils.copyProperties(loan,res);
        return res;
    }

    @Override
    public List<Loan> getLoansForUser(Long userId) {
        return loanRepo.findByUserId(userId);
    }
}
