package com.careercitydashboard.Service;

import java.util.List;

import com.careercitydashboard.Model.TMJobDetails;

public interface TMJobDetailsService {

    public List<TMJobDetails> getAllJobDetails();

    public List<TMJobDetails> addJobDetailsList(List<TMJobDetails> tmJobDetailsList);

    public TMJobDetails addTmJobDetails(TMJobDetails tmJobDetails);

    public TMJobDetails findDuplicateTmByWdId(String wdId, String jobTitle, String functionalArea);

    public TMJobDetails findEmployeeWithDifferentJobTitleOrFuncArea(String wdId, String jobTitle,
            String functionalArea);

    public TMJobDetails findLatestJobDetailsByEmpId(String wdId);

    public TMJobDetails findByWdId(String wdId);

}
