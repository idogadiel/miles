package miles.server.Entities.Recommender;

import miles.server.Entities.Airline.Airline;
import miles.server.Entities.Destination.Destination;
import miles.server.Entities.Destination.DestinationFactory;
import miles.server.Entities.Goal.Goal;
import miles.server.Entities.GoalCrawler.GoalCrawler;
import miles.server.Entities.GoalCrawler.SabreCrawler;
import miles.server.Entities.GoalMatrix.GoalMatrix;
import miles.server.Entities.GoalMatrix.GoalsRelation;
import miles.server.Entities.TakenFlight.TakenFlight;
import miles.server.Entities.TakenFlightMatrix.TakenFlightMatrix;
import miles.server.Entities.TakenFlightMatrix.TakenFlightsRelation;

import java.util.*;

public class Recommender {

    Goal goal;
    List<TakenFlight> takenFlights;

    // airline -> point needed
    Map<Airline, GoalsRelation> map;

    public Recommender(Goal goal) {
        this.goal = goal;
        takenFlights = new ArrayList<>();
        createGaolsMap();
    }

    public Recommender addTakenFlight(TakenFlight takenFlight) {
        takenFlights.add(takenFlight);
        return this;
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
                            .mapToDouble(tf -> {
                                if (friendsOfAirlineThatFlyTheGoal.get(tf.getAirline()).getRelationType() == TakenFlightsRelation.RelationType.By_Miles) {
                                    Double multiplierByMiles = friendsOfAirlineThatFlyTheGoal.get(tf.getAirline()).getMilesByClass("A");
                                    return tf.getMiles() * multiplierByMiles;
                                } else {
                                    Double multiplierByCost = friendsOfAirlineThatFlyTheGoal.get(tf.getAirline()).getMultiplier();
                                    return tf.getCost() * multiplierByCost;
                                }
                            })
                            .sum();
                    GoalsRelation goalsRelation = map.get(airlineThatFlyTheGoal);
                    Double needed = goalsRelation.firstClass;   // need to be change according to the user will
                    return (agg / needed);
                }));

        return mostBeneficialAirline.get().getAirlineName();
    }

    public void createGaolsMap() {

        map = new HashMap<>();

        // create new goal crawler with specific crawler: sabre [some shitty API]
        GoalCrawler goalCrawler = new GoalCrawler(new SabreCrawler());

        // preform the actual crawl, get all the airline that fly from "tlv" to "ny"
        List<Airline> airlines = goalCrawler.doCrawl(goal.getFrom(), goal.getTo());


        airlines.forEach(airline -> {

            //todo: conversion, from specific city to "Destination"
            Destination fromDestination = DestinationFactory.getInstance().getDestination("europe");
            Destination toDestination = DestinationFactory.getInstance().getDestination("north america");

            GoalsRelation gaolRelation = GoalMatrix.getInstance().getGaolRelation(airline, fromDestination, toDestination);
            if (gaolRelation != null) {
                map.put(airline, gaolRelation);
            }
        });


    }


}
