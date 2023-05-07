package com.careercitydashboard.ServiceImpl;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import javax.persistence.EntityManager;
import javax.persistence.ParameterMode;
import javax.persistence.PersistenceContext;
import javax.persistence.StoredProcedureQuery;
import org.springframework.stereotype.Service;
import com.careercitydashboard.Model.Report;
import com.careercitydashboard.Model.TidsSuppHire;
import com.careercitydashboard.Model.ValidHire;
import com.careercitydashboard.Service.AnalyticsService;

/**
 * @author A.M.Castor x164856
 * @date June 16, 2020
 */

@Service
public class AnalyticsServiceImpl implements AnalyticsService {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<Report> getAllFunctionalAreaByDate(String fromDate, String toDate) {

        StoredProcedureQuery query1 = entityManager.createStoredProcedureQuery("getAllFunctionalArea");
        List<String> completeFunctionalAreaList = query1.getResultList();

        StoredProcedureQuery query2 = entityManager.createStoredProcedureQuery("getAllFunctionalAreaByDate");
        query2.registerStoredProcedureParameter("from_date", String.class, ParameterMode.IN);
        query2.registerStoredProcedureParameter("to_date", String.class, ParameterMode.IN);
        query2.setParameter("from_date", fromDate);
        query2.setParameter("to_date", toDate);
        List<Object[]> objectList2 = query2.getResultList();
        List<Report> functionalAreaListByDate = objectList2.stream().map(obj -> {

            Report r = new Report();
            r.setLabel(obj[0].toString());
            r.setCounts(Integer.parseInt(obj[1].toString()));
            return r;

        }).collect(Collectors.toList());

        List<Report> functionalAreaListByDate_final = new ArrayList<Report>();

        for (String functionalAreaName : completeFunctionalAreaList) {

            Report r = new Report();
            r.setLabel(functionalAreaName);
            int counts = functionalAreaListByDate.stream().filter(x -> functionalAreaName.equals(x.getLabel()))
                    .map(Report::getCounts).findAny().orElse(0);

            r.setCounts(counts);
            functionalAreaListByDate_final.add(r);

        }

        return functionalAreaListByDate_final;

    }

    @Override
    public List<Report> getAllPreferredSites(String fromDate, String toDate, String site, String functionalArea,
            String manager, String supervisor) {

        StoredProcedureQuery query = entityManager.createStoredProcedureQuery("preferredSites");
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
        List<Object[]> prefSitesList = query.getResultList();
        List<Report> prefSitesListRaw = prefSitesList.stream()
                .filter(obj -> obj[0].toString() != null || Integer.parseInt(obj[1].toString()) != 0).map(obj -> {

                    Report r = new Report();
                    r.setLabel(obj[0].toString());
                    r.setCounts(Integer.parseInt(obj[1].toString()));
                    return r;

                }).collect(Collectors.toList());

        List<Report> prefSitesListFinal = prefSitesListRaw.stream().filter(report -> report.getCounts() != 0)
                .collect(Collectors.toList());
        return prefSitesListFinal;

    }

    @Override
    public List<Report> getAllPreferredWorkCCO(String fromDate, String toDate, String site, String functionalArea,
            String manager, String supervisor) {

        StoredProcedureQuery query = entityManager.createStoredProcedureQuery("preferredWorkCCO");
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
        List<Object[]> preferredWorkCCOList = query.getResultList();
        List<Report> preferredWorkCCOListRaw = preferredWorkCCOList.stream()
                .filter(obj -> obj[0].toString() != null || Integer.parseInt(obj[1].toString()) != 0).map(obj -> {

                    Report r = new Report();
                    r.setLabel(obj[0].toString());
                    r.setCounts(Integer.parseInt(obj[1].toString()));
                    return r;

                }).collect(Collectors.toList());

        List<Report> preferredWorkCCOListFinal = preferredWorkCCOListRaw.stream()
                .filter(report -> report.getCounts() != 0).collect(Collectors.toList());
        return preferredWorkCCOListFinal;

    }

