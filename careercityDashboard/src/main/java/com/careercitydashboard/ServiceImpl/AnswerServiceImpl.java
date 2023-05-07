package com.careercitydashboard.ServiceImpl;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.careercitydashboard.Dao.AnswerRepo;
import com.careercitydashboard.Model.Answer;
import com.careercitydashboard.Service.AnswerService;

@Service
@Transactional
public class AnswerServiceImpl implements AnswerService {

    @Autowired
    private AnswerRepo answerRepo;

    @Override
    public List<Answer> getallAnswer() {

        return (List<Answer>) this.answerRepo.findAll();

    }

    @Override
    public Answer getAnswerById(Integer answerid) {

        this.answerRepo.findById(answerid);
        return null;

    }

    @Override
    public Answer addAnswer(Answer answer) {

        return this.answerRepo.save(answer);

    }

    @Override
    public Answer updateAnswer(Answer answer) {

        return this.answerRepo.save(answer);

    }

    @Override
    public Integer getAnswerIdByAnswer(String answer) {

        return answerRepo.getAnswerIdByAnswer(answer);

    }

    @Override
    public void deleteAnswerById(Integer answerId) {

        answerRepo.deleteById(answerId);

    }

}
