package com.careercitydashboard.Dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.careercitydashboard.Model.OpenJobReq;

@Repository
public interface OpenJobReqRepo extends JpaRepository<OpenJobReq, Integer> {

}
