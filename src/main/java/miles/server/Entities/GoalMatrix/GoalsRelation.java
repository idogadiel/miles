package miles.server.Entities.GoalMatrix;

import miles.server.Entities.Goal.Goal;

public class GoalsRelation {
    public Double economy;
    public Double business;
    public Double firstClass;

    public Double getValue(Goal.SeatType saetType){
        if(saetType == Goal.SeatType.ECONOMY) return economy;
        if(saetType == Goal.SeatType.BUSINESS) return business;
        if(saetType == Goal.SeatType.FIRST_CLASS) return firstClass;
        return economy;
    }

}
