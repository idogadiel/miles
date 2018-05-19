package miles.server.Entities.TakenFlightMatrix;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TakenFlightsRelation {

    private String SEPARATOR_CON = ";";
    private String rawData;
    Map<String, Double> byMilesMap;
    Map<String, Double> byCostMap;
    private RelationType relationType;

    private Double multiplier; // in case of By_Cost


    public TakenFlightsRelation(String data) {

        // init maps
        byMilesMap = new HashMap<>();
        byCostMap = new HashMap<>();

        this.rawData = data;

        // initial value: will be change at addDataToMaps()
        relationType = RelationType.By_Cost;

        addDataToMaps();
    }

    private void addDataToMaps() {
        List<String> separated = Arrays.asList(this.rawData.split(SEPARATOR_CON));

        separated.stream().filter(rawPair -> !rawPair.equals("")).forEach(rawPair -> {
            if (rawPair.contains("[")) {  // its by cost
                rawPair = rawPair.replace("[", "").replace("]", "");
                multiplier = getMultiplierValue(rawPair);
            } else {
                byMilesMap.put(getKey(rawPair), getValue(rawPair));
                relationType = RelationType.By_Miles;
            }
        });
    }

    private String getKey(String rawPair) {
        return rawPair.split(":")[0];
    }

    private Double getValue(String rawPair) {
        try {
            return Double.valueOf(rawPair.split(":")[1]);
        } catch (Exception e) {
            System.out.println("error on guy excel: " + rawPair + "  expected pattern: a:1.3");
        }
        return -1D;
    }

    public RelationType getRelationType() {
        return relationType;
    }

    public Double getMultiplier() {
        return multiplier;
    }

    private Double getMultiplierValue(String rawPair) {
        try {
            return Double.valueOf(rawPair);
        } catch (Exception e) {
            System.out.println("error on guy excel: " + rawPair + "  expected pattern: 3  maybe forgot [] ?");
        }
        return -1D;
    }

    public enum RelationType {
        By_Cost,
        By_Miles
    }

}




