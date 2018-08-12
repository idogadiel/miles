package miles.server.Entities.GoalMatrix;

import com.google.common.collect.RowSortedTable;
import com.google.common.collect.TreeBasedTable;
import miles.server.Entities.Airline.Airline;
import miles.server.Entities.Airline.AirlineFactory;
import miles.server.Entities.Destination.Destination;
import miles.server.Entities.Destination.DestinationFactory;
import miles.server.Entities.Goal.Goal;

import java.io.*;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GoalCSVReader {

    public static Map<Airline, RowSortedTable<Destination, Destination, GoalsRelation>> getMatrixFromCSV() {


        InputStream in = GoalCSVReader.class.getClassLoader().getResourceAsStream("GoalMatrixCSV.csv");
        BufferedReader br = null;
        String line = "";
        String cvsSplitBy = ",";
        int linesCounter = 0;


        List<String> destinations = null;

        Map<Airline, RowSortedTable<Destination, Destination, GoalsRelation>> map = new HashMap<>();

        try {
            br = new BufferedReader(new InputStreamReader(in));
            while ((line = br.readLine()) != null) {

                // use comma as separator
                if (linesCounter == 0) {
                    destinations = Arrays.asList(line.split(cvsSplitBy.toLowerCase()));
                } else {
                    List<String> data = Arrays.asList(line.split(cvsSplitBy));
                    Airline rowAirline = AirlineFactory.getInstance().getAirline(data.get(0));
                    Destination rowDestination = DestinationFactory.getInstance().getDestinationFromString(data.get(1));
                    Goal.SeatType seatType = Goal.SeatType.fromString(data.get(14));


                    if (!map.containsKey(rowAirline)) map.put(rowAirline, TreeBasedTable.create());

                    for (int i = 2; i < data.size() - 1; i++) {
                        Destination colDestination = DestinationFactory.getInstance().getDestinationFromString(destinations.get(i));
                        addToMap(map, rowAirline, rowDestination, colDestination, data.get(i), seatType);
                    }
                }

                linesCounter++;
            }
            return map;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (br != null) {
                try {
                    br.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return null;
    }


    private static void addToMap(Map<Airline, RowSortedTable<Destination, Destination, GoalsRelation>> map, Airline rowAirline, Destination rowDestination, Destination colDestination, String val, Goal.SeatType type) {
        if (val.toLowerCase().equals("x")) return;
        if (!map.get(rowAirline).contains(rowDestination, colDestination)) {
            GoalsRelation relation = new GoalsRelation();
            map.get(rowAirline).put(rowDestination, colDestination, relation);
        }
        GoalsRelation goalsRelation = map.get(rowAirline).get(rowDestination, colDestination);

        switch (type) {
            case ECONOMY: {
                goalsRelation.economy = Double.valueOf(val);
                break;
            }
            case BUSINESS: {
                goalsRelation.business = Double.valueOf(val);
            }
            case FIRST_CLASS: {
                goalsRelation.firstClass = Double.valueOf(val);
            }
        }
    }
}