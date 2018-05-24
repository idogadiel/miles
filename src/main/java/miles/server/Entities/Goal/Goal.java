package miles.server.Entities.Goal;

/**
 * Created by gadiel on 12/10/2016.
 */

import org.json.JSONObject;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "Goals")
public class Goal {

    @Id
    private String id;
    String to;
    String from;
    SeatType seatType;
    String userId;

    @Override
    public String toString() {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("to", to);
        jsonObject.put("from", from);
        jsonObject.put("seatType", seatType);
        return jsonObject.toString();
    }

    public Goal() {
    }

    public Goal(String from, String to, int seatType) {
        this.to = to;
        this.from = from;
        this.seatType = getSeatTypeFromInt(seatType);
    }

    public Goal(String jsonUser) {
        JSONObject jsonObject = new JSONObject(jsonUser);
        this.to = (String) jsonObject.get("to");
        this.from = (String) jsonObject.get("from");
        int seatTypeInt = Integer.valueOf((String) jsonObject.get("seatType"));
        this.seatType = getSeatTypeFromInt(seatTypeInt);
    }

    SeatType getSeatTypeFromInt(int index){
        if(index==1) return SeatType.ECONOMY;
        if(index==2) return  SeatType.BUSINESS;
        if(index==3) return SeatType.FIRST_CLASS;
        return SeatType.ECONOMY;
    }

    public SeatType getSeatType() {
        return seatType;
    }

    public void setSeatType(SeatType seatType) {
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

    public enum SeatType {
        ECONOMY("Economy"),
        BUSINESS("Buisness"),
        FIRST_CLASS("First_class");

        private String text;

        SeatType(String text) {
            this.text = text;
        }

        public String getText() {
            return this.text;
        }

        public static SeatType fromString(String text) {
            for (SeatType b : SeatType.values()) {
                if (b.text.equalsIgnoreCase(text)) {
                    return b;
                }
            }
            return null;
        }
    }

}