package com.careercitydashboard.Service;

import java.util.List;

import com.careercitydashboard.Model.TMPersonalDetails;

public interface TMPersonalDetailsService {

    public List<TMPersonalDetails> findAll();

    public List<TMPersonalDetails> addAll(List<TMPersonalDetails> tmPersonalDetails);

    public TMPersonalDetails addTm(TMPersonalDetails tmPersonalDetails);

    public TMPersonalDetails findTmPersonalDetailsById(String empId);

    public TMPersonalDetails findByTmPersonalDetailsId(Integer tmPersonalDetailsId);

}
