package com.careercitydashboard.ServiceImpl;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.ParameterMode;
import javax.persistence.PersistenceContext;
import javax.persistence.StoredProcedureQuery;

import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.careercitydashboard.Dao.PositionRepo;
import com.careercitydashboard.Model.Position;
import com.careercitydashboard.Model.PositionHistory;
import com.careercitydashboard.Service.PositionService;

@Service
@Transactional
public class PositionServiceImpl implements PositionService {

    @Autowired
    private PositionHistoryServiceImpl positionHistory;

    @Autowired
    private PositionRepo positionRepo;

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<Position> getallPositions() {

        return (List<Position>) this.positionRepo.findAll();

    }

    @Override
    public Position getPositionById(Integer Id) {

        return this.positionRepo.findById(Id).get();

    }

    public Position savePosition(Position position) {

        try {

            if (position.getPOSITION_ID() == null || !this.positionRepo.existsById(position.getPOSITION_ID())) {

                Authentication auth = SecurityContextHolder.getContext().getAuthentication();
                Position savedPosition = this.positionRepo.save(position);
                PositionHistory positionHistory = new PositionHistory();
                positionHistory.setPosition(savedPosition);
                positionHistory.setPosition_hist_msg("Created");
                positionHistory.setPosition_hist_user(auth.getName());

                this.positionHistory.savePositionSession(positionHistory);
                return savedPosition;

            }
            else {

                Position updatedPosition = getPositionById(position.getPOSITION_ID());
                updatedPosition.setJOB_PROFILE(position.getJOB_PROFILE());
                updatedPosition.setDEPARTMENT(position.getDEPARTMENT());
                updatedPosition.setIMAGE_PATH(position.getIMAGE_PATH());
                updatedPosition.setSUPPORT_TYPE(position.getSUPPORT_TYPE());
                updatedPosition.setEDUCATIONAL_BACKGROUND(position.getEDUCATIONAL_BACKGROUND());
                updatedPosition.setEXPERIENCE_REQUIRED(position.getEXPERIENCE_REQUIRED());
                updatedPosition.setDESCRIPTION(position.getDESCRIPTION());
                updatedPosition.setSKILLS_REQUIRED(position.getSKILLS_REQUIRED());
                updatedPosition.setJOB_REQ(position.getJOB_REQ());
                updatedPosition.setCERTIFICATION(position.getCERTIFICATION());
                updatedPosition.setOTHER_SKILLS(position.getOTHER_SKILLS());
                updatedPosition.setTRAININGS(position.getTRAININGS());
                updatedPosition.setWORKDAY_JOB_PROFILE(position.getWORKDAY_JOB_PROFILE());
                updatedPosition.setCATEGORY(position.getCATEGORY());

                // History
                Authentication auth = SecurityContextHolder.getContext().getAuthentication();
                PositionHistory positionHistory = new PositionHistory();
                positionHistory.setPosition(updatedPosition);
                positionHistory.setPosition_hist_msg("Modified details");
                positionHistory.setPosition_hist_user(auth.getName());
                this.positionHistory.savePositionSession(positionHistory);
                return this.positionRepo.save(updatedPosition);

            }

        }
        catch (Exception e) {

            e.printStackTrace();

        }

        return null;

    }

