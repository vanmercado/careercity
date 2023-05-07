package com.careercitydashboard.Dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.careercitydashboard.Model.AccountHistory;

@Repository
public interface AccountHistoryRepo extends JpaRepository<AccountHistory, Integer> {

    @Query("SELECT a FROM AccountHistory a WHERE a.account.ACCOUNT_ID = ?1 ORDER BY a.timestamp DESC")
    public List<AccountHistory> findAccountHistoryByAccountId(Integer userId);

    @Query(value = "{call viewAllAccountHistories()}", nativeQuery = true)
    public List<AccountHistory> findAllAccountHistoriesByLatest();

    @Query(value = "{call viewAllAccountHistoriesById(:param1)}", nativeQuery = true)
    public List<AccountHistory> findAccountHistoriesById(@Param("param1") int id);

}
