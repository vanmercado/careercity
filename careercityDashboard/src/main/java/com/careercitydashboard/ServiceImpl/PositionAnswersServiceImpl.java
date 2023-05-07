package com.careercitydashboard.ServiceImpl;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.careercitydashboard.Dao.PositionAnswersRepo;
import com.careercitydashboard.Model.PositionAnswers;
import com.careercitydashboard.Service.PositionAnswerService;

@Service
@Transactional
public class PositionAnswersServiceImpl implements PositionAnswerService {

    @Autowired
    private PositionAnswersRepo positionAnswersRepo;

    @Override
    public List<PositionAnswers> getallPositionAnswers() {

        // TODO Auto-generated method stub
        return (List<PositionAnswers>) this.positionAnswersRepo.findAll();

    }

    @Override
    public PositionAnswers addPositionAnswers(PositionAnswers positionAnswers) {

        // TODO Auto-generated method stub
        return this.positionAnswersRepo.save(positionAnswers);

    }

    @Override
    public PositionAnswers updatePositionAnswers(PositionAnswers positionAnswers) {

        // TODO Auto-generated method stub
        return this.positionAnswersRepo.save(positionAnswers);

    }

    @Override
    public PositionAnswers getPositionMapping(Integer question_ANSWER_ID) {

        // TODO Auto-generated method stub
        return null;

    }

    @Override
    public PositionAnswers deletePositionMapping(Integer QUESTION_ANSWER_ID) {

        // TODO Auto-generated method stub
        this.positionAnswersRepo.deleteById(QUESTION_ANSWER_ID);
        ;
        return null;

    }

}
