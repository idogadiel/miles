package miles.server.Entities.Airports;

import org.json.JSONObject;

public class Airports {

    private static Airports instance;
    private static JSONObject airportsJson;

    private Airports() {
        this.airportsJson = GlobalAirportDatabaseReader.getMatrixFromCSV();
    }

    public static Airports getInstance() {
        if (instance == null) {
            instance = new Airports();
        }
        return instance;
    }

    private Double getLongitude(String airportName) {
        if (airportsJson.keySet().contains(airportName)) {
            return Double.valueOf((String) ((JSONObject) airportsJson.get(airportName)).get("longitude"));
        }
        return -1D;
    }


    private Double getLatitude(String airportName) {
        if (airportsJson.keySet().contains(airportName)) {
            return Double.valueOf((String) ((JSONObject) airportsJson.get(airportName)).get("latitude"));
        }
        return -1D;
    }

    public Double getDistanceBetweenAirports(String from, String to) {

        double lat1 = Airports.getInstance().getLatitude(from);
        double lon1 = Airports.getInstance().getLongitude(from);

        double lat2 = Airports.getInstance().getLatitude(to);
        double lon2 = Airports.getInstance().getLongitude(to);

        double theta = lon1 - lon2;
        double dist = Math.sin(deg2rad(lat1)) * Math.sin(deg2rad(lat2)) + Math.cos(deg2rad(lat1)) * Math.cos(deg2rad(lat2)) * Math.cos(deg2rad(theta));
        dist = Math.acos(dist);
        dist = rad2deg(dist);
        dist = dist * 60 * 1.1515;

        return (dist);
    }

    private double deg2rad(double deg) {
        return (deg * Math.PI / 180.0);
    }

    private double rad2deg(double rad) {
        return (rad * 180.0 / Math.PI);
    }


}

