package com.fintech.loanManagement.services.Impl;

import com.fintech.loanManagement.entity.Loan;
import com.fintech.loanManagement.entity.PaymentDetails;
import com.fintech.loanManagement.enums.LoanStatus;
import com.fintech.loanManagement.enums.PaymentStatus;
import com.fintech.loanManagement.exceptions.LoanException;
import com.fintech.loanManagement.repository.LoanRepo;
import com.fintech.loanManagement.repository.PaymentRepo;
import com.fintech.loanManagement.request.PaymentRequest;
import com.fintech.loanManagement.services.PaymentService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.UUID;

@Service
public class PaymentServiceImpl implements PaymentService {
    @Autowired
    private LoanRepo loanRepo;

    @Autowired
    private PaymentRepo paymentRepo;

    @Override
    public PaymentDetails makePayment(Long loanId, PaymentRequest request) {
        String orderId = UUID.randomUUID().toString();
        Loan loan = loanRepo.findById(loanId)
                .orElseThrow(() -> new LoanException(false,"No Loan Details found"));

        PaymentDetails paymentDetails = new PaymentDetails();
        BeanUtils.copyProperties(request,paymentDetails);
        paymentDetails.setOrderId(orderId);
        paymentDetails.setLoanId(loanId);

        if (request.getPaymentStatus().equalsIgnoreCase(PaymentStatus.COMPLETED.getStatus())) {
            loan.setRemainingAmount(loan.getRemainingAmount().subtract(request.getAmount()));
            if (loan.getRemainingAmount().compareTo(BigDecimal.ZERO) == 0) {
                loan.setStatus(LoanStatus.CLOSED.getStatus());
            }
            loanRepo.save(loan);
        }
        return paymentRepo.save(paymentDetails);
    }
}
