package com.careercitydashboard.Service;

import java.util.List;

import com.careercitydashboard.Model.AccountHistory;

public interface AccountHistoryService {

    public List<AccountHistory> getAllAccountHistory();

    public List<AccountHistory> getAccountHistoryByAccountId(Integer userId);

    public AccountHistory saveAccountSession(AccountHistory accountHistory);

}
