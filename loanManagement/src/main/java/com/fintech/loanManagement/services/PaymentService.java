package com.fintech.loanManagement.services;

import com.fintech.loanManagement.entity.PaymentDetails;
import com.fintech.loanManagement.request.PaymentRequest;

public interface PaymentService {
    PaymentDetails makePayment(Long loanId, PaymentRequest request);
}
