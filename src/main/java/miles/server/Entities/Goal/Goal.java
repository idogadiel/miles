package miles.server.Entities.Goal;

/**
 * Created by gadiel on 12/10/2016.
 */

import miles.server.Entities.GoalCrawler.SabreCrawler;
import org.json.JSONObject;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Document(collection = "Goals")
public class Goal {

    @Id
    private String id;
    String to;
    String from;
    int seatType;
    String userId;

    @Override
    public String toString(){
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("to",to);
        jsonObject.put("from",from);
        jsonObject.put("seatType", seatType);
        return jsonObject.toString();
    }

    public Goal() {
    }

    public Goal(String to, String from, int seatType) {
        this.to = to;
        this.from = from;
        this.seatType = seatType;
    }

    public Goal(String jsonUser) {
        JSONObject jsonObject = new JSONObject(jsonUser);
        this.to = (String) jsonObject.get("to");
        this.from = (String) jsonObject.get("from");
        this.seatType = Integer.valueOf((String)jsonObject.get("seatType"));
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

}