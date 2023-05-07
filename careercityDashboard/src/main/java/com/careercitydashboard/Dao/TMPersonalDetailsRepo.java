package com.careercitydashboard.Dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.careercitydashboard.Model.TMPersonalDetails;

@Repository
public interface TMPersonalDetailsRepo extends JpaRepository<TMPersonalDetails, Integer> {

    @Query(value = "{call findTmPersonalDetailsByEmpId(:param1)}", nativeQuery = true)
    public TMPersonalDetails findTmPersonalDetailsByEmpId(@Param("param1") String empId);

    @Query(value = "SELECT * FROM tm_personal_details WHERE tm_personal_details_id = ?", nativeQuery = true)
    public TMPersonalDetails findByPersonalDetailsId(Integer tmPersonalDetailsId);

}
