package com.careercitydashboard.ServiceImpl;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.careercitydashboard.Dao.TMJobDetailsRepo;
import com.careercitydashboard.Model.TMJobDetails;
import com.careercitydashboard.Service.TMJobDetailsService;

@Service
@Transactional
public class TMJobDetailsServiceImpl implements TMJobDetailsService {

    @Autowired
    private TMJobDetailsRepo tmJobDetailsRepo;

    @Override
    public List<TMJobDetails> getAllJobDetails() {

        return this.tmJobDetailsRepo.findAll();

    }

    @Override
    public List<TMJobDetails> addJobDetailsList(List<TMJobDetails> tmJobDetailsList) {

        return this.tmJobDetailsRepo.saveAll(tmJobDetailsList);

    }

    @Override
    public TMJobDetails addTmJobDetails(TMJobDetails tmJobDetails) {

        return this.tmJobDetailsRepo.save(tmJobDetails);

    }

    @Override
    public TMJobDetails findDuplicateTmByWdId(String wdId, String jobTitle, String functionalArea) {

        return this.tmJobDetailsRepo.findDuplicateTmByWkId(wdId, jobTitle, functionalArea);

    }

    @Override
    public TMJobDetails findEmployeeWithDifferentJobTitleOrFuncArea(String wdId, String jobTitle,
            String functionalArea) {

        return this.tmJobDetailsRepo.findExistingWdWithDifferentJobTitleOrFuncArea(wdId, jobTitle, functionalArea);

    }

    @Override
    public TMJobDetails findLatestJobDetailsByEmpId(String wdId) {

        return this.tmJobDetailsRepo.findLatestJobDetailsByEmpId(wdId);

    }

    @Override
    public TMJobDetails findByWdId(String wdId) {

        return this.tmJobDetailsRepo.findByWdId(wdId);

    }

}
