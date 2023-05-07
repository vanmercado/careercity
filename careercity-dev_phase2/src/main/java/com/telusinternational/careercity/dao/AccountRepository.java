package com.telusinternational.careercity.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.telusinternational.careercity.model.Account;

@Repository
public interface AccountRepository extends CrudRepository<Account, Integer> {

    @Query("from Account l WHERE l.LOCATION = :LOCATION")
    List<Account> findAllByLocation(@Param("LOCATION") String LOCATION);

    @Query("from Account l WHERE l.LOCATION = :LOCATION and l.LOB = 'voice'")
    List<Account> findAllVoiceAccountsByLocation(@Param("LOCATION") String LOCATION);

    @Query("from Account l WHERE l.CATEGORY = :account ORDER BY l.ACCOUNT_NAME ASC")
    List<Account> findAccount(@Param("account") String account);

}
