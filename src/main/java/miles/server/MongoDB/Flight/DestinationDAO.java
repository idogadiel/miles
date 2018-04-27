package miles.server.MongoDB.Flight;

import miles.server.MongoDB.SpringMongoConfig;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

import java.util.List;

/**
 * Created by gadiel on 12/10/2016.
 */
public class DestinationDAO {

    public static List<VisitedDestination> getAllVisitedDestinations(String userId) {
        ApplicationContext ctx = new AnnotationConfigApplicationContext(SpringMongoConfig.class);
        MongoOperations mongoOperation = (MongoOperations) ctx.getBean("mongoTemplate");
        Query searchDestinationQuery = new Query(Criteria.where("userId").is(userId));
        List<VisitedDestination> visitedDestinations = mongoOperation.find(searchDestinationQuery, VisitedDestination.class);
        return visitedDestinations;
    }

    public static List<DesiredDestination> getAllDesiredDestinations(String userId) {
        ApplicationContext ctx = new AnnotationConfigApplicationContext(SpringMongoConfig.class);
        MongoOperations mongoOperation = (MongoOperations) ctx.getBean("mongoTemplate");
        Query searchDestinationQuery = new Query(Criteria.where("userId").is(userId));
        List<DesiredDestination> desiredDestinations = mongoOperation.find(searchDestinationQuery, DesiredDestination.class);
        return desiredDestinations;
    }

    public static void addDesiredDestination(DesiredDestination desiredDestination){
        ApplicationContext ctx = new AnnotationConfigApplicationContext(SpringMongoConfig.class);
        MongoOperations mongoOperation = (MongoOperations) ctx.getBean("mongoTemplate");
        mongoOperation.save(desiredDestination);
    }

    public static void addVisitedDestination(VisitedDestination visitedDestination){
        ApplicationContext ctx = new AnnotationConfigApplicationContext(SpringMongoConfig.class);
        MongoOperations mongoOperation = (MongoOperations) ctx.getBean("mongoTemplate");
        mongoOperation.save(visitedDestination);
    }

}
