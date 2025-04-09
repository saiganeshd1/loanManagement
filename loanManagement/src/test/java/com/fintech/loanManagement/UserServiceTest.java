package com.fintech.loanManagement;

import com.fintech.loanManagement.entity.User;
import com.fintech.loanManagement.repository.UserRepo;
import com.fintech.loanManagement.request.CreateUserRequest;
import com.fintech.loanManagement.services.Impl.UserServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class UserServiceTest {
    @Mock
    private UserRepo userRepo;

    @Spy
    @InjectMocks
    private UserServiceImpl userService;

    @Test
    void createCustomer_shouldReturnSavedCustomer() {
        CreateUserRequest request = new CreateUserRequest();
        request.setName("John Doe");
        request.setEmail("john@example.com");
        request.setPhone("9876543210");
        request.setDateOfBirth(new Date());
        request.setAadhaarNo("123456789012");
        request.setPanNo("ABCDE1234F");

        User savedCustomer = new User();
        savedCustomer.setId(1L);
        savedCustomer.setName(request.getName());

        User mappedUser = new User();
        mappedUser.setName("Test User");
        mappedUser.setEmail("test@example.com");

        when(userRepo.save(any(User.class))).thenReturn(savedCustomer);

        User result = userService.createUser(request);

        assertNotNull(result);
        assertEquals("John Doe", result.getName());
    }

}
