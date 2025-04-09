package com.fintech.loanManagement.repository;

import com.fintech.loanManagement.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepo extends JpaRepository<User, Long> {}
