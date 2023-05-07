package com.careercitydashboard.Dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.careercitydashboard.Model.Questions;

@Repository
public interface QuestionsRepo extends JpaRepository<Questions, Integer> {

}
