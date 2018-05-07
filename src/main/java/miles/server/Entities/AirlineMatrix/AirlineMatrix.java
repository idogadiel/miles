package miles.server.Entities.AirlineMatrix;


import com.google.common.collect.RowSortedTable;
import com.google.common.collect.TreeBasedTable;
import miles.server.Entities.Airline.Airline;
import miles.server.Entities.Airline.Delta;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.Map;

public class AirlineMatrix {

    private static RowSortedTable<Airline, Airline, AirlinesRelation> matrix;
    private static AirlineMatrix airlineMatrix;
    private static AnnotationConfigApplicationContext context;

    private AirlineMatrix() {
        context = new AnnotationConfigApplicationContext(AirlineConfig.class);
        matrix = TreeBasedTable.create();
        updateMatrix();
    }

    public AirlineMatrix getInstance() {
        if (airlineMatrix == null) {
            airlineMatrix = new AirlineMatrix();
        }
        return airlineMatrix;
    }

    private void updateMatrix() {
        matrix.put(context.getBean(Delta.class), context.getBean(Delta.class), new AirlinesRelation(4));
        matrix.put(context.getBean(Delta.class), context.getBean(Delta.class), new AirlinesRelation(220));
        matrix.put(context.getBean(Delta.class), context.getBean(Delta.class), new AirlinesRelation(20));
    }

    private Map<Airline, AirlinesRelation> getMembers(Airline airline) {
        return matrix.row(airline);
    }


}
