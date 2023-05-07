package com.telusinternational.careercity.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.telusinternational.careercity.dao.AnswerRepository;
import com.telusinternational.careercity.model.Answer;

@Service
public class AnswerService {

    @Autowired
    private AnswerRepository answerRepository;

    public List<Answer> findByQuestionId(Integer question_id) {

        List<Answer> answers = this.answerRepository.findByQuestionId(question_id);
        //System.out.println("ANSWER size: " + answers.size());
        return answers;

    }

    /*
     * public List<Answer> findAll(Integer x){ List<Answer> answers1 =
     * this.answerRepository.findAll(); List<Answer> container = new ArrayList<>();
     * switch(x){ case 1: for(int i=0; i<5; i++) { container.add(answers1.get(i)); }
     * break; case 2: for(int i=5; i<9; i++) { container.add(answers1.get(i)); }
     * break; case 10: for(int i=9; i<13; i++) { container.add(answers1.get(i)); }
     * break; case 3: for(int i=13; i<22; i++) { container.add(answers1.get(i)); }
     * break; case 4: for(int i=22; i<27; i++) { container.add(answers1.get(i)); }
     * break; case 5: for(int i=27; i<32; i++) { container.add(answers1.get(i)); }
     * break; case 6: for(int i=32; i<37; i++) { container.add(answers1.get(i)); }
     * break; case 7: for(int i=37; i<41; i++) { container.add(answers1.get(i)); }
     * break; case 8: for(int i=41; i<43; i++) { container.add(answers1.get(i)); }
     * break; case 9: for(int i=43; i<44; i++) { container.add(answers1.get(i)); }
     * break; } return container; }
     */

    public List<Answer> findAll() {

        return this.answerRepository.findAll();

    }

}
