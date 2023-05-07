package com.careercitydashboard.Service;

import java.util.List;

import com.careercitydashboard.Model.Account;

public interface AccountService {

    public List<Account> getallAccounts();

    public Account getaccountbyId(Integer Id);

    public void saveAccount(Account account);

    public void updateAccount(Account account);

    public Account getAccountMapping(Integer ACCOUNT_ID);

    public Account saveImage(Account account);

    public Account saveUpdateAccount(Account account);

    public void syncJobReqAccounts();

}
