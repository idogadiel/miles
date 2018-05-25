package miles.server.Entities.TakenFlight;

/**
 * Created by gadiel on 12/10/2016.
 */

import miles.server.Entities.Airline.Airline;
import miles.server.Entities.Airline.AirlineFactory;
import miles.server.Entities.Airports.Airports;
import miles.server.Entities.GoalMatrix.GoalsRelation;
import org.json.JSONObject;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;


@Document(collection = "TakenFlights")
public class TakenFlight {

    @Id
    private String id;
    String to;
    String from;
    SeatType seatType;
    String userId;
    boolean redeemed;
    String redeemedTo;

    String flightNumber;
    String ticketNumber;
    String nameOnTicket;
    Long dateOfFlight;
    Double cost;
    Double miles;
    Airline airline;


    public TakenFlight() {
    }

    public TakenFlight(String to, String from, String seatType, String flightNumber, String ticketNumber, String nameOnTicket, Long date, Double cost) {
        setFlightNumber(flightNumber);
        setNameOnTicket(nameOnTicket);
        setTicketNumber(ticketNumber);
        setDateOfFlight(date);
        setCost(cost);
        setMiles(Airports.getInstance().getDistanceBetweenAirports(to.toUpperCase(), from.toUpperCase()));
        setAirline(AirlineFactory.getInstance().getAirline(getAirlineFromFlightNumber(flightNumber)));
        setSeatType(SeatType.fromString(seatType));
    }

    public TakenFlight(String to, String from, String seatType, String flightNumber, Long date, Double cost) {
        setFlightNumber(flightNumber);
        setDateOfFlight(date);
        setCost(cost);
        setMiles(Airports.getInstance().getDistanceBetweenAirports(to.toUpperCase(), from.toUpperCase()));
        setAirline(AirlineFactory.getInstance().getAirline(getAirlineFromFlightNumber(flightNumber)));
        setSeatType(SeatType.fromString(seatType));
    }

    public TakenFlight(String jsonUser) {
        JSONObject jsonObject = new JSONObject(jsonUser);
        this.from         = (String) jsonObject.get("from");
        this.to           = (String) jsonObject.get("to");
        this.flightNumber = (String) jsonObject.get("flightNumber");
        this.nameOnTicket = (String) jsonObject.get("nameOnTicket");
        this.ticketNumber = (String) jsonObject.get("ticketNumber");
        this.dateOfFlight = System.currentTimeMillis(); // mock
        this.seatType = SeatType.fromString(((String) jsonObject.get("seatType")).toUpperCase());
//        this.seatType = SeatType.A;     // to fix according to the json -> dandan
    }

    private String getAirlineFromFlightNumber(String flightNumber) {
        return flightNumber.replaceAll("[0-9]", "");
    }

    public SeatType getSeatType() {
        return seatType;
    }

    public void setSeatType(SeatType seatType) {
        this.seatType = seatType;
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

    public Double getCost() {
        return cost;
    }

    public void setCost(Double cost) {
        this.cost = cost;
    }

    public Double getMiles() {
        return miles;
    }

    public void setMiles(Double miles) {
        this.miles = miles;
    }

    public Airline getAirline() {
        return airline;
    }

    public void setAirline(Airline airline) {
        this.airline = airline;
    }

    public enum SeatType {
        A("A"),
        B("B"),
        C("C"),
        D("D"),
        E("E"),
        F("F"),
        G("G"),
        H("H"),
        I("I"),
        J("J"),
        K("K"),
        L("L"),
        M("M"),
        N("N"),
        O("O"),
        P("P"),
        Q("Q"),
        R("R"),
        S("S"),
        T("T"),
        U("U"),
        V("V"),
        W("W"),
        X("X"),
        Y("Y"),
        Z("Z");

        private String text;

        SeatType(String text) {
            this.text = text;
        }

        public String getText() {
            return this.text;
        }

        public static TakenFlight.SeatType fromString(String text) {
            for (TakenFlight.SeatType b : TakenFlight.SeatType.values()) {
                if (b.text.equalsIgnoreCase(text)) {
                    return b;
                }
            }
            return null;
        }
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