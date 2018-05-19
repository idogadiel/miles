import com.google.common.collect.RowSortedTable;
import miles.server.Entities.Airline.Airline;
import miles.server.Entities.Goal.Goal;
import miles.server.Entities.TakenFlightMatrix.TakenFlightCSVReader;
import miles.server.Entities.TakenFlightMatrix.TakenFlightsRelation;

import java.util.List;

public class Sandbox {
    static public void main(String[] arg) {
        System.out.println("start sandbox");

        RowSortedTable<Airline, Airline, TakenFlightsRelation> matrixFromCSV = TakenFlightCSVReader.getMatrixFromCSV();

        System.out.println("end of sandbox");

    }
}
