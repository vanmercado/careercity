package com.careercitydashboard.Controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import com.careercitydashboard.Model.DateRange;
import com.careercitydashboard.Model.FunctionalArea;
import com.careercitydashboard.Model.HireDate;
import com.careercitydashboard.Model.Manager;
import com.careercitydashboard.Model.Report;
import com.careercitydashboard.Model.SiteFilter;
import com.careercitydashboard.Model.Supervisor;
import com.careercitydashboard.Model.TidsSuppHire;
import com.careercitydashboard.Model.ValidHire;
import com.careercitydashboard.Service.AnalyticsService;
import com.careercitydashboard.Service.FilterService;

/**
 * @author A.M.Castor x164856
 * @date June 16, 2020
 */

@RestController
@RequestMapping("/reporting")
public class AnalyticsController {

    @Autowired
    private AnalyticsService analyticsService;

    @Autowired
    private FilterService filterService;

    @RequestMapping(value = "/surveyresults/preferredsites", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public List<Report> showPreferrredSites(@RequestBody DateRange dateRange) {

        String start = dateRange.getFrom_date();
        String end = dateRange.getTo_date();
        String site = dateRange.getSite();
        String functional_area = dateRange.getFunctional_area();
        String manager = dateRange.getManager();
        String supervisor = dateRange.getSupervisor();
        return analyticsService.getAllPreferredSites(start, end, site, functional_area, manager, supervisor);

    }

    @RequestMapping(value = "/surveyresults/preferredworkcco", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public List<Report> showPreferrredWorkCCO(@RequestBody DateRange dateRange) {

        String start = dateRange.getFrom_date();
        String end = dateRange.getTo_date();
        String site = dateRange.getSite();
        String functional_area = dateRange.getFunctional_area();
        String manager = dateRange.getManager();
        String supervisor = dateRange.getSupervisor();
        return analyticsService.getAllPreferredWorkCCO(start, end, site, functional_area, manager, supervisor);

    }

    @RequestMapping(value = "/surveyresults/preferredworktids", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public List<Report> showPreferrredWorkTIDS(@RequestBody DateRange dateRange) {

        String start = dateRange.getFrom_date();
        String end = dateRange.getTo_date();
        String site = dateRange.getSite();
        String functional_area = dateRange.getFunctional_area();
        String manager = dateRange.getManager();
        String supervisor = dateRange.getSupervisor();
        return analyticsService.getAllPreferredWorkTIDS(start, end, site, functional_area, manager, supervisor);

    }

    @RequestMapping(value = "/surveyresults/preferredworksupport", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public List<Report> showPreferrredWorkSupport(@RequestBody DateRange dateRange) {

        String start = dateRange.getFrom_date();
        String end = dateRange.getTo_date();
        String site = dateRange.getSite();
        String functional_area = dateRange.getFunctional_area();
        String manager = dateRange.getManager();
        String supervisor = dateRange.getSupervisor();
        return analyticsService.getAllPreferredWorkSupport(start, end, site, functional_area, manager, supervisor);

    }

    @RequestMapping(value = "/surveyresults/bpoexperience", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public List<Report> showBPOExperience(@RequestBody DateRange dateRange) {

        String start = dateRange.getFrom_date();
        String end = dateRange.getTo_date();
        String site = dateRange.getSite();
        String functional_area = dateRange.getFunctional_area();
        String manager = dateRange.getManager();
        String supervisor = dateRange.getSupervisor();
        return analyticsService.getAllBPOExperience(start, end, site, functional_area, manager, supervisor);

    }

    @RequestMapping(value = "/surveyresults/preferredworkschedule", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public List<Report> showPreferredWorkSchedule(@RequestBody DateRange dateRange) {

        String start = dateRange.getFrom_date();
        String end = dateRange.getTo_date();
        String site = dateRange.getSite();
        String functional_area = dateRange.getFunctional_area();
        String manager = dateRange.getManager();
        String supervisor = dateRange.getSupervisor();
        return analyticsService.getAllPreferredWorkSchedule(start, end, site, functional_area, manager, supervisor);

    }

    @RequestMapping(value = "/surveyresults/fieldofstudy", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public List<Report> showAllFieldOfStudy(@RequestBody DateRange dateRange) {

        String start = dateRange.getFrom_date();
        String end = dateRange.getTo_date();
        String site = dateRange.getSite();
        String functional_area = dateRange.getFunctional_area();
        String manager = dateRange.getManager();
        String supervisor = dateRange.getSupervisor();
        return analyticsService.getAllFieldOfStudy(start, end, site, functional_area, manager, supervisor);

    }

    @RequestMapping(value = "/surveyresults/additionalskills", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public List<Report> showAllAdditionalSkills(@RequestBody DateRange dateRange) {

        String start = dateRange.getFrom_date();
        String end = dateRange.getTo_date();
        String site = dateRange.getSite();
        String functional_area = dateRange.getFunctional_area();
        String manager = dateRange.getManager();
        String supervisor = dateRange.getSupervisor();
        return analyticsService.getAllAdditionalSkills(start, end, site, functional_area, manager, supervisor);

    }

    @RequestMapping(value = "/clicks/preferredsites", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public List<Report> getPreferredSitesBasedOnClicks(@RequestBody DateRange dateRange) {

        String start = dateRange.getFrom_date();
        String end = dateRange.getTo_date();
        String site = dateRange.getSite();
        String functional_area = dateRange.getFunctional_area();
        String manager = dateRange.getManager();
        String supervisor = dateRange.getSupervisor();
        return analyticsService.getAllPreferredSites_ccClicks(start, end, site, functional_area, manager, supervisor);

    }

    @RequestMapping(value = "/clicks/preferredprograms", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public List<Report> getPreferredProgramsBasedOnClicks(@RequestBody DateRange dateRange) {

        String start = dateRange.getFrom_date();
        String end = dateRange.getTo_date();
        String site = dateRange.getSite();
        String functional_area = dateRange.getFunctional_area();
        String manager = dateRange.getManager();
        String supervisor = dateRange.getSupervisor();
        return analyticsService.getAllPreferredPrograms_ccClicks(start, end, site, functional_area, manager, supervisor);

    }

    @RequestMapping(value = "/clicks/indemandbusinesstypes", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public List<Report> getInDemandBusinessTypesBasedOnClicks(@RequestBody DateRange dateRange) {

        String start = dateRange.getFrom_date();
        String end = dateRange.getTo_date();
        String site = dateRange.getSite();
        String functional_area = dateRange.getFunctional_area();
        String manager = dateRange.getManager();
        String supervisor = dateRange.getSupervisor();
        return analyticsService.getAllInDemandBusinessTypes_ccClicks(start, end, site, functional_area, manager, supervisor);

    }

    @RequestMapping(value = "/clicks/indemandworktypecco", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public List<Report> getInDemandWorkTypeCCOBasedOnClicks(@RequestBody DateRange dateRange) {

        String start = dateRange.getFrom_date();
        String end = dateRange.getTo_date();
        String site = dateRange.getSite();
        String functional_area = dateRange.getFunctional_area();
        String manager = dateRange.getManager();
        String supervisor = dateRange.getSupervisor();
        return analyticsService.getAllInDemandWorkTypesCCO_ccClicks(start, end, site, functional_area, manager, supervisor);

    }

    @RequestMapping(value = "/clicks/preferredsupportjobs", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public List<Report> getPreferredSupportJobsBasedOnClicks(@RequestBody DateRange dateRange) {

        String start = dateRange.getFrom_date();
        String end = dateRange.getTo_date();
        String site = dateRange.getSite();
        String functional_area = dateRange.getFunctional_area();
        String manager = dateRange.getManager();
        String supervisor = dateRange.getSupervisor();
        return analyticsService.getAllPreferredSupportJobs_ccClicks(start, end, site, functional_area, manager,
                supervisor);

    }

    @RequestMapping(value = "/clicks/preferredsupportdept", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public List<Report> getPreferredSupportDeptBasedOnClicks(@RequestBody DateRange dateRange) {

        String start = dateRange.getFrom_date();
        String end = dateRange.getTo_date();
        String site = dateRange.getSite();
        String functional_area = dateRange.getFunctional_area();
        String manager = dateRange.getManager();
        String supervisor = dateRange.getSupervisor();
        return analyticsService.getAllPreferredSupportDept_ccClicks(start, end, site, functional_area, manager,
                supervisor);

    }

    @RequestMapping(value = "/clicks/indemandjobprofile", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public List<Report> getInDemandJobProfileBasedOnClicks(@RequestBody DateRange dateRange) {

        String start = dateRange.getFrom_date();
        String end = dateRange.getTo_date();
        String site = dateRange.getSite();
        String functional_area = dateRange.getFunctional_area();
        String manager = dateRange.getManager();
        String supervisor = dateRange.getSupervisor();
        return analyticsService.getAllInDemandJobProfiles_ccClicks(start, end, site, functional_area, manager, supervisor);

    }

    @RequestMapping(value = "/clicks/supportandtids", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public List<Report> getsupportandtidsBasedOnClicks(@RequestBody DateRange dateRange) {

        String start = dateRange.getFrom_date();
        String end = dateRange.getTo_date();
        String site = dateRange.getSite();
        String functional_area = dateRange.getFunctional_area();
        String manager = dateRange.getManager();
        String supervisor = dateRange.getSupervisor();
        return analyticsService.getAllClickCountsForCcoAndTids_ccClicks(start, end, site, functional_area, manager, supervisor);

    }

    @RequestMapping(value = "/usage/byusertraffic", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public List<Report> getUsageByTraffic(@RequestBody DateRange dateRange) {

        String start = dateRange.getFrom_date();
        String end = dateRange.getTo_date();
        String site = dateRange.getSite();
        String functional_area = dateRange.getFunctional_area();
        String manager = dateRange.getManager();
        String supervisor = dateRange.getSupervisor();
        return analyticsService.getUsageTraffic(start, end, site, functional_area, manager, supervisor);

    }

    @RequestMapping(value = "/usage/byagegroup", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public List<Report> getUsageByAgeGroup(@RequestBody DateRange dateRange) {

        String start = dateRange.getFrom_date();
        String end = dateRange.getTo_date();
        String site = dateRange.getSite();
        String functional_area = dateRange.getFunctional_area();
        String manager = dateRange.getManager();
        String supervisor = dateRange.getSupervisor();
        return analyticsService.getUsageByAgeGroup(start, end, site, functional_area, manager, supervisor);

    }

    @RequestMapping(value = "/usage/bygender", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public List<Report> getUsageByGender(@RequestBody DateRange dateRange) {

        String start = dateRange.getFrom_date();
        String end = dateRange.getTo_date();
        String site = dateRange.getSite();
        String functional_area = dateRange.getFunctional_area();
        String manager = dateRange.getManager();
        String supervisor = dateRange.getSupervisor();
        return analyticsService.getUsageByGender(start, end, site, functional_area, manager, supervisor);

    }

    @RequestMapping(value = "/usage/bysite", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public List<Report> getUsageBySite(@RequestBody DateRange dateRange) {

        String start = dateRange.getFrom_date();
        String end = dateRange.getTo_date();
        String site = dateRange.getSite();
        String functional_area = dateRange.getFunctional_area();
        String manager = dateRange.getManager();
        String supervisor = dateRange.getSupervisor();
        return analyticsService.getUsageBySite(start, end, site, functional_area, manager, supervisor);

    }

    @RequestMapping(value = "/usage/byjoblevel", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public List<Report> getUsageByJobLevel(@RequestBody DateRange dateRange) {

        String start = dateRange.getFrom_date();
        String end = dateRange.getTo_date();
        String site = dateRange.getSite();
        String functional_area = dateRange.getFunctional_area();
        String manager = dateRange.getManager();
        String supervisor = dateRange.getSupervisor();
        return analyticsService.getUsageByJobProfile(start, end, site, functional_area, manager, supervisor);

    }

    @RequestMapping(value = "/functionalareabydate", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public List<Report> getFunctionalAreaByDate(@RequestBody DateRange dateRange) {

        String start = dateRange.getFrom_date();
        String end = dateRange.getTo_date();
        return analyticsService.getAllFunctionalAreaByDate(start, end);

    }

    // Filters
    @RequestMapping(value = "/sites", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public List<SiteFilter> getAllSites() {

        return filterService.findAllSites();

    }

    @RequestMapping(value = "/functionalareabysite", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public List<FunctionalArea> getFunctionalAreaBysite(@RequestBody FunctionalArea functionalArea) {

        String site = functionalArea.getSite();
        return filterService.findFunctionalAreaBySite(site);

    }

    @RequestMapping(value = "/managerlistbyfunctionalarea", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public List<Manager> getManagerListByFunctionalArea(@RequestBody FunctionalArea functionalArea) {

        String functional_area = functionalArea.getFunctional_area();
        return filterService.findAllManagerByFunctionalArea(functional_area);

    }

    @RequestMapping(value = "/suplistbymanager", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public List<Supervisor> getSupervisorListByManager(@RequestBody Manager manager) {

        String managerIdList = manager.getMgr_id();
        return filterService.findAllSupervisorByManager(managerIdList);

    }

    @RequestMapping(value = "/validhires", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public List<ValidHire> getCCOHireByDate(@RequestBody HireDate hireDate) {

        String start = hireDate.getFrom_date();
        String end = hireDate.getTo_date();
        return analyticsService.getAllValidHiresByDate(start, end);

    }

    @RequestMapping(value = "/tidssupphirebydate", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public List<TidsSuppHire> getTidsSuppHireByDate(@RequestBody HireDate hireDate) {

        String start = hireDate.getFrom_date();
        String end = hireDate.getTo_date();
        return analyticsService.getAllTidsSuppHireByDate(start, end);

    }

}
