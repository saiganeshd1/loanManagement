package com.fintech.loanManagement;

import com.fintech.loanManagement.entity.Loan;
import com.fintech.loanManagement.entity.User;
import com.fintech.loanManagement.repository.LoanRepo;
import com.fintech.loanManagement.repository.UserRepo;
import com.fintech.loanManagement.request.CreateLoanRequest;
import com.fintech.loanManagement.response.LoanResponse;
import com.fintech.loanManagement.services.Impl.LoanServiceImpl;
import com.fintech.loanManagement.services.LoanService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.Optional;

import static org.hibernate.validator.internal.util.Contracts.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class LoanServiceTest {

    @Mock
    private LoanRepo loanRepo;

    @Mock
    private UserRepo userRepo;

    @Spy
    @InjectMocks
    private LoanServiceImpl loanService;

    @Test
    void applyLoan_shouldReturnSavedLoan() {
        CreateLoanRequest request = new CreateLoanRequest();
        request.setUserId(1L);
        request.setAmount(new BigDecimal("10000"));
        request.setTermInMonths(12);
        request.setInterestRate(new BigDecimal("10"));

        User user = new User();
        user.setId(1L);

        Loan loan = new Loan();
        loan.setLoanId(1L);
        loan.setUserId(user.getId());

        when(userRepo.findById(1L)).thenReturn(Optional.of(user));
        when(loanRepo.save(any(Loan.class))).thenReturn(loan);

        LoanResponse result = loanService.applyForLoan(request);

        assertNotNull(result);
        assertEquals(loan.getLoanId(), result.getLoanId());
    }

    @Test
    void applyLoan_shouldThrowExceptionForMissingCustomer() {
        CreateLoanRequest dto = new CreateLoanRequest();
        dto.setUserId(1L);

        when(userRepo.findById(1L)).thenReturn(Optional.empty());

        assertThrows(RuntimeException.class, () -> loanService.applyForLoan(dto));
    }
}