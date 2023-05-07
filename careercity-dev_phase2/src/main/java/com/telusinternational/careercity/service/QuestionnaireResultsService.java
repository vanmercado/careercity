package com.telusinternational.careercity.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.quartz.QuartzDataSourceInitializer;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.telusinternational.careercity.dao.QuetionnaireResultsRepository;
import com.telusinternational.careercity.model.Account;
import com.telusinternational.careercity.model.AnswerSheet;
import com.telusinternational.careercity.model.Position;
import com.telusinternational.careercity.model.QuestionnaireResults;
import com.telusinternational.careercity.model.Users;
import com.telusinternational.careercity.util.AnswersHelper;

@Service
@Transactional
public class QuestionnaireResultsService {

    @Autowired
    private QuetionnaireResultsRepository qrrepository;

    @Autowired
    private AccountService accountService;

    @Autowired
    private PositionService positionService;

    public List<Account> getResultsCco(AnswerSheet userAnswers, HashMap<Integer, AnswerSheet> answerSheets,
            Integer workdayId, Users user) {

        ArrayList<Account> myMatchedCcoAccounts = new ArrayList<>();

        for (Integer id : answerSheets.keySet()) {

            if (AnswersHelper.isCcoMatch(answerSheets.get(id), userAnswers)) {

                myMatchedCcoAccounts.add(accountService.getAccountById(id));

            }

        }

        if (!myMatchedCcoAccounts.isEmpty()) {

            saveCcoResults(myMatchedCcoAccounts, workdayId, user);

        }

        return myMatchedCcoAccounts;

    }

    public List<Position> getResultsDsSt(AnswerSheet userAnswers, HashMap<Integer, AnswerSheet> answerSheets,
            Integer workdayId, Users user) {

        ArrayList<Position> myMatchedDsStAccounts = new ArrayList<>();

        for (Integer id : answerSheets.keySet()) {

            if (AnswersHelper.isDsStMatch(answerSheets.get(id), userAnswers)) {

                myMatchedDsStAccounts.add(positionService.getPositionById(id));

            }

        }

        if (!myMatchedDsStAccounts.isEmpty()) {

            saveDsStResults(myMatchedDsStAccounts, workdayId, user);

        }

        return myMatchedDsStAccounts;

    }

    public void saveCcoResults(List<Account> myMatchedCcoAccounts2, Integer workdayId, Users user) {

        List<QuestionnaireResults> results = new ArrayList<>();

        for (Object object : myMatchedCcoAccounts2) {

            results.add(new QuestionnaireResults(workdayId, ((Account) object).getACCOUNT_ID(), "CCO", user));

        }

        this.qrrepository.saveAll(results);

    }

    public void saveDsStResults(List<Position> myMatchedDsStAccounts2, Integer workdayId, Users user) {

        List<QuestionnaireResults> results = new ArrayList<>();

        for (Object object : myMatchedDsStAccounts2) {

            results.add(new QuestionnaireResults(workdayId, ((Position) object).getPOSITION_ID(),
                    ((Position) object).getCATEGORY(), user));

        }

        this.qrrepository.saveAll(results);

    }

}
