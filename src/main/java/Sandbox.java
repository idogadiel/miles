import miles.server.Entities.Airports.GlobalAirportDatabaseReader;
import miles.server.Entities.Goal.Goal;
import miles.server.Entities.Recommender.Recommender;
import miles.server.Entities.TakenFlight.TakenFlight;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

import java.io.*;

public class Sandbox {
    static public void main(String[] arg) {
        System.out.println("start sandbox");

        Goal goal = new Goal("JFK", "TLV", 1);
        Recommender recommender = new Recommender(goal);

        TakenFlight t1 = new TakenFlight("tlv", "lax", "A", "EZY643", 234234L, 1000D);
        TakenFlight t2 = new TakenFlight("tlv", "jfk", "B", "EZY353", 234234L, 500D);

        System.out.println("Adding Flights");
        recommender.addTakenFlight(t1).addTakenFlight(t2);

        String recommend = recommender.recommend();
        System.out.println("Recommend: "+recommend);


        System.out.println("end of sandbox");
    }
}
