package com.careercitydashboard.ServiceImpl;

import com.careercitydashboard.Model.Report;
import com.careercitydashboard.Service.ExportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.ParameterMode;
import javax.persistence.PersistenceContext;
import javax.persistence.StoredProcedureQuery;
import java.util.List;

@Service
public class ExportServiceImpl implements ExportService {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List getUsageTrafficData(String fromDate, String toDate, String site, String functionalArea,
                               String manager, String supervisor) {
        StoredProcedureQuery query = entityManager.createStoredProcedureQuery("getUsageTrafficData");
        query.registerStoredProcedureParameter("from_date", String.class, ParameterMode.IN);
        query.registerStoredProcedureParameter("to_date", String.class, ParameterMode.IN);
        query.registerStoredProcedureParameter("site", String.class, ParameterMode.IN);
        query.registerStoredProcedureParameter("functional_area", String.class, ParameterMode.IN);
        query.registerStoredProcedureParameter("manager", String.class, ParameterMode.IN);
        query.registerStoredProcedureParameter("supervisor", String.class, ParameterMode.IN);
        query.setParameter("from_date", fromDate);
        query.setParameter("to_date", toDate);
        query.setParameter("site", site);
        query.setParameter("functional_area", functionalArea);
        query.setParameter("manager", manager);
        query.setParameter("supervisor", supervisor);
        List<Object> usageTrafficList = query.getResultList();

        return usageTrafficList;
    }

    @Override
    public List getUsageByJobLevel(String fromDate, String toDate, String site, String functionalArea,
                                    String manager, String supervisor, String jobLevel) {
        StoredProcedureQuery query = entityManager.createStoredProcedureQuery("getUsageByJobLevelData");
        query.registerStoredProcedureParameter("from_date", String.class, ParameterMode.IN);
        query.registerStoredProcedureParameter("to_date", String.class, ParameterMode.IN);
        query.registerStoredProcedureParameter("site", String.class, ParameterMode.IN);
        query.registerStoredProcedureParameter("functional_area", String.class, ParameterMode.IN);
        query.registerStoredProcedureParameter("manager", String.class, ParameterMode.IN);
        query.registerStoredProcedureParameter("supervisor", String.class, ParameterMode.IN);
        query.registerStoredProcedureParameter("jobLevel", String.class, ParameterMode.IN);
        query.setParameter("from_date", fromDate);
        query.setParameter("to_date", toDate);
        query.setParameter("site", site);
        query.setParameter("functional_area", functionalArea);
        query.setParameter("manager", manager);
        query.setParameter("supervisor", supervisor);
        query.setParameter("jobLevel", jobLevel);
        List<Object> usageByJobLevelList = query.getResultList();

        return usageByJobLevelList;
    }



}
