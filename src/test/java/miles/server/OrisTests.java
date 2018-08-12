package miles.server;


import miles.server.Entities.Airports.Airports;
import miles.server.Entities.Destination.Destination;
import miles.server.Entities.Goal.Goal;
import miles.server.Entities.Recommender.Recommender;
import miles.server.Entities.TakenFlight.TakenFlight;
import org.junit.Test;


public class OrisTests {

    @Test
    public void testForMiles() {
        TakenFlight t1 = new TakenFlight("tlv", "lax", "A", "EZY643", 234234L, 1000D);
        assert t1.getMiles()> 1 && t1.getMiles() < 10000000000L;
        assert t1.getCost() > 0 && t1.getCost() < 1000000000000L;
        assert t1.getDateOfFlight() > 0;
        assert t1.getFlightNumber() != null;
        assert t1.getSeatType() != null;
    }

    @Test
    public void testForGoals() {
        Goal g1 = new Goal("JFK", "TLV", 1);
        assert g1.getFrom() != null;
        assert g1.getSeatType() != null;
        assert g1.getTo() != null;
    }

    @Test
    public void testForRecommender() {
        Goal goal = new Goal("JFK", "TLV", 1);
        Recommender recommender = new Recommender(goal);
        assert recommender.recommend() != null;
    }

    @Test
    public void testForDistance() {
        TakenFlight t1 = new TakenFlight("tlv", "lax", "A", "EZY643", 234234L, 1000D);
        Double dist = Airports.getInstance().getDistanceBetweenAirports(t1.getTo().toUpperCase(), t1.getFrom().toUpperCase());
        assert dist > 0;
    }

}
