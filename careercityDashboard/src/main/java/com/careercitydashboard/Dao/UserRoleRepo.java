package com.careercitydashboard.Dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.careercitydashboard.Model.UserRole;

public interface UserRoleRepo extends JpaRepository<UserRole, Integer> {

}
