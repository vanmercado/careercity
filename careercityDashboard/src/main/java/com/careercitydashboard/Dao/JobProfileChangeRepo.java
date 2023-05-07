package com.careercitydashboard.Dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import com.careercitydashboard.Model.JobProfileChange;

@Repository
public interface JobProfileChangeRepo extends JpaRepository<JobProfileChange, Integer> {

    @Query(value = "SELECT * FROM tm_job_details j WHERE j.employee_id = ?", nativeQuery = true)
    public List<Object[]> userExists(String wdId);

}
