package com.telusinternational.careercity.dao;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.telusinternational.careercity.model.Answer;

@Repository
@Transactional
public interface AnswerRepository extends JpaRepository<Answer, Integer> {

    @Query("from Answer a WHERE a.QUESTION_ID = :questionId")
    List<Answer> findByQuestionId(@Param("questionId") Integer question_id);

}
