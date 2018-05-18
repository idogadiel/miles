package miles.server.Entities.GoalMatrix;


import com.google.common.collect.RowSortedTable;
import miles.server.Entities.Airline.Airline;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.Map;

public class GoalMatrix {

    // represent the excel Ori did

    private static RowSortedTable<Airline, Airline, GoalsRelation> matrix;
    private static GoalMatrix airlineMatrix;
    private static AnnotationConfigApplicationContext context;

    private GoalMatrix() {
        matrix = GoalCSVReader.getMatrixFromCSV();
    }

    private Map<Airline, GoalsRelation> getMembers(Airline airline) {
        return matrix.row(airline);
    }

    public GoalMatrix getInstance() {
        if (airlineMatrix == null) {
            airlineMatrix = new GoalMatrix();
        }
        return airlineMatrix;
    }

}
