import miles.server.Entities.Airline.Airline;
import miles.server.Entities.Airline.AirlineFactory;
import miles.server.Entities.TakenFlightMatrix.TakenFlightMatrix;
import miles.server.Entities.TakenFlightMatrix.TakenFlightsRelation;

import java.util.Map;

public class Sandbox {
    static public void main(String[] arg) {
        System.out.println("start sandbox");

        Airline delta = AirlineFactory.getInstance().getAirline("delta");
        Map<Airline, TakenFlightsRelation> members = TakenFlightMatrix.getInstance().getMembers(delta);

        System.out.println("end of sandbox");

    }
}
