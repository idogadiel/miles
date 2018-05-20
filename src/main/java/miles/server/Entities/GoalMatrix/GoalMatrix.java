package miles.server.Entities.GoalMatrix;


import com.google.common.collect.RowSortedTable;
import miles.server.Entities.Airline.Airline;
import miles.server.Entities.Destination.Destination;

import java.util.Map;

public class GoalMatrix {

    // represent the excel Ori did

    private static Map<Airline, RowSortedTable<Destination, Destination, GoalsRelation>> matrixMap;
    private static GoalMatrix airlineMatrix; // singleton

    private GoalMatrix() {
        matrixMap = GoalCSVReader.getMatrixFromCSV();
    }

    public GoalsRelation getGaolRelation(Airline airline, Destination from, Destination to) {
        try {
            return matrixMap.get(airline).get(from, to);
        } catch (NullPointerException ex) {
            return null;
        }
    }

    public static GoalMatrix getInstance() {
        if (airlineMatrix == null) {
            airlineMatrix = new GoalMatrix();
        }
        return airlineMatrix;
    }

}
