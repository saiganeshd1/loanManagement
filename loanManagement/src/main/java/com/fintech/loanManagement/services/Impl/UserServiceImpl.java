package com.fintech.loanManagement.services.Impl;

import com.fintech.loanManagement.entity.User;
import com.fintech.loanManagement.repository.UserRepo;
import com.fintech.loanManagement.request.CreateUserRequest;
import com.fintech.loanManagement.services.UserService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepo userRepo;

    @Override
    public User createUser(CreateUserRequest request) {
        User user = new User();
        BeanUtils.copyProperties(request, user);
        return userRepo.save(user);
    }
}
