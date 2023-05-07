/**
 * 
 */
package com.telusinternational.careercity.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.telusinternational.careercity.dao.SiteRepo;
import com.telusinternational.careercity.model.Site;

@Service
@Transactional
public class SiteService {

    @Autowired
    private SiteRepo siteRepo;

    public List<Site> getAllSite() {

        return (List<Site>) this.siteRepo.findAll();

    }

}
