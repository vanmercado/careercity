package com.careercitydashboard.Dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.careercitydashboard.Model.TMJobDetails;

@Repository
public interface TMJobDetailsRepo extends JpaRepository<TMJobDetails, Integer> {

    @Query(value = "{call getDuplicateTmByWdId(:param1, :param2, :param3)}", nativeQuery = true)
    public TMJobDetails findDuplicateTmByWkId(@Param("param1") String wdId, @Param("param2") String jobTitle,
            @Param("param3") String functionalArea);

    @Query(value = "{call findExistingWdWithDifferentJobTitleOrFuncArea(:param1, :param2, :param3)}", nativeQuery = true)
    public TMJobDetails findExistingWdWithDifferentJobTitleOrFuncArea(@Param("param1") String wdId,
            @Param("param2") String jobTitle, @Param("param3") String functionalArea);

    @Query(value = "{call findLatestJobDetailsByEmpId(:param1)}", nativeQuery = true)
    public TMJobDetails findLatestJobDetailsByEmpId(@Param("param1") String wdId);

    @Query(value = "SELECT * FROM tm_job_details WHERE employee_id = ? LIMIT 1", nativeQuery = true)
    public TMJobDetails findByWdId(String wdId);

}
