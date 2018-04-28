package miles.server.MongoDB.Flight;

/**
 * Created by gadiel on 12/10/2016.
 */

import org.json.JSONObject;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Document(collection = "DesiredDestination")
public class DesiredDestination extends Destination{

    public DesiredDestination(){super();}


    public DesiredDestination(String to, String from, int seatType, String flightNum) {
        super(to, from, seatType, flightNum);
    }

    public DesiredDestination(String jsonUser) {
        super(jsonUser);
    }

    public String getFlightNum() {
        return flightNum;
    }

    public void setFlightNum(String flightNum) {
        this.flightNum = flightNum;
    }

    public int getSeatType() {
        return seatType;
    }

    public void setSeatType(int seatType) {
        this.seatType = seatType;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    @Override
    public String toString(){
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("to",to);
        jsonObject.put("from",from);
        jsonObject.put("seatType", seatType);
        return jsonObject.toString();
    }

}