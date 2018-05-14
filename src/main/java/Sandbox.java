import miles.server.Entities.Goal.Goal;

import java.util.List;

public class Sandbox {
    static public void main(String[] arg) {
        System.out.println("start sandbox");

        Goal originalGoal = new Goal();
        originalGoal.setTo("TLV");
        originalGoal.setFrom("JFK");

        List<Goal> concreteGoals = originalGoal.getConcreteGoals();

        System.out.println("end of sandbox");

    }
}
