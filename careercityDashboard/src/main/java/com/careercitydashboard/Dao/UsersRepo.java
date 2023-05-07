package com.careercitydashboard.Dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import com.careercitydashboard.Model.Users;
import org.springframework.data.repository.query.Param;

@Repository
public interface UsersRepo extends JpaRepository<Users, Integer> {

    @Query(value = "{call getAllUsersWithAdminRole()}", nativeQuery = true)
    public List<Users> findAllUsersWithAdminRole();

    @Query(value = "{call getAllUsersWithUserRole()}", nativeQuery = true)
    public List<Users> findAllUsersWithUserRole();

    @Query(value = "{call findUserByEmpId(:param1)}", nativeQuery = true)
    public Users findUserByEmpId(@Param("param1") String wdId);

    @Query(value = "{call findUserByEmail(:param1)}", nativeQuery = true)
    public Users findUserByEmail(@Param("param1") String email);

    @Query(value = "SELECT * FROM users WHERE USER_EMPID = ?", nativeQuery = true)
    public Users findEmpId(String EmpId);

}
