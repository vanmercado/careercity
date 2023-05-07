package com.careercitydashboard.Service;

import java.util.List;

import com.careercitydashboard.Model.AccountAnswers;

public interface AccountAnswersService {

    public List<AccountAnswers> getallAccountAnswers();

    public AccountAnswers addAccountAnswers(AccountAnswers accountAnswers);

    public AccountAnswers updateAccountAnswers(AccountAnswers accountAnswers);

    public AccountAnswers getAccountMapping(Integer question_ANSWER_ID);

    public AccountAnswers deleteMapping(Integer QUESTION_ANSWER_ID);

}
