package com.careercitydashboard.Controller;

import com.careercitydashboard.Model.DateRange;
import com.careercitydashboard.Model.Report;
import com.careercitydashboard.Service.ExportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/exporting")
public class ExportController {

    @Autowired
    public ExportService exportService;

    @RequestMapping(value = "/usage/byusertraffic", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public List getUsageByTraffic(@RequestBody DateRange dateRange) {

        String start = dateRange.getFrom_date();
        String end = dateRange.getTo_date();
        String site = dateRange.getSite();
        String functional_area = dateRange.getFunctional_area();
        String manager = dateRange.getManager();
        String supervisor = dateRange.getSupervisor();
        return exportService.getUsageTrafficData(start, end, site, functional_area, manager, supervisor);

    }

    @RequestMapping(value = "/usage/byuserjoblevel", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public List getUsageByJobLevel(@RequestBody DateRange dateRange) {

        String start = dateRange.getFrom_date();
        String end = dateRange.getTo_date();
        String site = dateRange.getSite();
        String functional_area = dateRange.getFunctional_area();
        String manager = dateRange.getManager();
        String supervisor = dateRange.getSupervisor();
        String jobLevel = dateRange.getJobLevel();
        return exportService.getUsageByJobLevel(start, end, site, functional_area, manager, supervisor, jobLevel);

    }
}
