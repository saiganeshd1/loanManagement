package com.fintech.loanManagement.response;

import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

@Data
public class LoanResponse {
    public Long loanId;
    public String status;
    public BigDecimal amount;
    public BigDecimal remainingAmount;
    public Date startDate;
    public Date endDate;
}
