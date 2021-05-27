package com.WebDatabaseApplication.repository;

import com.WebDatabaseApplication.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepo  extends JpaRepository<User, Long>{
    User findByUsername(String username);
}

