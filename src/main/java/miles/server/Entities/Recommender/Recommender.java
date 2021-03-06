package miles.server.Entities.Recommender;

import miles.server.Entities.Airline.Airline;
import miles.server.Entities.Destination.Destination;
import miles.server.Entities.Destination.DestinationFactory;
import miles.server.Entities.Goal.Goal;
import miles.server.Entities.GoalCrawler.GoalCrawler;
import miles.server.Entities.GoalCrawler.OpenFlightOrgCrawler;
import miles.server.Entities.GoalCrawler.SabreCrawler;
import miles.server.Entities.GoalMatrix.GoalMatrix;
import miles.server.Entities.GoalMatrix.GoalsRelation;
import miles.server.Entities.TakenFlight.TakenFlight;
import miles.server.Entities.TakenFlightMatrix.TakenFlightMatrix;
import miles.server.Entities.TakenFlightMatrix.TakenFlightsRelation;

import java.util.*;
import java.util.stream.Collectors;

public class Recommender {

    Goal goal;
    List<TakenFlight> takenFlights;

    // airline -> point needed
    Map<Airline, GoalsRelation> map;

    public Recommender(Goal goal) {
        setGoal(goal);
        takenFlights = new ArrayList<>();
        createGoalsMap();
    }

    public Recommender addTakenFlight(TakenFlight takenFlight) {
        this.takenFlights.add(takenFlight);
        return this;
    }

    public Recommender addTakenFlights(List<TakenFlight> takenFlights) {
        this.takenFlights.addAll(takenFlights);
        return this;
    }

    public void setGoal(Goal goal) {
        this.goal = goal;
    }

    public String recommend() {

        // foreach airline that do the flight -> find most beneficial
        Optional<Airline> mostBeneficialAirline = map.keySet().stream()
                .max(Comparator.comparing(airlineThatFlyTheGoal -> {
                    // get all the partners of the airline
                    Map<Airline, TakenFlightsRelation> friendsOfAirlineThatFlyTheGoal = TakenFlightMatrix.getInstance().getMembers(airlineThatFlyTheGoal);
                    //  aggregation of taken flights to specific airline that do the flight
                    double agg = takenFlights.stream()
                            //filter irrelevant taken flight (not partners)
                            .filter(tf -> friendsOfAirlineThatFlyTheGoal.keySet().contains(tf.getAirline()))
                            // map each one of the taken flights to the points/miles they are earning
                            .mapToDouble(takenFlight -> {
                                TakenFlightsRelation relation = friendsOfAirlineThatFlyTheGoal.get(takenFlight.getAirline());
                                if (relation.getRelationType() == TakenFlightsRelation.RelationType.By_Miles) {
                                    Double multiplierByMiles = relation.getMilesByClass(takenFlight.getSeatType());       // tf.getClass()
                                    return takenFlight.getMiles() * multiplierByMiles;
                                } else if (relation.getRelationType() == TakenFlightsRelation.RelationType.By_Cost) {
                                    Double multiplierByCost = relation.getMultiplier();
                                    return takenFlight.getCost() * multiplierByCost;
                                } else {
                                    Double fixed = relation.getFixed();
                                    return fixed;
                                }
                            })
                            .sum();
                    GoalsRelation goalsRelation = map.get(airlineThatFlyTheGoal);
                    Double needed = goalsRelation.getValue(goal.getSeatType());
                    System.out.println(airlineThatFlyTheGoal.getAirlineName()+" - agg / needed:  " + agg / needed);
                    return (agg / needed);
                }));

        return mostBeneficialAirline.get().getAirlineName();
    }

    public void createGoalsMap() {

        map = new HashMap<>();

        // todo: add more crawlers
        // create new goal crawler with specific crawler: sabre [some shitty API]
        GoalCrawler goalCrawler = new GoalCrawler(new SabreCrawler());

        // preform the actual crawl, get all the airline that fly from "tlv" to "ny"
        List<Airline> airlines = goalCrawler.doCrawl(goal.getFrom(), goal.getTo());
        if (airlines.size() < 10) {
            goalCrawler = new GoalCrawler(new OpenFlightOrgCrawler());
            airlines.addAll(goalCrawler.doCrawl(goal.getFrom(), goal.getTo()));
        }

        // remove duplicates
        airlines = airlines.stream().distinct().collect(Collectors.toList());

        airlines.forEach(airline -> {

            // get Destination object from factory
            Destination fromDestination = DestinationFactory.getInstance().getDestinationByAirPort(goal.getFrom());
            Destination toDestination = DestinationFactory.getInstance().getDestinationByAirPort(goal.getTo());

            GoalsRelation gaolRelation = GoalMatrix.getInstance().getGaolRelation(airline, fromDestination, toDestination);
            if (gaolRelation != null) {
                map.put(airline, gaolRelation);
            }
        });
    }
}
