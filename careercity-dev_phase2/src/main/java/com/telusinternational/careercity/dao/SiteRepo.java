/**
 * 
 */
package com.telusinternational.careercity.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.telusinternational.careercity.model.Site;

@Repository
public interface SiteRepo extends CrudRepository<Site, Integer> {

}
