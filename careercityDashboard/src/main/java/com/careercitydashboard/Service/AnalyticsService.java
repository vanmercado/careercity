package com.careercitydashboard.Service;

import java.util.List;
import com.careercitydashboard.Model.Report;
import com.careercitydashboard.Model.TidsSuppHire;
import com.careercitydashboard.Model.ValidHire;

/**
 * @author A.M.Castor x164856
 * @date June 16, 2020
 */

public interface AnalyticsService {

    public List<Report> getAllFunctionalAreaByDate(String fromDate, String toDate);

    public List<Report> getAllPreferredSites(String fromDate, String toDate, String site, String functionalArea,
            String manager, String supervisor);

    public List<Report> getAllPreferredWorkCCO(String fromDate, String toDate, String site, String functionalArea,
            String manager, String supervisor);

    public List<Report> getAllPreferredWorkTIDS(String fromDate, String toDate, String site, String functionalArea,
            String manager, String supervisor);

    public List<Report> getAllPreferredWorkSupport(String fromDate, String toDate, String site, String functionalArea,
            String manager, String supervisor);

    public List<Report> getAllBPOExperience(String fromDate, String toDate, String site, String functionalArea,
            String manager, String supervisor);

    public List<Report> getAllPreferredWorkSchedule(String fromDate, String toDate, String site, String functionalArea,
            String manager, String supervisor);

    public List<Report> getAllFieldOfStudy(String fromDate, String toDate, String site, String functionalArea,
            String manager, String supervisor);

    public List<Report> getAllAdditionalSkills(String fromDate, String toDate, String site, String functionalArea,
            String manager, String supervisor);

    public List<Report> getAllPreferredSites_ccClicks(String fromDate, String toDate, String site,
            String functionalArea, String manager, String supervisor);

    public List<Report> getAllPreferredPrograms_ccClicks(String fromDate, String toDate, String site,
            String functionalArea, String manager, String supervisor);

    public List<Report> getAllInDemandBusinessTypes_ccClicks(String fromDate, String toDate, String site,
            String functionalArea, String manager, String supervisor);

    public List<Report> getAllInDemandWorkTypesCCO_ccClicks(String fromDate, String toDate, String site,
            String functionalArea, String manager, String supervisor);

    public List<Report> getAllPreferredSupportDept_ccClicks(String fromDate, String toDate, String site,
            String functionalArea, String manager, String supervisor);

    public List<Report> getAllPreferredSupportJobs_ccClicks(String fromDate, String toDate, String site,
            String functionalArea, String manager, String supervisor);

    public List<Report> getAllInDemandJobProfiles_ccClicks(String fromDate, String toDate, String site,
            String functionalArea, String manager, String supervisor);

    public List<Report> getAllClickCountsForCcoAndTids_ccClicks(String fromDate, String toDate, String site,
            String functionalArea, String manager, String supervisor);

    public List<Report> getUsageTraffic(String fromDate, String toDate, String site, String functionalArea,
            String manager, String supervisor);

    public List<Report> getUsageByAgeGroup(String fromDate, String toDate, String site, String functionalArea,
            String manager, String supervisor);

    public List<Report> getUsageByGender(String fromDate, String toDate, String site, String functionalArea,
            String manager, String supervisor);

    public List<Report> getUsageBySite(String fromDate, String toDate, String site, String functionalArea,
            String manager, String supervisor);

    public List<Report> getUsageByJobProfile(String fromDate, String toDate, String site, String functionalArea,
            String manager, String supervisor);

    public List<ValidHire> getAllValidHiresByDate(String fromDate, String toDate);

    public List<TidsSuppHire> getAllTidsSuppHireByDate(String fromDate, String toDate);

}
