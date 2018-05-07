package miles.server.Entities.Airline;

import miles.server.Entities.TakenFlight.TakenFlight;
import org.json.JSONObject;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

public abstract class Airline {

    private Long id;
    private String airlineName;
    private String websiteAddress;


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

    // here can be some method like open http request etc ...

    public abstract String redeemSuggest(TakenFlight takenFlight);

    public abstract void redeemDo(TakenFlight takenFlight);

}
