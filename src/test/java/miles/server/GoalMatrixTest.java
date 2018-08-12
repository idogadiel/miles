package miles.server;


import miles.server.Entities.Airline.Airline;
import miles.server.Entities.Airline.AirlineFactory;
import miles.server.Entities.Destination.Destination;
import miles.server.Entities.Destination.DestinationFactory;
import miles.server.Entities.GoalMatrix.GoalMatrix;
import miles.server.Entities.GoalMatrix.GoalsRelation;
import org.junit.Test;


public class GoalMatrixTest {

    @Test
    public void happyFlow() {

        Airline airline = AirlineFactory.getInstance().getAirline("dl");
        Destination fromDestination = DestinationFactory.getInstance().getDestinationByAirPort("jfk");
        Destination toDestination = DestinationFactory.getInstance().getDestinationByAirPort("tlv");

        GoalsRelation gaolRelation = GoalMatrix.getInstance().getGaolRelation(airline, fromDestination, toDestination);

        assert gaolRelation != null;
    }


}
