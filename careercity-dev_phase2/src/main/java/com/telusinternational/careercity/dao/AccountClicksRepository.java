package com.telusinternational.careercity.dao;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.telusinternational.careercity.model.AccountClicks;

@Repository
public interface AccountClicksRepository extends CrudRepository<AccountClicks, Integer> {

    @Query("SELECT COUNT (c) from AccountClicks c WHERE c.ACCOUNT_ID = :ACCOUNT_ID")
    Integer countClicks(@Param("ACCOUNT_ID") Integer ACCOUNT_ID);

}
