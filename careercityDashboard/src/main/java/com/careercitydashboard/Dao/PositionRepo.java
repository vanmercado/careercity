package com.careercitydashboard.Dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.careercitydashboard.Model.Position;

public interface PositionRepo extends JpaRepository<Position, Integer> {

}
