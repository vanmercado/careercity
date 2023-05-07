package com.careercitydashboard.ServiceImpl;

import java.util.List;
import java.util.stream.Collectors;
import javax.persistence.EntityManager;
import javax.persistence.ParameterMode;
import javax.persistence.PersistenceContext;
import javax.persistence.StoredProcedureQuery;
import javax.transaction.Transactional;
import org.springframework.stereotype.Service;
import com.careercitydashboard.Model.FunctionalArea;
import com.careercitydashboard.Model.Manager;
import com.careercitydashboard.Model.SiteFilter;
import com.careercitydashboard.Model.Supervisor;
import com.careercitydashboard.Service.FilterService;

@Service
@Transactional
public class FilterServiceImpl implements FilterService {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<SiteFilter> findAllSites() {

        StoredProcedureQuery query = entityManager.createStoredProcedureQuery("filter_getAllSites");
        List<String> strList = query.getResultList();
        List<SiteFilter> siteList = strList.stream().map(str -> {

            SiteFilter s = new SiteFilter();
            s.setSite(str);
            return s;

        }).collect(Collectors.toList());
        return siteList;

    }

    @Override
    public List<FunctionalArea> findFunctionalAreaBySite(String site) {

        StoredProcedureQuery query = entityManager.createStoredProcedureQuery("filter_getFunctionalAreaBySite");
        query.registerStoredProcedureParameter("site", String.class, ParameterMode.IN);
        query.setParameter("site", site);
        List<String> objectList = query.getResultList();

        List<FunctionalArea> functionalAreaList = objectList.stream().map(str -> {

            FunctionalArea fa = new FunctionalArea();
            fa.setSite(str);
            fa.setFunctional_area(str);
            return fa;

        }).collect(Collectors.toList());

        return functionalAreaList;

    }

    @Override
    public List<Manager> findAllManagerByFunctionalArea(String functionalAreaList) {

        StoredProcedureQuery query = entityManager.createStoredProcedureQuery("filter_getManagerByFunctionalArea");
        query.registerStoredProcedureParameter("functionalAreaList", String.class, ParameterMode.IN);
        query.setParameter("functionalAreaList", functionalAreaList);
        List<Object[]> objectList = query.getResultList();
        List<Manager> managerList = objectList.stream().map(obj -> {

            Manager m = new Manager();
            m.setMgr_id(obj[0].toString());
            m.setMgr_name(obj[1].toString());
            return m;

        }).collect(Collectors.toList());

        return managerList;

    }

    @Override
    public List<Supervisor> findAllSupervisorByManager(String managerList) {

        StoredProcedureQuery query = entityManager.createStoredProcedureQuery("filter_getSuppByManager");
        query.registerStoredProcedureParameter("managerList", String.class, ParameterMode.IN);
        query.setParameter("managerList", managerList);
        List<Object[]> objectList = query.getResultList();
        List<Supervisor> supList = objectList.stream().map(obj -> {

            Supervisor s = new Supervisor();
            s.setSup_id(obj[0].toString());
            s.setSup_name(obj[1].toString());
            s.setMgr_id(obj[2].toString());
            return s;

        }).collect(Collectors.toList());
        return supList;

    }

}
