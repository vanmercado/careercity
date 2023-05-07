package com.careercitydashboard.ServiceImpl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.StoredProcedureQuery;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.careercitydashboard.Dao.PositionHistoryRepo;
import com.careercitydashboard.Model.PositionHistory;
import com.careercitydashboard.Service.PositionHistoryService;

@Service
@Transactional
public class PositionHistoryServiceImpl implements PositionHistoryService {

    @Autowired
    private PositionHistoryRepo positionHistoryRepo;

    @Override
    public List<PositionHistory> getAllPositionHistory() {

        return (List<PositionHistory>) this.positionHistoryRepo.findAllPositionHistoriesByLatest();

    }

    @Override
    public List<PositionHistory> getPositionHistoryByPositionId(Integer positionId) {

        return this.positionHistoryRepo.findPositionHistoriesById(positionId);

    }

    @Override
    public PositionHistory savePositionSession(PositionHistory positionHistory) {

        return this.positionHistoryRepo.save(positionHistory);

    }

}
