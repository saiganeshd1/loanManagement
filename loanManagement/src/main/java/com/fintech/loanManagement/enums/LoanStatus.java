package com.fintech.loanManagement.enums;

import lombok.Getter;

@Getter
public enum LoanStatus {
    PENDING("pending"),
    APPROVED("approved"),
    REJECTED("rejected"),
    CLOSED("closed");

    private final String status;

    LoanStatus(String status) {
        this.status = status;
    }
}
