package com.careercitydashboard.ServiceImpl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.ParameterMode;
import javax.persistence.PersistenceContext;
import javax.persistence.StoredProcedureQuery;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.careercitydashboard.Dao.JobProfileChangeRepo;
import com.careercitydashboard.Model.JobProfileChange;
import com.careercitydashboard.Service.JobProfileChangeService;

@Service
@Transactional
public class JobProfileChangeServiceimpl implements JobProfileChangeService {

    @Autowired
    private JobProfileChangeRepo jobProfileChangeRepo;

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public void save(JobProfileChange accountPosition) {

        jobProfileChangeRepo.save(accountPosition);

    }

    @Override
    public List<Object[]> findUserByWdId(String wdId) {

        return jobProfileChangeRepo.userExists(wdId);

    }

    @Override
    public JobProfileChange findMatchedJobProfileQuiz_cco(String wdId, String firstName, String lastName,
            String prevJobProfile, String newJobProfile, String currentDate) {

        StoredProcedureQuery query = entityManager.createStoredProcedureQuery("findMatchedProfileCco_quiz");
        query.registerStoredProcedureParameter("wdId", String.class, ParameterMode.IN);
        query.registerStoredProcedureParameter("incomingJobProfile", String.class, ParameterMode.IN);
        query.registerStoredProcedureParameter("currentDate", String.class, ParameterMode.IN);
        query.setParameter("wdId", wdId);
        query.setParameter("incomingJobProfile", newJobProfile);
        query.setParameter("currentDate", currentDate);
        List<Object[]> objectList = query.getResultList();

        if (objectList.size() == 0) {

            return null;

        }
        else {

            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");

            JobProfileChange x = new JobProfileChange();
            x.setWorkdayId(wdId);
            x.setFirstName(firstName);
            x.setLastName(lastName);
            x.setPrevJobProfile(prevJobProfile);
            x.setNewJobProfile(newJobProfile);
            x.setWorkType(objectList.get(0)[1].toString());
            x.setMethod("Quiz");

            try {

                x.setDateUsed(formatter.parse(objectList.get(0)[2].toString()));

            }
            catch (ParseException e) {

                e.printStackTrace();

            }

            return x;

        }

    }

    @Override
    public JobProfileChange findMatchedJobProfileQuiz_dsSt(String wdId, String firstName, String lastName,
            String prevJobProfile, String newJobProfile, String currentDate) {

        StoredProcedureQuery query = entityManager.createStoredProcedureQuery("findMatchedProfileDsSt_quiz");
        query.registerStoredProcedureParameter("wdId", String.class, ParameterMode.IN);
        query.registerStoredProcedureParameter("incomingJobProfile", String.class, ParameterMode.IN);
        query.registerStoredProcedureParameter("currentDate", String.class, ParameterMode.IN);
        query.setParameter("wdId", wdId);
        query.setParameter("incomingJobProfile", newJobProfile);
        query.setParameter("currentDate", currentDate);
        List<Object[]> objectList = query.getResultList();

        if (objectList.size() == 0) {

            return null;

        }
        else {

            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");

            JobProfileChange x = new JobProfileChange();
            x.setWorkdayId(wdId);
            x.setFirstName(firstName);
            x.setLastName(lastName);
            x.setPrevJobProfile(prevJobProfile);
            x.setNewJobProfile(newJobProfile);
            x.setWorkType(objectList.get(0)[1].toString());
            x.setMethod("Quiz");

            try {

                x.setDateUsed(formatter.parse(objectList.get(0)[2].toString()));

            }
            catch (ParseException e) {

                e.printStackTrace();

            }

            return x;

        }

    }

    @Override
    public JobProfileChange findMatchedJobProfileSearch_cco(String wdId, String firstName, String lastName,
            String prevJobProfile, String newJobProfile, String currentDate) {

        StoredProcedureQuery query = entityManager.createStoredProcedureQuery("findMatchedProfileCco_search");
        query.registerStoredProcedureParameter("wdId", String.class, ParameterMode.IN);
        query.registerStoredProcedureParameter("incomingJobProfile", String.class, ParameterMode.IN);
        query.registerStoredProcedureParameter("currentDate", String.class, ParameterMode.IN);
        query.setParameter("wdId", wdId);
        query.setParameter("incomingJobProfile", newJobProfile);
        query.setParameter("currentDate", currentDate);
        List<Object[]> objectList = query.getResultList();

        if (objectList.size() == 0) {

            return null;

        }
        else {

            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");

            JobProfileChange x = new JobProfileChange();
            x.setWorkdayId(wdId);
            x.setFirstName(firstName);
            x.setLastName(lastName);
            x.setPrevJobProfile(prevJobProfile);
            x.setNewJobProfile(newJobProfile);
            x.setWorkType(objectList.get(0)[1].toString());
            x.setMethod("Search");

            try {

                x.setDateUsed(formatter.parse(objectList.get(0)[2].toString()));

            }
            catch (ParseException e) {

                e.printStackTrace();

            }

            return x;

        }

    }

    @Override
    public JobProfileChange findMatchedJobProfileSearch_dsSt(String wdId, String firstName, String lastName,
            String prevJobProfile, String newJobProfile, String currentDate) {

        StoredProcedureQuery query = entityManager.createStoredProcedureQuery("findMatchedProfileDsSt_search");
        query.registerStoredProcedureParameter("wdId", String.class, ParameterMode.IN);
        query.registerStoredProcedureParameter("incomingJobProfile", String.class, ParameterMode.IN);
        query.registerStoredProcedureParameter("currentDate", String.class, ParameterMode.IN);
        query.setParameter("wdId", wdId);
        query.setParameter("incomingJobProfile", newJobProfile);
        query.setParameter("currentDate", currentDate);
        List<Object[]> objectList = query.getResultList();

        if (objectList.size() == 0) {

            return null;

        }
        else {

            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");

            JobProfileChange x = new JobProfileChange();
            x.setWorkdayId(wdId);
            x.setFirstName(firstName);
            x.setLastName(lastName);
            x.setPrevJobProfile(prevJobProfile);
            x.setNewJobProfile(newJobProfile);
            x.setWorkType(objectList.get(0)[1].toString());
            x.setMethod("Search");

            try {

                x.setDateUsed(formatter.parse(objectList.get(0)[2].toString()));

            }
            catch (ParseException e) {

                e.printStackTrace();

            }

            return x;

        }

    }

}
