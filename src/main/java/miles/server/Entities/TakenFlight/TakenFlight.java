package miles.server.Entities.TakenFlight;

/**
 * Created by gadiel on 12/10/2016.
 */

import org.json.JSONObject;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;


@Document(collection = "TakenFlights")
public class TakenFlight {

    @Id
    private String id;
    String to;
    String from;
    int seatType;
    String userId;
    boolean redeemed;
    String redeemedTo;

    String flightNumber;
    String ticketNumber;
    String nameOnTicket;
    Long dateOfFlight;



    public TakenFlight() {
    }

    public TakenFlight(String to, String from, int seatType, String flightNumber , String ticketNumber, String nameOnTicket, Long date) {
        setFlightNumber(flightNumber);
        setNameOnTicket(nameOnTicket);
        setTicketNumber(ticketNumber);
        setDateOfFlight(date);
    }

    public TakenFlight(String jsonUser) {
     //   super(jsonUser); maybe inheritance is not a good idea
        JSONObject jsonObject = new JSONObject(jsonUser);
        this.flightNumber = (String) jsonObject.get("flightNumber");
        this.nameOnTicket = (String) jsonObject.get("nameOnTicket");
        this.ticketNumber = (String) jsonObject.get("ticketNumber");
     //   this.date = (Date) jsonObject.get("date"); // to be implemented later
        this.dateOfFlight = System.currentTimeMillis(); // mock

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

    public String getFlightNumber() {
        return flightNumber;
    }

    public void setFlightNumber(String flightNumber) {
        this.flightNumber = flightNumber;
    }

    public String getTicketNumber() {
        return ticketNumber;
    }

    public void setTicketNumber(String ticketNumber) {
        this.ticketNumber = ticketNumber;
    }

    public String getNameOnTicket() {
        return nameOnTicket;
    }

    public void setNameOnTicket(String nameOnTicket) {
        this.nameOnTicket = nameOnTicket;
    }

    public Long getDateOfFlight() {
        return dateOfFlight;
    }

    public void setDateOfFlight(Long dateOfFlight) {
        this.dateOfFlight = dateOfFlight;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    @Override
    public String toString() {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("to", to);
        jsonObject.put("from", from);
        jsonObject.put("seatType", seatType);

        jsonObject.put("flightNumber", flightNumber);
        jsonObject.put("ticketNumber", ticketNumber);
        jsonObject.put("nameOnTicket", nameOnTicket);
        jsonObject.put("dateOfFlight", dateOfFlight);
        return jsonObject.toString();
    }

}