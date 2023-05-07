package com.careercitydashboard.Service;

import java.util.List;

import com.careercitydashboard.Model.Position;

public interface PositionService {

    public List<Position> getallPositions();

    public Position getPositionById(Integer Id);

    public Position savePosition(Position position);

    public void updatePosition(Position position);

    public Position getPositionMapping(Integer POSITION_ID);

    public void syncJobReqPosition();

}
