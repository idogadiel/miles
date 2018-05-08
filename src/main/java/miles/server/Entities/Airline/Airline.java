package miles.server.Entities.Airline;

import miles.server.Entities.TakenFlight.TakenFlight;
import org.json.JSONObject;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

public abstract class Airline implements Comparable {

    protected Long id;
    protected String airlineName;
    protected String websiteAddress;


    public Airline(Long id, String airlineName, String websiteAddress) {
        this.id = id;
        this.airlineName = airlineName;
        this.websiteAddress = websiteAddress;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAirlineName() {
        return airlineName;
    }

    public void setAirlineName(String airlineName) {
        this.airlineName = airlineName;
    }

    public String getWebsiteAddress() {
        return websiteAddress;
    }

    public void setWebsiteAddress(String websiteAddress) {
        this.websiteAddress = websiteAddress;
    }

}