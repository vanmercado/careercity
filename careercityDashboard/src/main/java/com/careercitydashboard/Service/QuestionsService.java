package com.careercitydashboard.Service;

import java.util.List;

import com.careercitydashboard.Model.Questions;

public interface QuestionsService {

    public List<Questions> getallQuestions();

    public Questions addQuestions(Questions questions);

    public Questions updateQuestions(Questions questions);

    public Questions getQuestionsbyId(Integer Id);

}
