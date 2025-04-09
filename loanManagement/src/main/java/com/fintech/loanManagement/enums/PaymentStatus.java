package com.fintech.loanManagement.enums;

import lombok.Getter;

@Getter
public enum PaymentStatus {
    PENDING("PENDING"),
    COMPLETED("COMPLETED"),
    FAILED("FAILED");

    private final String status;

    PaymentStatus(String status) {
        this.status = status;
    }


}
