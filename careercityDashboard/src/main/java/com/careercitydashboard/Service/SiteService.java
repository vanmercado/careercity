package com.careercitydashboard.Service;

import java.util.List;

import com.careercitydashboard.Model.Site;

public interface SiteService {

    public List<Site> getAllSiteList();

    public void addSite(Site site);

    public void updateSite(Site site);

    public Site deleteSite(Integer SITE_ID);

    public Site getSiteById(Integer siteId);

}
