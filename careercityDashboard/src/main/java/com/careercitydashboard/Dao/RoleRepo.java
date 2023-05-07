package com.careercitydashboard.Dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.careercitydashboard.Model.Role;

@Repository
public interface RoleRepo extends JpaRepository<Role, Integer> {

}
