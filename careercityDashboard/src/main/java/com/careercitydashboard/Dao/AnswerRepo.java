package com.careercitydashboard.Dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import com.careercitydashboard.Model.Answer;

@Repository
public interface AnswerRepo extends JpaRepository<Answer, Integer> {

    @Query(value = "{call getAnswerIdByAnswer(:param1)}", nativeQuery = true)
    public Integer getAnswerIdByAnswer(@Param("param1") String answer);

}
