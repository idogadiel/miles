package miles.server.Entities.TakenFlight;

import miles.server.DB.SpringMongoConfig;
import miles.server.Entities.Goal.Goal;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

import java.util.List;

/**
 * Created by gadiel on 12/10/2016.
 */
public class TakenFlightDAO {

    public static List<TakenFlight> getAllVisitedDestinations(String userId) {
        ApplicationContext ctx = new AnnotationConfigApplicationContext(SpringMongoConfig.class);
        MongoOperations mongoOperation = (MongoOperations) ctx.getBean("mongoTemplate");
        Query searchDestinationQuery = new Query(Criteria.where("userId").is(userId));
        List<TakenFlight> takenFlights = mongoOperation.find(searchDestinationQuery, TakenFlight.class);
        return takenFlights;
    }

    public static List<Goal> getAllDesiredDestinations(String userId) {
        ApplicationContext ctx = new AnnotationConfigApplicationContext(SpringMongoConfig.class);
        MongoOperations mongoOperation = (MongoOperations) ctx.getBean("mongoTemplate");
        Query searchDestinationQuery = new Query(Criteria.where("userId").is(userId));
        List<Goal> desiredDestinations = mongoOperation.find(searchDestinationQuery, Goal.class);
        return desiredDestinations;
    }

    public static void addDesiredDestination(Goal desiredDestination){
        ApplicationContext ctx = new AnnotationConfigApplicationContext(SpringMongoConfig.class);
        MongoOperations mongoOperation = (MongoOperations) ctx.getBean("mongoTemplate");
        mongoOperation.save(desiredDestination);
    }

    public static void addVisitedDestination(TakenFlight takenFlight){
        ApplicationContext ctx = new AnnotationConfigApplicationContext(SpringMongoConfig.class);
        MongoOperations mongoOperation = (MongoOperations) ctx.getBean("mongoTemplate");
        mongoOperation.save(takenFlight);
    }

}
