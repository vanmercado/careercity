package com.careercitydashboard.Service;

import java.util.List;

import com.careercitydashboard.Model.FunctionalArea;
import com.careercitydashboard.Model.Manager;
import com.careercitydashboard.Model.SiteFilter;
import com.careercitydashboard.Model.Supervisor;

public interface FilterService {

    List<SiteFilter> findAllSites();

    List<FunctionalArea> findFunctionalAreaBySite(String site);

    List<Manager> findAllManagerByFunctionalArea(String functionalAreaList);

    List<Supervisor> findAllSupervisorByManager(String managerList);

}
