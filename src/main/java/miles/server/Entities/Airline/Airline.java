package miles.server.Entities.Airline;

public class Airline implements Comparable {

    protected Long id;
    protected String airlineName;
    protected String websiteAddress;

    private static Long idCounter = 0L;

    public Airline(String airlineName) {
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

    public String getWebsiteAddress() {
        return websiteAddress;
    }

    public void setWebsiteAddress(String websiteAddress) {
        this.websiteAddress = websiteAddress;
    }

    public int compareTo(Object o) {
        return 1;
    }

    @Override
    public boolean equals(Object other) {
        return this.airlineName.equals(((Airline) other).airlineName);
    }

}
