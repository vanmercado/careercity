package com.careercitydashboard.ServiceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.careercitydashboard.Dao.AccountHistoryRepo;
import com.careercitydashboard.Model.AccountHistory;
import com.careercitydashboard.Service.AccountHistoryService;

@Service
@Transactional
public class AccountHistoryServiceImpl implements AccountHistoryService {

    @Autowired
    private AccountHistoryRepo accountHistoryRepo;

    @Override
    public List<AccountHistory> getAllAccountHistory() {

        return (List<AccountHistory>) this.accountHistoryRepo.findAllAccountHistoriesByLatest();

    }

    @Override
    public List<AccountHistory> getAccountHistoryByAccountId(Integer accountId) {

        return this.accountHistoryRepo.findAccountHistoriesById(accountId);

    }

    @Override
    public AccountHistory saveAccountSession(AccountHistory accountHistory) {

        return this.accountHistoryRepo.save(accountHistory);

    }

}
