package miles.server;


import miles.server.Entities.Airline.Airline;
import miles.server.Entities.Airline.AirlineFactory;
import miles.server.Entities.Airports.Airports;
import miles.server.Entities.Destination.Destination;
import miles.server.Entities.Destination.DestinationFactory;
import miles.server.Entities.Goal.Goal;
import miles.server.Entities.GoalMatrix.GoalMatrix;
import miles.server.Entities.GoalMatrix.GoalsRelation;
import miles.server.Entities.Recommender.Recommender;
import miles.server.Entities.TakenFlight.TakenFlight;
import org.junit.Test;


public class GoalMatrixTest {

    @Test
    public void happyFlow() {

        Airline airline = AirlineFactory.getInstance().getAirline("dl");
        Destination fromDestination = DestinationFactory.getInstance().getDestination("jfk");
        Destination toDestination = DestinationFactory.getInstance().getDestination("tlv");

        GoalsRelation gaolRelation = GoalMatrix.getInstance().getGaolRelation(airline, fromDestination, toDestination);

        assert gaolRelation != null;
    }


}
