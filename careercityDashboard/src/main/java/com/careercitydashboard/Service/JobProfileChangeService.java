package com.careercitydashboard.Service;

import java.util.List;

import com.careercitydashboard.Model.JobProfileChange;

public interface JobProfileChangeService {

    void save(JobProfileChange accountPosition);

    List<Object[]> findUserByWdId(String wdId);

    JobProfileChange findMatchedJobProfileQuiz_cco(String wdId, String firstName, String lastName,
            String prevJobProfile, String newJobProfile, String currentDate);

    JobProfileChange findMatchedJobProfileQuiz_dsSt(String wdId, String firstName, String lastName,
            String prevJobProfile, String newJobProfile, String currentDate);

    JobProfileChange findMatchedJobProfileSearch_cco(String wdId, String firstName, String lastName,
            String prevJobProfile, String newJobProfile, String currentDate);

    JobProfileChange findMatchedJobProfileSearch_dsSt(String wdId, String firstName, String lastName,
            String prevJobProfile, String newJobProfile, String currentDate);

}