    @Override
    public List<Report> getAllPreferredWorkTIDS(String fromDate, String toDate, String site, String functionalArea,
            String manager, String supervisor) {

        StoredProcedureQuery query = entityManager.createStoredProcedureQuery("preferredWorkTIDS");
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
        List<Object[]> preferredWorkTIDSList = query.getResultList();
        List<Report> preferredWorkTIDSListRaw = preferredWorkTIDSList.stream()
                .filter(obj -> obj[0].toString() != null || Integer.parseInt(obj[1].toString()) != 0).map(obj -> {

                    Report r = new Report();
                    r.setLabel(obj[0].toString());
                    r.setCounts(Integer.parseInt(obj[1].toString()));
                    return r;

                }).collect(Collectors.toList());

        List<Report> preferredWorkTIDSListFinal = preferredWorkTIDSListRaw.stream()
                .filter(report -> report.getCounts() != 0).collect(Collectors.toList());
        return preferredWorkTIDSListFinal;

    }

    @Override
    public List<Report> getAllPreferredWorkSupport(String fromDate, String toDate, String site, String functionalArea,
            String manager, String supervisor) {

        StoredProcedureQuery query = entityManager.createStoredProcedureQuery("preferredWorkSupport");
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
        List<Object[]> preferredWorkSupportList = query.getResultList();
        List<Report> preferredWorkSupportListRaw = preferredWorkSupportList.stream()
                .filter(obj -> obj[0].toString() != null || Integer.parseInt(obj[1].toString()) != 0).map(obj -> {

                    Report r = new Report();
                    r.setLabel(obj[0].toString());
                    r.setCounts(Integer.parseInt(obj[1].toString()));
                    return r;

                }).collect(Collectors.toList());

        List<Report> preferredWorkSupportListFinal = preferredWorkSupportListRaw.stream()
                .filter(report -> report.getCounts() != 0).collect(Collectors.toList());
        return preferredWorkSupportListFinal;

    }

    @Override
    public List<Report> getAllBPOExperience(String fromDate, String toDate, String site, String functionalArea,
            String manager, String supervisor) {

        StoredProcedureQuery query = entityManager.createStoredProcedureQuery("bpoExperience");
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
        List<Object[]> bpoExperienceList = query.getResultList();
        List<Report> bpoExperienceListRaw = bpoExperienceList.stream()
                .filter(obj -> obj[0].toString() != null || Integer.parseInt(obj[1].toString()) != 0).map(obj -> {

                    Report r = new Report();
                    r.setLabel(obj[0].toString());
                    r.setCounts(Integer.parseInt(obj[1].toString()));
                    return r;

                }).collect(Collectors.toList());

        List<Report> bpoExperienceListFinal = bpoExperienceListRaw.stream().filter(report -> report.getCounts() != 0)
                .collect(Collectors.toList());
        return bpoExperienceListFinal;

    }

    @Override
    public List<Report> getAllPreferredWorkSchedule(String fromDate, String toDate, String site, String functionalArea,
            String manager, String supervisor) {

        StoredProcedureQuery query = entityManager.createStoredProcedureQuery("preferredWorkSchedule");
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
        List<Object[]> preferredWorkScheduleList = query.getResultList();
        List<Report> preferredWorkScheduleListRaw = preferredWorkScheduleList.stream()
                .filter(obj -> obj[0].toString() != null || Integer.parseInt(obj[1].toString()) != 0).map(obj -> {

                    Report r = new Report();
                    r.setLabel(obj[0].toString());
                    r.setCounts(Integer.parseInt(obj[1].toString()));
                    return r;

                }).collect(Collectors.toList());

        List<Report> preferredWorkScheduleListFinal = preferredWorkScheduleListRaw.stream()
                .filter(report -> report.getCounts() != 0).collect(Collectors.toList());
        return preferredWorkScheduleListFinal;

    }

