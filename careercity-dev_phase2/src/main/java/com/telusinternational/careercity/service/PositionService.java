package com.telusinternational.careercity.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
/*import org.springframework.data.repository.CrudRepository;*/
import org.springframework.stereotype.Service;

import com.telusinternational.careercity.dao.AccountClicksRepository;
import com.telusinternational.careercity.dao.PositionRepository;
/*import com.telusinternational.careercity.model.Account;*/
import com.telusinternational.careercity.model.Position;

@Service
@Transactional
public class PositionService {

    @Autowired
    private PositionRepository positionDao;
    @Autowired
    private AccountClicksRepository clickDao;

    public List<Position> getAllPositions() {

        return (List<Position>) this.positionDao.findAll();

    }

    public List<Position> getDsStAccount(String account) {

        return (List<Position>) this.positionDao.findAccount(account);

    }

    public Position getPositionById(Integer id) {

        return positionDao.findById(id).get();

    }

}
