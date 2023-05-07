package com.careercitydashboard.Dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.careercitydashboard.Model.Users;

@Repository
public interface UsersDetailsRepo extends JpaRepository<Users, Integer> {

    public Users findByUsername(String username);

    public Users findByFirstname(String firstname);

    public Users findByLastname(String lastname);

}