    @Override
    public List<Report> getAllFieldOfStudy(String fromDate, String toDate, String site, String functionalArea,
            String manager, String supervisor) {

        StoredProcedureQuery query = entityManager.createStoredProcedureQuery("fieldOfStudy");
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
        List<Object[]> fieldOfStudyList = query.getResultList();
        List<Report> fieldOfStudyListRaw = fieldOfStudyList.stream()
                .filter(obj -> obj[0].toString() != null || Integer.parseInt(obj[1].toString()) != 0).map(obj -> {

                    Report r = new Report();
                    r.setLabel(obj[0].toString());
                    r.setCounts(Integer.parseInt(obj[1].toString()));
                    return r;

                }).collect(Collectors.toList());

        List<Report> fieldOfStudyListFinal = fieldOfStudyListRaw.stream().filter(report -> report.getCounts() != 0)
                .collect(Collectors.toList());
        return fieldOfStudyListFinal;

    }

    @Override
    public List<Report> getAllAdditionalSkills(String fromDate, String toDate, String site, String functionalArea,
            String manager, String supervisor) {

        StoredProcedureQuery query = entityManager.createStoredProcedureQuery("additionalSkillsByJobLevel");
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
        List<Object[]> additionalSkillsList = query.getResultList();
        List<Report> additionalSkillsListRaw = additionalSkillsList.stream().filter(
                obj -> obj[0].toString() != null || obj[1].toString() != "" || Integer.parseInt(obj[1].toString()) != 0)
                .map(obj -> {

                    Report r = new Report();
                    r.setLabel(obj[0].toString());
                    r.setCounts(Integer.parseInt(obj[1].toString()));
                    return r;

                }).collect(Collectors.toList());

        List<Report> additionalSkillsListFinal = additionalSkillsListRaw.stream()
                .filter(report -> report.getCounts() != 0).collect(Collectors.toList());
        return additionalSkillsListFinal;

    }

    // CAREER CITY CLICKS TAB
    @Override
    public List<Report> getAllPreferredSites_ccClicks(String fromDate, String toDate, String site,
            String functionalArea, String manager, String supervisor) {

        StoredProcedureQuery query = entityManager.createStoredProcedureQuery("preferredSitesCCO_clickBased");
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
        List<Object[]> preferredSitesCCOList = query.getResultList();
        List<Report> preferredSitesCCOListRaw = preferredSitesCCOList.stream()
                .filter(obj -> obj[0].toString() != null || Integer.parseInt(obj[1].toString()) != 0).map(obj -> {

                    Report r = new Report();
                    r.setLabel(obj[0].toString());
                    r.setCounts(Integer.parseInt(obj[1].toString()));
                    return r;

                }).collect(Collectors.toList());

        List<Report> preferredSitesCCOListFinal = preferredSitesCCOListRaw.stream()
                .filter(report -> report.getCounts() != 0).collect(Collectors.toList());

//		 StoredProcedureQuery query2 = entityManager.createStoredProcedureQuery("preferredSitesCCO_clickBased"); 
//			query2.registerStoredProcedureParameter("from_date", String.class, ParameterMode.IN);
//		    query2.registerStoredProcedureParameter("to_date", String.class, ParameterMode.IN);
//			query2.setParameter("from_date", fromDate);
//			query2.setParameter("to_date", toDate);
//			List<Object[]>  preferredSitesDSList =  query2.getResultList();
//			List<Report> preferredSitesDSListRaw = preferredSitesDSList.stream()
//													.filter(obj -> obj[0].toString() != null || Integer.parseInt(obj[1].toString()) != 0)
//													.map(obj -> {
//														Report r = new Report();
//														r.setLabel(obj[0].toString());
//														r.setCounts(Integer.parseInt(obj[1].toString()));
//														return r;
//													})
//													.collect(Collectors.toList());

//			 List<Report> preferredSitesDSListFinal = preferredSitesDSListRaw.stream()
//					 									.filter(report -> report.getCounts() != 0)
//					 									.collect(Collectors.toList());
//			 
//			 Integer discovIndex = checkIndex(preferredSitesCCOListFinal);
//			 Integer discovCounts = preferredSitesCCOListFinal.get(discovIndex).getCounts();
//			 if(discovIndex == null) {
//				 System.out.println("discovery does not exist, creating new one..");
//				 Integer total = 0;
//				 Report report = new Report();
//				 for(Report ds : preferredSitesDSListFinal) {
//					 total += ds.getCounts();
//				 }
//				 report.setLabel("Discovery");
//				 report.setCounts(total);
//				 preferredSitesCCOListFinal.add(report);
//			 }else {
//				 for(Report ds : preferredSitesDSListFinal) {
//					 discovCounts += ds.getCounts();
//				 }
//			 }
//			 
//			 preferredSitesCCOListFinal.get(discovIndex).setCounts(discovCounts);

        return preferredSitesCCOListFinal;

    }

//	private Integer checkIndex(List<Report> reportList) {
//		Integer i = 0;
//		if(reportList.size() == 0) {
//			return null;
//		}
//		for(Report report : reportList) {
//			if(report.getLabel().equalsIgnoreCase("Discovery")) {
//				i = reportList.indexOf(report);
//			}
//		}
//		return i;
//	}

