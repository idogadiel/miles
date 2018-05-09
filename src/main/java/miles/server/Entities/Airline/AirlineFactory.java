package miles.server.Entities.Airline;

import java.util.HashMap;
import java.util.Map;

public class AirlineFactory {

    private Map<String, Airline> map;
    private  static AirlineFactory instance;

    public static AirlineFactory getInstance(){
        if(instance == null){
            instance = new AirlineFactory();
        }
        return instance;
    }

    private AirlineFactory() {
        map = new HashMap();
    }

    public Airline getAirline(String airline) {
        if (!map.containsKey(airline)) {
            map.put(airline, new Airline(airline));
        }
        return map.get(airline);
    }

}
