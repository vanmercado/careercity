package com.careercitydashboard.ServiceImpl;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.careercitydashboard.Dao.TMPersonalDetailsRepo;
import com.careercitydashboard.Model.TMPersonalDetails;
import com.careercitydashboard.Service.TMPersonalDetailsService;

@Service
@Transactional
public class TMPersonalDetailsImpl implements TMPersonalDetailsService {

    @Autowired
    private TMPersonalDetailsRepo tmPersonalDetailsRepo;

    @Override
    public List<TMPersonalDetails> addAll(List<TMPersonalDetails> tmPersonalDetails) {

        // Remove all existing data that no data modification
        //for(TMPersonalDetails personalDetails: tmPersonalDetails) {
        //}
        return this.tmPersonalDetailsRepo.saveAll(tmPersonalDetails);

    }

    @Override
    public List<TMPersonalDetails> findAll() {

        return this.tmPersonalDetailsRepo.findAll();

    }

    @Override
    public TMPersonalDetails addTm(TMPersonalDetails tmPersonalDetails) {

        return this.tmPersonalDetailsRepo.save(tmPersonalDetails);

    }

    @Override
    public TMPersonalDetails findTmPersonalDetailsById(String empId) {

        return this.tmPersonalDetailsRepo.findTmPersonalDetailsByEmpId(empId);

    }

    @Override
    public TMPersonalDetails findByTmPersonalDetailsId(Integer tmPersonalDetailsId) {

        return this.tmPersonalDetailsRepo.findByPersonalDetailsId(tmPersonalDetailsId);

    }

}
