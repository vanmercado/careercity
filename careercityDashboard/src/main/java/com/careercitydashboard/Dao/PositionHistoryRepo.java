package com.careercitydashboard.Dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import com.careercitydashboard.Model.PositionHistory;

@Repository
public interface PositionHistoryRepo extends JpaRepository<PositionHistory, Integer> {

    @Query("SELECT a FROM PositionHistory a WHERE a.position.POSITION_ID = ?1 ORDER BY a.timestamp DESC")
    public List<PositionHistory> findPositiontHistoryByPositionId(Integer userId);

    @Query(value = "{call viewAllPositionHistories()}", nativeQuery = true)
    public List<PositionHistory> findAllPositionHistoriesByLatest();

    @Query(value = "{call viewAllPositionHistoriesById(:param1)}", nativeQuery = true)
    public List<PositionHistory> findPositionHistoriesById(@Param("param1") int id);

}
