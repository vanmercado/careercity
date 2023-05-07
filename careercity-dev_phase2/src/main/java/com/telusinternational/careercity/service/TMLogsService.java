/**
 * 
 */
package com.telusinternational.careercity.service;

import java.sql.Timestamp;
import java.util.Date;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
/*import org.springframework.beans.factory.annotation.Value;*/
import org.springframework.stereotype.Service;

import com.telusinternational.careercity.dao.TMLogsRepository;
import com.telusinternational.careercity.dao.UsersRepository;
/*import com.telusinternational.careercity.enums.UsedUsername;*/
import com.telusinternational.careercity.model.Users;
import com.telusinternational.careercity.model.tm.TMLogs;

/**
 * @author A.C.Estrada Jr. x209486
 * @date May 5, 2020
 */
@Service
@Transactional
public class TMLogsService {

    @Autowired
    private TMLogsRepository tmLogsRepository;

    @Autowired
    private UsersRepository usersRepository;

    public void saveLogRepository(String username) {

        // Fetch user by user ID
        Users user = this.usersRepository.findByEmail(username);

        // Create entity
        TMLogs tmLogs = new TMLogs();
        tmLogs.setTimestamp(new Timestamp(new Date().getTime()));
        tmLogs.setUsers(user);
        tmLogs.setUsedUsername("EMAIL");

        // Save
        this.tmLogsRepository.save(tmLogs);

    }

    public boolean isUserExistInLogs(Users user) {

        return this.tmLogsRepository.existsByUsers(user);

    }

    public Integer countUserExistence(Users user) {

        return this.tmLogsRepository.countUserExistence(user.getUSER_ID());

    }

}