    @Override
    public List<Report> getAllPreferredPrograms_ccClicks(String fromDate, String toDate, String site,
            String functionalArea, String manager, String supervisor) {

        StoredProcedureQuery query = entityManager.createStoredProcedureQuery("ccClicks_preferredPrograms");
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
        List<Object[]> prefProgramsList = query.getResultList();
        List<Report> prefProgramsListRaw = prefProgramsList.stream()
                .filter(obj -> obj[0].toString() != null || Integer.parseInt(obj[1].toString()) != 0).map(obj -> {

                    Report r = new Report();
                    r.setLabel(obj[0].toString());
                    r.setCounts(Integer.parseInt(obj[1].toString()));
                    return r;

                }).collect(Collectors.toList());

        List<Report> prefProgramsListFinal = prefProgramsListRaw.stream().filter(report -> report.getCounts() != 0)
                .collect(Collectors.toList());
        return prefProgramsListFinal;

    }

    @Override
    public List<Report> getAllInDemandBusinessTypes_ccClicks(String fromDate, String toDate, String site,
            String functionalArea, String manager, String supervisor) {

        StoredProcedureQuery query = entityManager.createStoredProcedureQuery("ccClicks_inDemandBusinessTypes");
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
        List<Object[]> busTypesList_fromDb = query.getResultList();
        List<Report> busTypesList_processed = busTypesList_fromDb.stream().map(obj -> {

            Report r = new Report();
            r.setLabel((obj[0] == null || obj[0].toString().equalsIgnoreCase("")) ? null : obj[0].toString());
            r.setCounts(obj[1] == null || (Integer.parseInt(obj[1].toString()) == 0) ? 0
                    : Integer.parseInt(obj[1].toString()));
            return r;

        }).filter(r -> r.getLabel() != null && r.getCounts() != 0).collect(Collectors.toList());

        return busTypesList_processed;

    }

    @Override
    public List<Report> getAllInDemandWorkTypesCCO_ccClicks(String fromDate, String toDate, String site,
            String functionalArea, String manager, String supervisor) {

        StoredProcedureQuery query = entityManager.createStoredProcedureQuery("ccClicks_inDemandWorkCCO");
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
        List<Object[]> workTypeCCOList_fromDb = query.getResultList();
        List<Report> workTypeCCOList_processed = workTypeCCOList_fromDb.stream().map(obj -> {

            Report r = new Report();
            r.setLabel((obj[0] == null || obj[0].toString().equalsIgnoreCase("")) ? null : obj[0].toString());
            r.setCounts(obj[1] == null || (Integer.parseInt(obj[1].toString()) == 0) ? 0
                    : Integer.parseInt(obj[1].toString()));
            return r;

        }).filter(r -> r.getLabel() != null && r.getCounts() != 0).collect(Collectors.toList());

        return workTypeCCOList_processed;

    }

