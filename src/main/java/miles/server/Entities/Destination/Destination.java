package miles.server.Entities.Destination;

import miles.server.Entities.Airline.Airline;

public class Destination implements Comparable {

    protected Long id;
    protected String destinationName;

    private static Long idCounter = 0L;

    protected Destination(String destinationName) {
        this.id = getIncrementId();
        this.destinationName = destinationName;
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

    public String getDestinationName() {
        return destinationName;
    }

    public void setDestinationName(String destinationName) {
        this.destinationName = destinationName;
    }

    public int compareTo(Object o) {
        return this.destinationName.compareTo(((Destination) o).destinationName);
    }

}
