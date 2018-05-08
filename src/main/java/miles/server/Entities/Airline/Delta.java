package miles.server.Entities.Airline;

import miles.server.Entities.TakenFlight.TakenFlight;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;


@Component
@Scope(value = ConfigurableBeanFactory.SCOPE_SINGLETON)
public class Delta extends Airline  {

    public Delta(){
        super(1L,"Delta" , "http://www.delta.com/redeem_api");
    }



    // kastach
    public int compareTo(Object  o){
    return 1;
    }
}
