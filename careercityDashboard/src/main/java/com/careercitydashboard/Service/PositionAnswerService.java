package com.careercitydashboard.Service;

import java.util.List;

import com.careercitydashboard.Model.PositionAnswers;

public interface PositionAnswerService {

    public List<PositionAnswers> getallPositionAnswers();

    public PositionAnswers addPositionAnswers(PositionAnswers positionAnswers);

    public PositionAnswers updatePositionAnswers(PositionAnswers positionAnswers);

    public PositionAnswers getPositionMapping(Integer question_ANSWER_ID);

    public PositionAnswers deletePositionMapping(Integer QUESTION_ANSWER_ID);

}
