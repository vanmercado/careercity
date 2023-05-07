package com.careercitydashboard.ServiceImpl;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.careercitydashboard.Dao.QuestionsRepo;
import com.careercitydashboard.Model.Questions;
import com.careercitydashboard.Service.QuestionsService;

@Service
@Transactional
public class QuestionsServiceImpl implements QuestionsService {

    @Autowired
    private QuestionsRepo questionsRepo;

    @Override
    public List<Questions> getallQuestions() {

        return (List<Questions>) this.questionsRepo.findAll();

    }

    @Override
    public Questions addQuestions(Questions questions) {

        return this.questionsRepo.save(questions);

    }

    @Override
    public Questions updateQuestions(Questions questions) {

        return this.questionsRepo.save(questions);

    }

    @Override
    public Questions getQuestionsbyId(Integer Id) {

        this.questionsRepo.findById(Id);
        return null;

    }

}
