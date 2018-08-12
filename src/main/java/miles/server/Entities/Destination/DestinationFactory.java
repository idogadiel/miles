package miles.server.Entities.Destination;

import java.util.HashMap;
import java.util.Map;

public class DestinationFactory {

    private Map<String, Destination> map;
    private static DestinationFactory instance;

    public static DestinationFactory getInstance() {
        if (instance == null) {
            instance = new DestinationFactory();
        }
        return instance;
    }

    private DestinationFactory() {
        map = new HashMap();
    }

    public Destination getDestinationByAirPort(String rawAirport) {
        String rawDestinationString = DestinationConvertor.covertAirportToGeographicalArea(rawAirport);
        String destination = rawDestinationString.toLowerCase();
        if (!map.containsKey(destination)) {
            map.put(destination, new Destination(destination));
        }
        return map.get(destination);
    }

    public Destination getDestinationFromString(String rawDestinationString) {
        String destination = rawDestinationString.toLowerCase();
        if (!map.containsKey(destination)) {
            map.put(destination, new Destination(destination));
        }
        return map.get(destination);
    }

}
