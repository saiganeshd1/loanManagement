package com.fintech.loanManagement.exceptions;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class LoanException extends RuntimeException {

    private final boolean isSuccess;
    private final String message;
    private final HttpStatus httpStatus;

    public LoanException(boolean isSuccess, String statusMsg) {
        this.isSuccess = isSuccess;
        this.message = statusMsg;
        this.httpStatus = HttpStatus.OK;
    }

    public LoanException(boolean isSuccess, String statusMsg, HttpStatus httpStatus) {
        this.isSuccess = isSuccess;
        this.message = statusMsg;
        this.httpStatus = httpStatus;
    }
}
