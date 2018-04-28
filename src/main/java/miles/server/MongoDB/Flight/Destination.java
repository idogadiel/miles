package miles.server.MongoDB.Flight;

import org.json.JSONObject;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

public abstract class Destination {

    @Id
    private String id;
    String to;
    String from;
    String flightNum;  // probably unnecessary
    int seatType;
    String userId;
    Date date;

    public Destination(){}

    public Destination(String to, String from, int seatType, String flightNum) {
        this.to = to;
        this.from = from;
        this.seatType = seatType;
    }

    public Destination(String jsonUser) {
        JSONObject jsonObject = new JSONObject(jsonUser);
        this.to = (String) jsonObject.get("to");
        this.from = (String) jsonObject.get("from");
        this.seatType = Integer.valueOf((String)jsonObject.get("seatType"));
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
    public String toString() {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("to", to);
        jsonObject.put("from", from);
        jsonObject.put("seatType", seatType);
        return jsonObject.toString();
    }
}
