package miles.server.Entities.AirlineMatrix;


import miles.server.Entities.Airline.Airline;

import java.util.List;
// singleton

public class AirlineMatrix {

    private static int DIMENSION = 10;
    private static Long[][] matrix = new Long[DIMENSION][DIMENSION];
    public static AirlineMatrix airlineMatrix;

    private AirlineMatrix() {
        // schedule new thread => call updateMatrix() => sleep for 12 hours
    }

    public AirlineMatrix getInstance() {
        if (airlineMatrix == null) {
            airlineMatrix = new AirlineMatrix();
        }
        return airlineMatrix;
    }

    private void updateMatrix(){
        // go and do the actual update for the matrix
    }

    private List<Airline> getMembers(Long companyId){
        return null;
    }


}
