package com.fintech.loanManagement.repository;

import com.fintech.loanManagement.entity.Loan;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface LoanRepo extends JpaRepository<Loan, Long> {

    List<Loan> findByUserId(Long userId);
}
