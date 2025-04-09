package com.fintech.loanManagement.services;

import com.fintech.loanManagement.entity.User;
import com.fintech.loanManagement.request.CreateUserRequest;

public interface UserService {
    User createUser(CreateUserRequest request);
}
