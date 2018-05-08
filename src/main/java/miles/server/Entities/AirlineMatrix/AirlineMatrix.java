package miles.server.Entities.AirlineMatrix;


import com.google.common.collect.RowSortedTable;
import miles.server.Entities.Airline.Airline;
import miles.server.Utills.CSVReader;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.Map;

public class AirlineMatrix {

    private static RowSortedTable<String, String, AirlinesRelation> matrix;
    private static AirlineMatrix airlineMatrix;
    private static AnnotationConfigApplicationContext context;

    private AirlineMatrix() {
        context = new AnnotationConfigApplicationContext(AirlineConfig.class);
        matrix = CSVReader.getMatrixFromCSV();
    }

    private Map<String, AirlinesRelation> getMembers(Airline airline) {
        return matrix.row(airline.getAirlineName());
    }

    public AirlineMatrix getInstance() {
        if (airlineMatrix == null) {
            airlineMatrix = new AirlineMatrix();
        }
        return airlineMatrix;
    }

}