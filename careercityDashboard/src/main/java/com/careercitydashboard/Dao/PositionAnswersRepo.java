package com.careercitydashboard.Dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.careercitydashboard.Model.PositionAnswers;

@Repository
public interface PositionAnswersRepo extends JpaRepository<PositionAnswers, Integer> {

}
