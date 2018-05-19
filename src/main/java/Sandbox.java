import miles.server.Entities.Airline.Airline;
import miles.server.Entities.Airline.AirlineFactory;
import miles.server.Entities.Destination.Destination;
import miles.server.Entities.Destination.DestinationFactory;
import miles.server.Entities.GoalMatrix.GoalMatrix;
import miles.server.Entities.GoalMatrix.GoalsRelation;

public class Sandbox {
    static public void main(String[] arg) {
        System.out.println("start sandbox");

        Airline Brussels = AirlineFactory.getInstance().getAirline("Brussels Airlines");
        Destination from = DestinationFactory.getInstance().getDestination("South America");
        Destination to = DestinationFactory.getInstance().getDestination("Middle East / Caucasus /Central Africa");

        GoalsRelation gaolRelation = GoalMatrix.getInstance().getGaolRelation(Brussels, from, to);


        System.out.println("end of sandbox");

    }
}
