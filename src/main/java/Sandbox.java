import miles.server.Entities.Goal.Goal;
import miles.server.Entities.Recommender.Recommender;
import miles.server.Entities.TakenFlight.TakenFlight;

public class Sandbox {
    static public void main(String[] arg) {
        System.out.println("start sandbox");

        Goal goal = new Goal("JFK", "TLV", 1);
        Recommender recommender = new Recommender(goal);

        TakenFlight t1 = new TakenFlight("tlv", "amsterdam", 1, "123LGD", "343254mdfg54", "ido", 234234L, 1000D, "EZY", 600D);
        TakenFlight t2 = new TakenFlight("tlv", "berlin", 1, "123LGD", "343254mdfg54", "ido", 234234L, 500D, "EZY", 500D);
        recommender.addTakenFlight(t1).addTakenFlight(t2);

        String recommend = recommender.recommend();
        System.out.println(recommend);

        System.out.println("end of sandbox");
    }
}
