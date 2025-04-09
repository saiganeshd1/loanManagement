package com.fintech.loanManagement.controller;

import com.fintech.loanManagement.entity.Loan;
import com.fintech.loanManagement.entity.PaymentDetails;
import com.fintech.loanManagement.request.CreateLoanRequest;
import com.fintech.loanManagement.request.PaymentRequest;
import com.fintech.loanManagement.response.LoanResponse;
import com.fintech.loanManagement.services.LoanService;
import com.fintech.loanManagement.services.PaymentService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/loans")
public class LoanController {

    @Autowired
    private LoanService loanService;
    @Autowired
    private PaymentService repayService;

    @PostMapping("create")
    public ResponseEntity<LoanResponse> apply(@Valid @RequestBody CreateLoanRequest request) {
        return ResponseEntity.ok(loanService.applyForLoan(request));
    }

    @GetMapping("/{id}")
    public ResponseEntity<LoanResponse> getLoan(@PathVariable Long id) {
        return ResponseEntity.ok(loanService.getLoanById(id));
    }

    @PostMapping("/{id}/payment")
    public ResponseEntity<PaymentDetails> payment(@PathVariable Long id, @RequestBody PaymentRequest request) {
        return ResponseEntity.ok(repayService.makePayment(id, request));
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<Loan>> getLoans(@PathVariable Long userId) {
        return ResponseEntity.ok(loanService.getLoansForUser(userId));
    }

    @GetMapping("/{id}/status")
    public ResponseEntity<String> getStatus(@PathVariable Long id) {
        return ResponseEntity.ok(loanService.getLoanById(id).getStatus());
    }
}
