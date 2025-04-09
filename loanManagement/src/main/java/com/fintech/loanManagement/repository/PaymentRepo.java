package com.fintech.loanManagement.repository;


import com.fintech.loanManagement.entity.PaymentDetails;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PaymentRepo extends JpaRepository<PaymentDetails, Long> {
}

