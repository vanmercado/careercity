/**
 * 
 */
package com.telusinternational.careercity.service;

/*import java.util.ArrayList;
import java.util.HashMap;*/
import java.util.List;
/*import java.util.Map;*/

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.telusinternational.careercity.dao.AnswerSheetRecordRepository;
/*import com.telusinternational.careercity.model.Answer;*/
import com.telusinternational.careercity.model.AnswerSheet;
import com.telusinternational.careercity.model.AnswerSheetRecord;
import com.telusinternational.careercity.model.Users;

/**
 * @author A.C.Estrada Jr. x209486
 * @date Oct 25, 2018
 */
@Service
@Transactional
public class AnswerSheetRecordService {

    @Autowired
    private AnswerSheetRecordRepository answerSheetRecordRepository;

    private String a1 = "";
    private String a2 = "";
    private String a3 = "";
    private String a4 = "";
    private String a5 = "";
    private String a6 = "";
    private String a7 = "";
    private String a8 = "";
    private String a9 = "";
    private String a10 = "";
    private String a11 = "";
    private String a12 = "";

    public List<AnswerSheetRecord> getAnswerSheetRecordList() {

        return (List<AnswerSheetRecord>) this.answerSheetRecordRepository.findAll();

    }

    public void saveNewAnswerSheetRecord(AnswerSheet userAnswers, Integer quizWorkdayId, Users user) {

        AnswerSheetRecord answerSheetRecord = new AnswerSheetRecord();
        answerSheetRecord.setWORKDAY_ID(quizWorkdayId);

        // Map the values of answer to global variable. For standard delimiter purposes
        if (userAnswers.getQ1() != null)
            userAnswers.getQ1().stream().forEach(x -> a1 += x.getANSWER() + "~");
        if (userAnswers.getQ2() != null)
            userAnswers.getQ2().stream().forEach(x -> a2 += x.getANSWER() + "~");
        if (userAnswers.getQ3() != null)
            userAnswers.getQ3().stream().forEach(x -> a3 += x.getANSWER() + "~");
        if (userAnswers.getQ4() != null)
            userAnswers.getQ4().stream().forEach(x -> a4 += x.getANSWER() + "~");
        if (userAnswers.getQ5() != null)
            userAnswers.getQ5().stream().forEach(x -> a5 += x.getANSWER() + "~");
        if (userAnswers.getQ6() != null)
            userAnswers.getQ6().stream().forEach(x -> a6 += x.getANSWER() + "~");
        if (userAnswers.getQ7() != null)
            userAnswers.getQ7().stream().forEach(x -> a7 += x.getANSWER() + "~");
        if (userAnswers.getQ8() != null)
            userAnswers.getQ8().stream().forEach(x -> a8 += x.getANSWER() + "~");
        if (userAnswers.getQ9() != null)
            userAnswers.getQ9().stream().forEach(x -> a9 += x.getANSWER() + "~");
        if (userAnswers.getQ10() != null)
            userAnswers.getQ10().stream().forEach(x -> a10 += x.getANSWER() + "~");
        if (userAnswers.getQ11() != null)
            userAnswers.getQ11().stream().forEach(x -> a11 += x.getANSWER() + "~");
        if (userAnswers.getQ12() != null)
            userAnswers.getQ12().stream().forEach(x -> a12 += x.getANSWER() + "~");

        answerSheetRecord.setA1(a1);
        answerSheetRecord.setA2(a2);
        answerSheetRecord.setA3(a3);
        answerSheetRecord.setA4(a4);
        answerSheetRecord.setA5(a5);
        answerSheetRecord.setA6(a6);
        answerSheetRecord.setA7(a7);
        answerSheetRecord.setA8(a8);
        answerSheetRecord.setA9(a9);
        answerSheetRecord.setA10(a10);
        answerSheetRecord.setA11(a11);
        answerSheetRecord.setA12(a12);
        answerSheetRecord.setUser(user);
        this.answerSheetRecordRepository.save(answerSheetRecord);

    }

}
