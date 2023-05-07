package com.careercitydashboard.Service;

import java.util.List;

import com.careercitydashboard.Model.UserHistory;

public interface UserHistoryService {

    public List<UserHistory> getAllUserHistory();

    public List<UserHistory> getUserHistoryByUserId(Integer userId);

    public UserHistory saveUserSession(UserHistory userHistory);

}
