package miles.server.Entities.TakenFlightMatrix;


import com.google.common.collect.RowSortedTable;
import miles.server.Entities.Airline.Airline;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.Map;

public class TakenFlightMatrix {

    // represent the excel Guy did

    private static RowSortedTable<Airline, Airline, TakenFlightsRelation> matrix;
    private static TakenFlightMatrix takenFlightMatrix; // singleton object

    private TakenFlightMatrix() {
        matrix = TakenFlightCSVReader.getMatrixFromCSV();
    }

    public Map<Airline, TakenFlightsRelation> getMembers(Airline airline) {
        return matrix.row(airline);
    }

    public static TakenFlightMatrix getInstance() {
        if (takenFlightMatrix == null) {
            takenFlightMatrix = new TakenFlightMatrix();
        }
        return takenFlightMatrix;
    }

}
