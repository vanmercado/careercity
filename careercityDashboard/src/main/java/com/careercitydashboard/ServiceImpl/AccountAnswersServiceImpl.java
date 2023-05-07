package com.careercitydashboard.ServiceImpl;

import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.careercitydashboard.Dao.AccountAnswersRepo;
import com.careercitydashboard.Model.AccountAnswers;
import com.careercitydashboard.Service.AccountAnswersService;

@Service
@Transactional
public class AccountAnswersServiceImpl implements AccountAnswersService {

    @Autowired
    private AccountAnswersRepo accountAnswersRepo;

    @Override
    public List<AccountAnswers> getallAccountAnswers() {

        // TODO Auto-generated method stub
        return (List<AccountAnswers>) this.accountAnswersRepo.findAll();

    }

    @Override
    public AccountAnswers addAccountAnswers(AccountAnswers accountAnswers) {

        // TODO Auto-generated method stub
        return this.accountAnswersRepo.save(accountAnswers);

    }

    @Override
    public AccountAnswers updateAccountAnswers(AccountAnswers accountAnswers) {

        // TODO Auto-generated method stub
        return this.accountAnswersRepo.save(accountAnswers);

    }

    @Override
    public AccountAnswers getAccountMapping(Integer question_ANSWER_ID) {

        // TODO Auto-generated method stub
        return null;

    }

    @Override
    public AccountAnswers deleteMapping(Integer QUESTION_ANSWER_ID) {

        this.accountAnswersRepo.deleteById(QUESTION_ANSWER_ID);
        return null;

    }

}
