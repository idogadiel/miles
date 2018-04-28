package miles.server.MongoDB.Flight;

/**
 * Created by gadiel on 12/10/2016.
 */

import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "VisitedDestination")
public class VisitedDestination extends Destination {

    boolean redeemed;
    String redeemedTo;

    public VisitedDestination(){super();}

    public VisitedDestination(String to, String from, int seatType, String flightNum) {
        super(to, from, seatType, flightNum);
    }

    public VisitedDestination(String jsonUser) {
        super(jsonUser);
    }

    public boolean isRedeemed() {
        return redeemed;
    }

    public void setRedeemed(boolean redeemed) {
        this.redeemed = redeemed;
    }

    public String getRedeemedTo() {
        return redeemedTo;
    }

    public void setRedeemedTo(String redeemedTo) {
        this.redeemedTo = redeemedTo;
    }

}