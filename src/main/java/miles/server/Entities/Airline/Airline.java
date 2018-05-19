package miles.server.Entities.Airline;

public class Airline implements Comparable {

    protected Long id;
    protected String airlineName;
    private static Long idCounter = 0L;

     protected Airline(String airlineName) {
        this.id = getIncrementId();
        this.airlineName = airlineName;
    }

    private Long getIncrementId() {
        idCounter++;
        return idCounter;
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

    public int compareTo(Object o) {
        return this.airlineName.compareTo(((Airline)o).airlineName);
    }

}
