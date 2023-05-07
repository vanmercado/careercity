package com.careercitydashboard.ServiceImpl;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.ParameterMode;
import javax.persistence.PersistenceContext;
import javax.persistence.StoredProcedureQuery;

import org.hibernate.Hibernate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.careercitydashboard.Controller.CommonController;
import com.careercitydashboard.Dao.AccountRepo;
import com.careercitydashboard.Model.Account;
import com.careercitydashboard.Model.AccountHistory;
import com.careercitydashboard.Service.AccountService;

@Service
@Transactional
public class AccountServiceImpl implements AccountService {

    private final Logger logger = LoggerFactory.getLogger(CommonController.class);

    @Autowired
    private AccountHistoryServiceImpl accountHistory;

    @Autowired
    private AccountRepo accountRepo;

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<Account> getallAccounts() {

        return (List<Account>) this.accountRepo.findAll();

    }

    @Override
    public Account getaccountbyId(Integer Id) {

        return this.accountRepo.findById(Id).get();

    }

    @Override
    public void saveAccount(Account account) {

        this.accountRepo.save(account);

    }

    @Override
    public void updateAccount(Account account) {

        Account saveAccount = getaccountbyId(account.getACCOUNT_ID());
        saveAccount.setACCOUNT_NAME(account.getACCOUNT_NAME());
        saveAccount.setLOCATION(account.getLOCATION());
        saveAccount.setLOB(account.getLOB());
        saveAccount.setBUSINESS_VERTICAL(account.getBUSINESS_VERTICAL());
        saveAccount.setWORK_TYPE(account.getWORK_TYPE());
        saveAccount.setACCOUNT_IMAGE_PATH(account.getACCOUNT_IMAGE_PATH());
        saveAccount.setOPERATING_HOURS(account.getOPERATING_HOURS());
        saveAccount.setEDUCATION(account.getEDUCATION());
        saveAccount.setSKILLS_PREMIUM(account.getSKILLS_PREMIUM());
        saveAccount.setSKILLS(account.getSKILLS());
        saveAccount.setBPO_EXP(account.getBPO_EXP());
        saveAccount.setDESCRIPTION(account.getDESCRIPTION());
        saveAccount.setTM_TASK(account.getTM_TASK());
        saveAccount.setTRAININGS(account.getTRAININGS());
        saveAccount.setWORK_TYPE(account.getWORK_TYPE());
        saveAccount.setCATEGORY(account.getCATEGORY());
        this.accountRepo.save(saveAccount);

    }

    public Account saveUpdateAccount(Account account) {

        try {

            if (account.getACCOUNT_ID() == null || !this.accountRepo.existsById(account.getACCOUNT_ID())) {

                Authentication auth = SecurityContextHolder.getContext().getAuthentication();
                Account savedAccount = this.accountRepo.save(account);
                AccountHistory accountHistory = new AccountHistory();
                accountHistory.setAccount(savedAccount);
                accountHistory.setAccount_hist_msg("Account Created");
                accountHistory.setAccount_hist_user(auth.getName());

                this.accountHistory.saveAccountSession(accountHistory);
                return savedAccount;

            }
            else {

                Account saveAccount = getaccountbyId(account.getACCOUNT_ID());
                saveAccount.setACCOUNT_NAME(account.getACCOUNT_NAME());
                saveAccount.setLOCATION(account.getLOCATION());
                saveAccount.setLOB(account.getLOB());
                saveAccount.setBUSINESS_VERTICAL(account.getBUSINESS_VERTICAL());
                saveAccount.setWORK_TYPE(account.getWORK_TYPE());
                saveAccount.setACCOUNT_IMAGE_PATH(account.getACCOUNT_IMAGE_PATH());
                saveAccount.setOPERATING_HOURS(account.getOPERATING_HOURS());
                saveAccount.setEDUCATION(account.getEDUCATION());
                saveAccount.setSKILLS_PREMIUM(account.getSKILLS_PREMIUM());
                saveAccount.setSKILLS(account.getSKILLS());
                saveAccount.setBPO_EXP(account.getBPO_EXP());
                saveAccount.setDESCRIPTION(account.getDESCRIPTION());
                saveAccount.setTM_TASK(account.getTM_TASK());
                saveAccount.setTRAININGS(account.getTRAININGS());
                saveAccount.setWORKDAY_JOB_PROFILE(account.getWORKDAY_JOB_PROFILE());
                saveAccount.setWORK_TYPE(account.getWORK_TYPE());
                saveAccount.setCATEGORY(account.getCATEGORY());
                saveAccount.setJOB_REQ(account.getJOB_REQ());

                // History
                Authentication auth = SecurityContextHolder.getContext().getAuthentication();
                AccountHistory accountHistory = new AccountHistory();
                accountHistory.setAccount(saveAccount);
                accountHistory.setAccount_hist_msg("Updated Details");
                accountHistory.setAccount_hist_user(auth.getName());
                this.accountHistory.saveAccountSession(accountHistory);
                return this.accountRepo.save(saveAccount);

            }

        }
        catch (Exception e) {

            logger.warn("saveUpdateAccount Exception");
            e.printStackTrace();

        }

        return null;

    }

