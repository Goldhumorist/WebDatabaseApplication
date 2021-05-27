package com.WebDatabaseApplication.repository;

import com.WebDatabaseApplication.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepo extends JpaRepository<Role,Long> {
}