    @Override
    public List<Report> getAllPreferredSupportDept_ccClicks(String fromDate, String toDate, String site,
            String functionalArea, String manager, String supervisor) {

        StoredProcedureQuery query = entityManager.createStoredProcedureQuery("ccClicks_preferredSupportDept");
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
        List<Object[]> prefSuppDeptList_fromDb = query.getResultList();
        List<Report> prefSuppDeptList_processed = prefSuppDeptList_fromDb.stream().map(obj -> {

            Report r = new Report();
            r.setLabel((obj[0] == null || obj[0].toString().equalsIgnoreCase("")) ? null : obj[0].toString());
            r.setCounts(obj[1] == null || (Integer.parseInt(obj[1].toString()) == 0) ? 0
                    : Integer.parseInt(obj[1].toString()));
            return r;

        }).filter(r -> r.getLabel() != null && r.getCounts() != 0).collect(Collectors.toList());

        return prefSuppDeptList_processed;

    }

    @Override
    public List<Report> getAllPreferredSupportJobs_ccClicks(String fromDate, String toDate, String site,
            String functionalArea, String manager, String supervisor) {

        StoredProcedureQuery query = entityManager.createStoredProcedureQuery("ccClicks_preferredSupportJobs");
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
        List<Object[]> prefSuppJobList_fromDb = query.getResultList();
        List<Report> prefSuppJobList_processed = prefSuppJobList_fromDb.stream().map(obj -> {

            Report r = new Report();
            r.setLabel((obj[0] == null || obj[0].toString().equalsIgnoreCase("")) ? null : obj[0].toString());
            r.setCounts(obj[1] == null || (Integer.parseInt(obj[1].toString()) == 0) ? 0
                    : Integer.parseInt(obj[1].toString()));
            return r;

        }).filter(r -> r.getLabel() != null && r.getCounts() != 0).collect(Collectors.toList());

        return prefSuppJobList_processed;

    }

    @Override
    public List<Report> getAllInDemandJobProfiles_ccClicks(String fromDate, String toDate, String site,
            String functionalArea, String manager, String supervisor) {

        StoredProcedureQuery query = entityManager.createStoredProcedureQuery("ccClicks_inDemandJobProfile");
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
        List<Object[]> jobProfileList_fromDb = query.getResultList();
        List<Report> jobProfileList_processed = jobProfileList_fromDb.stream().map(obj -> {

            Report r = new Report();
            r.setLabel((obj[0] == null || obj[0].toString().equalsIgnoreCase("")) ? null : obj[0].toString());
            r.setCounts(obj[1] == null || (Integer.parseInt(obj[1].toString()) == 0) ? 0
                    : Integer.parseInt(obj[1].toString()));
            return r;

        }).filter(r -> r.getLabel() != null && r.getCounts() != 0).collect(Collectors.toList());

        return jobProfileList_processed;

    }

    @Override
    public List<Report> getAllClickCountsForCcoAndTids_ccClicks(String fromDate, String toDate, String site,
            String functionalArea, String manager, String supervisor) {

        StoredProcedureQuery query = entityManager.createStoredProcedureQuery("ccClicks_supportAndTids");
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
        List<Object[]> supportAndTidsList_fromDb = query.getResultList();
        List<Report> supportAndTidsList_processed = supportAndTidsList_fromDb.stream().map(obj -> {

            Report r = new Report();
            r.setLabel((obj[0] == null || obj[0].toString().equalsIgnoreCase("")) ? null : obj[0].toString());
            r.setCounts(obj[1] == null || (Integer.parseInt(obj[1].toString()) == 0) ? 0
                    : Integer.parseInt(obj[1].toString()));
            return r;

        }).filter(r -> r.getLabel() != null && r.getCounts() != 0).collect(Collectors.toList());

        return supportAndTidsList_processed;

    }

    @Override
    public List<Report> getUsageTraffic(String fromDate, String toDate, String site, String functionalArea,
            String manager, String supervisor) {

        StoredProcedureQuery query = entityManager.createStoredProcedureQuery("getUserTraffic");
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
        List<Object[]> usageTrafficList_fromDb = query.getResultList();
        List<Report> usageTrafficList_processed = usageTrafficList_fromDb.stream().map(obj -> {

            Report r = new Report();
            r.setLabel((obj[0] == null || obj[0].toString().equalsIgnoreCase("")) ? null : obj[0].toString());
            r.setCounts(obj[1] == null || (Integer.parseInt(obj[1].toString()) == 0) ? 0
                    : Integer.parseInt(obj[1].toString()));
            return r;

        }).filter(r -> r.getLabel() != null && r.getCounts() != 0).collect(Collectors.toList());

        return usageTrafficList_processed;

    }

