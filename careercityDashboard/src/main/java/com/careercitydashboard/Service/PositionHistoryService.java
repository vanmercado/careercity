package com.careercitydashboard.Service;

import java.util.List;

import com.careercitydashboard.Model.PositionHistory;

public interface PositionHistoryService {

    public List<PositionHistory> getAllPositionHistory();

    public List<PositionHistory> getPositionHistoryByPositionId(Integer positionId);

    public PositionHistory savePositionSession(PositionHistory positionHistory);

}
