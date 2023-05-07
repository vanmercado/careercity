package com.telusinternational.careercity.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.telusinternational.careercity.dao.AccountClicksRepository;
import com.telusinternational.careercity.dao.AccountRepository;
import com.telusinternational.careercity.model.Account;
import com.telusinternational.careercity.model.AccountClicks;

@Service
@Transactional
public class AccountService {

    @Autowired
    private AccountRepository accountDao;
    @Autowired
    private AccountClicksRepository clickDao;

    public List<Account> getAllAccounts() {

        return (List<Account>) this.accountDao.findAll();

    }

    public List<Account> getCcoAccount(String account) {

        return (List<Account>) this.accountDao.findAccount(account);

    }

    public Account getAccountById(Integer id) {

        return this.accountDao.findById(id).get();

    }

    public List<Account> getByLocation(String LOCATION) {

        return this.accountDao.findAllByLocation(LOCATION);

    }

    public void saveClickCounts(AccountClicks accountId) {

        this.clickDao.save(accountId);

    }

    public Integer countClicks(Integer accountId) {

        return this.clickDao.countClicks(accountId);

    }

    public List<Account> getVoiceAccountByLocation(String LOCATION) {

        return this.accountDao.findAllVoiceAccountsByLocation(LOCATION);

    }

}