    @Override
    public List<Report> getUsageByAgeGroup(String fromDate, String toDate, String site, String functionalArea,
            String manager, String supervisor) {

        StoredProcedureQuery query = entityManager.createStoredProcedureQuery("getUsageByAgeGroups");
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
        List<Object[]> usageByAgeGroupsList_fromDb = query.getResultList();
        List<Report> usageByAgeGroupsList_processed = usageByAgeGroupsList_fromDb.stream().map(obj -> {

            Report r = new Report();
            r.setLabel((obj[0] == null || obj[0].toString().equalsIgnoreCase("")) ? null : obj[0].toString());
            r.setCounts(obj[1] == null || (Integer.parseInt(obj[1].toString()) == 0) ? 0
                    : Integer.parseInt(obj[1].toString()));
            return r;

        }).filter(r -> r.getLabel() != null && r.getCounts() != 0).collect(Collectors.toList());

        return usageByAgeGroupsList_processed;

    }

    @Override
    public List<Report> getUsageByGender(String fromDate, String toDate, String site, String functionalArea,
            String manager, String supervisor) {

        StoredProcedureQuery query = entityManager.createStoredProcedureQuery("getUsageByGender");
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
        List<Object[]> usageByGenderList_fromDb = query.getResultList();
        List<Report> usageByGenderList_processed = usageByGenderList_fromDb.stream().map(obj -> {

            Report r = new Report();
            r.setLabel((obj[0] == null || obj[0].toString().equalsIgnoreCase("")) ? null : obj[0].toString());
            r.setCounts(obj[1] == null || (Integer.parseInt(obj[1].toString()) == 0) ? 0
                    : Integer.parseInt(obj[1].toString()));
            return r;

        }).filter(r -> r.getLabel() != null && r.getCounts() != 0).collect(Collectors.toList());

        return usageByGenderList_processed;

    }

    @Override
    public List<Report> getUsageBySite(String fromDate, String toDate, String site, String functionalArea,
            String manager, String supervisor) {

        StoredProcedureQuery query = entityManager.createStoredProcedureQuery("getUsageBySite");
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
        List<Object[]> usageBySiteList_fromDb = query.getResultList();
        List<Report> usageBySiteList_processed = usageBySiteList_fromDb.stream().map(obj -> {

            Report r = new Report();
            r.setLabel((obj[0] == null || obj[0].toString().equalsIgnoreCase("")) ? null : obj[0].toString());
            r.setCounts(obj[1] == null || (Integer.parseInt(obj[1].toString()) == 0) ? 0
                    : Integer.parseInt(obj[1].toString()));
            return r;

        }).filter(r -> r.getLabel() != null && r.getCounts() != 0).collect(Collectors.toList());

        return usageBySiteList_processed;

    }

    @Override
    public List<Report> getUsageByJobProfile(String fromDate, String toDate, String site, String functionalArea,
            String manager, String supervisor) {

        StoredProcedureQuery query = entityManager.createStoredProcedureQuery("getUsageByJobLevel");
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
        List<Object[]> objectList = query.getResultList();
        List<Report> usageByJobLevel = objectList.stream().map(obj -> {

            Report r = new Report();
            r.setLabel((obj[0] == null || obj[0].toString().equalsIgnoreCase("")) ? null : obj[0].toString());
            r.setCounts(obj[1] == null || (Integer.parseInt(obj[1].toString()) == 0) ? 0
                    : Integer.parseInt(obj[1].toString()));
            return r;

        }).filter(r -> r.getLabel() != null && r.getCounts() != 0).collect(Collectors.toList());

        return usageByJobLevel;

    }

