package com.fintech.loanManagement.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;


@Entity
@Table(name = "loanDetails")
@Data
public class Loan {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long loanId;
    private Long userId;
    private BigDecimal amount;
    private int termInMonths;
    private BigDecimal interestRate;
    private Date startDate;
    private Date endDate;
    private String status;
    private BigDecimal remainingAmount;
}
