package com.careercitydashboard.Dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.careercitydashboard.Model.UserHistory;

@Repository
public interface UserHistoryRepo extends JpaRepository<UserHistory, Integer> {

    @Query("SELECT a FROM PositionHistory a WHERE a.position.POSITION_ID = ?1 ORDER BY a.timestamp DESC")
    public List<UserHistory> findUsertHistoryByUserId(Integer userId);

    @Query(value = "{call viewAllUserHistories()}", nativeQuery = true)
    public List<UserHistory> findAllUserHistoriesByLatest();

    @Query(value = "{call viewAllUserHistoriesById(:param1)}", nativeQuery = true)
    public List<UserHistory> findUserHistoriesById(@Param("param1") int id);

}
