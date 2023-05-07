package com.careercitydashboard.ServiceImpl;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.StoredProcedureQuery;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.careercitydashboard.Dao.OpenJobReqRepo;
import com.careercitydashboard.Model.OpenJobReq;
import com.careercitydashboard.Service.UploadJobReqService;

@Service
@Transactional
public class UploadJobReqServiceImpl implements UploadJobReqService {

    @Autowired
    private OpenJobReqRepo openJobReqRepo;

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public void save(OpenJobReq openJobReq) {

        this.openJobReqRepo.save(openJobReq);

    }

    @Override
    public void truncate() {

        StoredProcedureQuery query1 = entityManager.createStoredProcedureQuery("truncateOpenJobReqTable");
        query1.executeUpdate();

    }

}
