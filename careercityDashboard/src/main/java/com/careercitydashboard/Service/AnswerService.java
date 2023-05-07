package com.careercitydashboard.Service;

import java.util.List;
import com.careercitydashboard.Model.Answer;

public interface AnswerService {

    public List<Answer> getallAnswer();

    public Answer getAnswerById(Integer answerid);

    public Answer addAnswer(Answer answer);

    public Answer updateAnswer(Answer answer);

    public Integer getAnswerIdByAnswer(String answer);

    public void deleteAnswerById(Integer answerId);

}
