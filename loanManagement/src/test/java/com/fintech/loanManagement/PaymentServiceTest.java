package com.fintech.loanManagement;


import com.fintech.loanManagement.entity.Loan;
import com.fintech.loanManagement.entity.PaymentDetails;
import com.fintech.loanManagement.repository.LoanRepo;
import com.fintech.loanManagement.repository.PaymentRepo;
import com.fintech.loanManagement.request.PaymentRequest;
import com.fintech.loanManagement.services.Impl.PaymentServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class RepaymentServiceTest {

    @Mock
    private LoanRepo loanRepository;

    @Mock
    private PaymentRepo paymentRepo;

    @Spy
    @InjectMocks
    private PaymentServiceImpl paymentService;

    @Test
    void makeRepayment_shouldReturnSavedRepayment() {
        PaymentRequest request = new PaymentRequest();
        request.setAmount(new BigDecimal("10000"));
        request.setPaymentStatus("COMPLETED");

        Loan loan = new Loan();
        loan.setLoanId(1L);
        loan.setRemainingAmount(new BigDecimal("5000"));

        PaymentDetails paymentDetails = new PaymentDetails();
        paymentDetails.setId(1L);

        when(loanRepository.findById(1L)).thenReturn(Optional.of(loan));
        when(paymentRepo.save(any(PaymentDetails.class))).thenReturn(paymentDetails);

        PaymentDetails result = paymentService.makePayment(1L,request);

        assertNotNull(result);
        assertEquals(paymentDetails.getId(), result.getId());
    }

    @Test
    void makeRepayment_shouldThrowExceptionForInvalidLoan() {
        PaymentRequest request = new PaymentRequest();
        request.setAmount(new BigDecimal("500"));

        when(loanRepository.findById(2L)).thenReturn(Optional.empty());

        assertThrows(RuntimeException.class, () -> paymentService.makePayment(2L,request));
    }
}