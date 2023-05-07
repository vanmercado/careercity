package com.careercitydashboard.Dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.careercitydashboard.Model.Site;

@Repository
public interface SiteRepo extends JpaRepository<Site, Integer> {

}
