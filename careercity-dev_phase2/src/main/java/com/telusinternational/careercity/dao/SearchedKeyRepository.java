package com.telusinternational.careercity.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.telusinternational.careercity.model.SearchedKey;

public interface SearchedKeyRepository extends JpaRepository<SearchedKey, Integer> {

}
