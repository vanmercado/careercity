package com.careercitydashboard.Service;



import java.util.List;

public interface ExportService {

    public List getUsageTrafficData(String fromDate, String toDate, String site, String functionalArea,
                                             String manager, String supervisor);

    public List getUsageByJobLevel(String fromDate, String toDate, String site, String functionalArea,
                                    String manager, String supervisor, String jobLevel);
}
