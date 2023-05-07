package com.telusinternational.careercity.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.telusinternational.careercity.dao.SearchedKeyRepository;
import com.telusinternational.careercity.model.SearchedKey;

@Service
@Transactional
public class SearchedKeyService {

    @Autowired
    private SearchedKeyRepository searchedKeyDAO;

    public void saveSearchedKey(SearchedKey searchedKey) {

        this.searchedKeyDAO.save(searchedKey);

    }

    public List<SearchedKey> searchedKeyList() {

        return this.searchedKeyDAO.findAll();

    }

}
