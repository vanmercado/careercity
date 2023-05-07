package com.telusinternational.careercity.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.telusinternational.careercity.dao.UserHistoryRepo;
import com.telusinternational.careercity.model.UserHistory;

@Service
@Transactional
public class UserHistoryServiceImpl {

    @Autowired
    private UserHistoryRepo userHistoryRepo;

    public List<UserHistory> getAllUserHistory() {

        return (List<UserHistory>) this.userHistoryRepo.findAllUserHistoriesByLatest();

    }

    public List<UserHistory> getUserHistoryByUserId(Integer userId) {

        return this.userHistoryRepo.findUserHistoriesById(userId);

    }

    public UserHistory saveUserSession(UserHistory userHistory) {

        return this.userHistoryRepo.save(userHistory);

    }

}