    @Override
    public void updatePosition(Position position) {

        Position updatedPosition = getPositionById(position.getPOSITION_ID());
        updatedPosition.setJOB_PROFILE(position.getJOB_PROFILE());
        updatedPosition.setDEPARTMENT(position.getDEPARTMENT());
        updatedPosition.setIMAGE_PATH(position.getIMAGE_PATH());
        updatedPosition.setSUPPORT_TYPE(position.getSUPPORT_TYPE());
        updatedPosition.setEDUCATIONAL_BACKGROUND(position.getEDUCATIONAL_BACKGROUND());
        updatedPosition.setEXPERIENCE_REQUIRED(position.getEXPERIENCE_REQUIRED());
        updatedPosition.setDESCRIPTION(position.getDESCRIPTION());
        updatedPosition.setSKILLS_REQUIRED(position.getSKILLS_REQUIRED());
        updatedPosition.setCERTIFICATION(position.getCERTIFICATION());
        updatedPosition.setOTHER_SKILLS(position.getOTHER_SKILLS());
        updatedPosition.setTRAININGS(position.getTRAININGS());
        updatedPosition.setCATEGORY(position.getCATEGORY());
        updatedPosition.setJOB_REQ(position.getJOB_REQ());

        // History
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();

        PositionHistory positionHistory = new PositionHistory();
        positionHistory.setPosition(updatedPosition);
        positionHistory.setPosition_hist_msg("Updated Details");
        positionHistory.setPosition_hist_user(auth.getName());
        this.positionRepo.save(updatedPosition);

    }

    @Override
    public Position getPositionMapping(Integer POSITION_ID) {

        Position position = this.positionRepo.findById(POSITION_ID).get();
        Hibernate.initialize(position.getPositionAnswers().size());
        return position;

    }

    @Override
    public void syncJobReqPosition() {

        List<Position> positionList = this.positionRepo.findAll();

        for (Position p : positionList) {

            if (p.getWORKDAY_JOB_PROFILE() != null) {

                //StoredProcedureQuery query = entityManager.createStoredProcedureQuery("getJobReqIdByProfileName");
                //query.registerStoredProcedureParameter("profileName", String.class, ParameterMode.IN);
                //query.setParameter("profileName", p.getWORKDAY_JOB_PROFILE());
                //List<Object[]>  objectList =  query.getResultList();
                //if(objectList.size() == 0) {
                //    p.setJOB_REQ("");
                //    this.positionRepo.save(p);
                //}
                //else {
                //    for(Object[] obj: objectList) {
                //        p.setJOB_REQ(obj[3].toString());
                //        this.positionRepo.save(p);
                //    }
                //}

                if (p.getWORKDAY_JOB_PROFILE().contains("~")) {

                    String string = p.getWORKDAY_JOB_PROFILE();
                    String[] tags = string.split("~");

                    List<String> jobReqList = new ArrayList<String>();

                    for (String tag : tags) {

                        StoredProcedureQuery query = entityManager
                                .createStoredProcedureQuery("getJobReqIdByProfileName");
                        query.registerStoredProcedureParameter("profileName", String.class, ParameterMode.IN);
                        query.setParameter("profileName", tag);
                        List<Object[]> objectList = query.getResultList();

                        if (objectList.size() == 0) {

                        }
                        else {

                            for (Object[] obj : objectList) {

                                jobReqList.add(obj[3].toString());

                            }

                        }

                    }

                    if (jobReqList.size() == 0) {

                        p.setJOB_REQ("");
                        this.positionRepo.save(p);

                    }
                    else {

                        p.setJOB_REQ(jobReqList.toString().substring(1, jobReqList.toString().length() - 1));
                        this.positionRepo.save(p);

                    }

                }
                else {

                    List<String> jobReqList = new ArrayList<String>();

                    StoredProcedureQuery query = entityManager.createStoredProcedureQuery("getJobReqIdByProfileName");
                    query.registerStoredProcedureParameter("profileName", String.class, ParameterMode.IN);
                    query.setParameter("profileName", p.getWORKDAY_JOB_PROFILE());
                    List<Object[]> objectList = query.getResultList();

                    if (objectList.size() == 0) {

                        p.setJOB_REQ("");
                        this.positionRepo.save(p);

                    }
                    else {

                        for (Object[] obj : objectList) {

                            jobReqList.add(obj[3].toString());

                        }

                        p.setJOB_REQ(jobReqList.toString().substring(1, jobReqList.toString().length() - 1));
                        this.positionRepo.save(p);

                    }

                }

            }
            else {
                //System.out.println(p.getJOB_PROFILE() + ": No Workday Profile Tag");
            }

        }

    }// void syncJobReqPosition

}// class PositionServiceImpl
