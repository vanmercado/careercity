package com.careercitydashboard.ServiceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.careercitydashboard.Dao.UserHistoryRepo;
import com.careercitydashboard.Model.UserHistory;
import com.careercitydashboard.Service.UserHistoryService;

@Service
@Transactional
public class UserHistoryServiceImpl implements UserHistoryService {

    @Autowired
    private UserHistoryRepo userHistoryRepo;

    @Override
    public List<UserHistory> getAllUserHistory() {

        return (List<UserHistory>) this.userHistoryRepo.findAllUserHistoriesByLatest();

    }

    @Override
    public List<UserHistory> getUserHistoryByUserId(Integer userId) {

        return this.userHistoryRepo.findUserHistoriesById(userId);

    }

    @Override
    public UserHistory saveUserSession(UserHistory userHistory) {

        return this.userHistoryRepo.save(userHistory);

    }

}
