/**
 * 
 */
package com.telusinternational.careercity.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.telusinternational.careercity.model.Users;
import com.telusinternational.careercity.model.tm.TMLogs;

/**
 * @author A.C.Estrada Jr. x209486
 * @date May 5, 2020
 */
@Repository
public interface TMLogsRepository extends JpaRepository<TMLogs, Integer> {

    public boolean existsByUsers(Users user);

    @Query("SELECT COUNT(tm) FROM TMLogs tm WHERE tm.users.USER_ID = :users_id")
    public Integer countUserExistence(@Param("users_id") Integer user);

}