    @Override
    public Account getAccountMapping(Integer ACCOUNT_ID) {

        Account account = this.accountRepo.findById(ACCOUNT_ID).get();
        Hibernate.initialize(account.getAccountAnswers().size());
        return account;

    }

    @Override
    public Account saveImage(Account account) {

        Account saveImgagePath = getaccountbyId(account.getACCOUNT_ID());
        saveImgagePath.getACCOUNT_IMAGE_PATH();
        return this.accountRepo.save(account);

    }

    @Override
    public void syncJobReqAccounts() {

        List<Account> accountList = this.accountRepo.findAll();

        for (Account a : accountList) {

            if (a.getWORKDAY_JOB_PROFILE() != null) {

                if (a.getWORKDAY_JOB_PROFILE().contains("~")) {

                    String string = a.getWORKDAY_JOB_PROFILE();
                    String[] tags = string.split("~");

                    List<String> jobReqList = new ArrayList<String>();

                    for (String tag : tags) {

                        StoredProcedureQuery query = entityManager
                                .createStoredProcedureQuery("getJobReqIdByProfileName_acct");
                        query.registerStoredProcedureParameter("profileName", String.class, ParameterMode.IN);
                        query.registerStoredProcedureParameter("functionalArea", String.class, ParameterMode.IN);
                        query.registerStoredProcedureParameter("location", String.class, ParameterMode.IN);
                        query.setParameter("profileName", tag);
                        query.setParameter("functionalArea", a.getACCOUNT_NAME());
                        query.setParameter("location", a.getLOCATION());
                        List<Object[]> objectList = query.getResultList();

                        if (objectList.size() == 0) {

                        }
                        else {

                            for (Object[] obj : objectList) {

                                jobReqList.add(obj[3].toString());

                            }

                        }

                    }

                    if (jobReqList.size() == 0) {

                        a.setJOB_REQ("");
                        this.accountRepo.save(a);

                    }
                    else {

                        a.setJOB_REQ(jobReqList.toString().substring(1, jobReqList.toString().length() - 1));
                        this.accountRepo.save(a);

                    }

                }
                else {

                    List<String> jobReqList = new ArrayList<String>();

                    StoredProcedureQuery query = entityManager
                            .createStoredProcedureQuery("getJobReqIdByProfileName_acct");
                    query.registerStoredProcedureParameter("profileName", String.class, ParameterMode.IN);
                    query.registerStoredProcedureParameter("functionalArea", String.class, ParameterMode.IN);
                    query.registerStoredProcedureParameter("location", String.class, ParameterMode.IN);
                    query.setParameter("profileName", a.getWORKDAY_JOB_PROFILE());
                    query.setParameter("functionalArea", a.getACCOUNT_NAME());
                    query.setParameter("location", a.getLOCATION());
                    List<Object[]> objectList = query.getResultList();

                    if (objectList.size() == 0) {

                        a.setJOB_REQ("");
                        this.accountRepo.save(a);

                    }
                    else {

                        for (Object[] obj : objectList) {

                            jobReqList.add(obj[3].toString());

                        }

                        a.setJOB_REQ(jobReqList.toString().substring(1, jobReqList.toString().length() - 1));
                        this.accountRepo.save(a);

                    }

                }

            }
            else {
                //System.out.println(a.getACCOUNT_NAME() + ": No Workday Profile Tag");
            }

        }

    }

}
