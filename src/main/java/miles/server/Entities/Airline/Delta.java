package miles.server.Entities.Airline;

import miles.server.Entities.TakenFlight.TakenFlight;


// example
public class Delta extends Airline  {

    public Delta(){
        super(1L,"Delta" , "http://www.delta.com/redeem_api");
    }

    @Override
    public String redeemSuggest(TakenFlight takenFlight) {
        return null;
    }

    @Override
    public void redeemDo(TakenFlight takenFlight) {

    }
}
