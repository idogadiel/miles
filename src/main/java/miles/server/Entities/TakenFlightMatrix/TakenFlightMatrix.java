package miles.server.Entities.TakenFlightMatrix;


import com.google.common.collect.RowSortedTable;
import miles.server.Entities.Airline.Airline;
import miles.server.Utills.TakenFlightCSVReader;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.Map;

public class TakenFlightMatrix {

    // represent the excel Guy did

    private static RowSortedTable<Airline, Airline, TakenFlightsRelation> matrix;
    private static TakenFlightMatrix airlineMatrix;
    private static AnnotationConfigApplicationContext context;

    private TakenFlightMatrix() {
     //   context = new AnnotationConfigApplicationContext(AirlineConfig.class);
        matrix = TakenFlightCSVReader.getMatrixFromCSV();
    }

    private Map<Airline, TakenFlightsRelation> getMembers(Airline airline) {
        return matrix.row(airline);
    }

    public TakenFlightMatrix getInstance() {
        if (airlineMatrix == null) {
            airlineMatrix = new TakenFlightMatrix();
        }
        return airlineMatrix;
    }

}