    @Override
    public List<ValidHire> getAllValidHiresByDate(String fromDate, String toDate) {

        StoredProcedureQuery query = entityManager.createStoredProcedureQuery("getAllValidHires");
        query.registerStoredProcedureParameter("from_date", String.class, ParameterMode.IN);
        query.registerStoredProcedureParameter("to_date", String.class, ParameterMode.IN);
        query.setParameter("from_date", fromDate);
        query.setParameter("to_date", toDate);

        List<ValidHire> validHireList = new ArrayList<ValidHire>();

        List<Object[]> objectList = query.getResultList();

        if (objectList != null) {

            validHireList = objectList.stream().map(obj -> {

                ValidHire validHire = new ValidHire();
                validHire.setEmp_id(obj[1].toString());
                validHire.setFirst_name(obj[2].toString());
                validHire.setLast_name(obj[3].toString());
                validHire.setPreviousJobProfile(obj[4].toString());
                validHire.setNewJobProfile(obj[5].toString());
                validHire.setWorkType(obj[6].toString());
                validHire.setMethod(obj[7].toString());
                validHire.setDateUsed(obj[8].toString());
                return validHire;

            }).collect(Collectors.toList());

        }

        return validHireList;

    }

    @Override
    public List<TidsSuppHire> getAllTidsSuppHireByDate(String fromDate, String toDate) {

        List<TidsSuppHire> tidsSuppHireViaSearchList = new ArrayList<TidsSuppHire>();
        List<TidsSuppHire> tidsSuppHireViaQuizList = new ArrayList<TidsSuppHire>();
        List<TidsSuppHire> tidsSuppHireByDateFinal = new ArrayList<TidsSuppHire>();

        StoredProcedureQuery query1 = entityManager.createStoredProcedureQuery("checkJobChangeViaSearch_tids_supp");
        query1.registerStoredProcedureParameter("from_date", String.class, ParameterMode.IN);
        query1.registerStoredProcedureParameter("to_date", String.class, ParameterMode.IN);
        query1.setParameter("from_date", fromDate);
        query1.setParameter("to_date", toDate);
        List<Object[]> objectList1 = query1.getResultList();

        if (objectList1 != null) {

            tidsSuppHireViaSearchList = objectList1.stream().map(obj -> {

                TidsSuppHire tidsSupp = new TidsSuppHire();
                tidsSupp.setEmp_id(obj[0].toString());
                tidsSupp.setFirst_name(obj[1].toString());
                tidsSupp.setLast_name(obj[2].toString());
                tidsSupp.setJob_title(obj[3].toString());
                tidsSupp.setPillar(obj[4].toString());
                tidsSupp.setChanged_date(obj[5].toString());
                tidsSupp.setResult_date(obj[6].toString());
                tidsSupp.setJob_profile(obj[7].toString());
                tidsSupp.setMethod(obj[8].toString());
                return tidsSupp;

            }).collect(Collectors.toList());

        }

        StoredProcedureQuery query2 = entityManager.createStoredProcedureQuery("checkJobChangeViaQuiz_tids_supp");
        query2.registerStoredProcedureParameter("from_date", String.class, ParameterMode.IN);
        query2.registerStoredProcedureParameter("to_date", String.class, ParameterMode.IN);
        query2.setParameter("from_date", fromDate);
        query2.setParameter("to_date", toDate);
        List<Object[]> objectList2 = query2.getResultList();

        if (objectList1 != null) {

            tidsSuppHireViaQuizList = objectList2.stream().map(obj -> {

                TidsSuppHire tidsSupp = new TidsSuppHire();
                tidsSupp.setEmp_id(obj[0].toString());
                tidsSupp.setFirst_name(obj[1].toString());
                tidsSupp.setLast_name(obj[2].toString());
                tidsSupp.setJob_title(obj[3].toString());
                tidsSupp.setPillar(obj[4].toString());
                tidsSupp.setChanged_date(obj[5].toString());
                tidsSupp.setResult_date(obj[6].toString());
                tidsSupp.setJob_profile(obj[7].toString());
                tidsSupp.setMethod(obj[8].toString());
                return tidsSupp;

            }).collect(Collectors.toList());

        }

        tidsSuppHireByDateFinal.addAll(tidsSuppHireViaSearchList);
        tidsSuppHireByDateFinal.addAll(tidsSuppHireViaQuizList);

        return tidsSuppHireByDateFinal;

    }

}
