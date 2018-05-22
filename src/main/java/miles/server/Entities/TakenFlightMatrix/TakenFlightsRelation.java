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
    private Double fixed;   // in case of By_Fixed


    public TakenFlightsRelation(String data) {

        // init maps
        byMilesMap = new HashMap<>();
        byCostMap = new HashMap<>();

        this.rawData = data;

        // initial value: will be change at addDataToMaps()
        relationType = RelationType.By_Cost;

        addDataToMaps();
    }

    public Double getMilesByClass(String _class) {
        //todo: un mock it
        Map.Entry<String, Double> entry = byMilesMap.entrySet().iterator().next();
        return entry.getValue();
    }


    private void addDataToMaps() {
        List<String> separated = Arrays.asList(this.rawData.split(SEPARATOR_CON));

        separated.stream().filter(rawPair -> !rawPair.equals("")).forEach(rawValue -> {
            if (rawValue.contains("[")) {  // its by cost
                rawValue = rawValue.replace("[", "").replace("]", "");
                multiplier = getValueAsDouble(rawValue);
            }

            else if (rawValue.contains("{")) {  // its by cost
                rawValue = rawValue.replace("{", "").replace("}", "");
                fixed = getValueAsDouble(rawValue);
                relationType = RelationType.By_Fixed;
            }

            else {
                byMilesMap.put(getKey(rawValue), getValue(rawValue));
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


    public Double getFixed() {
        return fixed;
    }

    public void setFixed(Double fixed) {
        this.fixed = fixed;
    }

    private Double getValueAsDouble(String rawPair) {
        try {
            return Double.valueOf(rawPair);
        } catch (Exception e) {
            System.out.println("error on guy excel: " + rawPair + "  expected pattern: 3  maybe forgot [] ?");
        }
        return -1D;
    }

    public enum RelationType {
        By_Cost,
        By_Miles,
        By_Fixed
    }

}




