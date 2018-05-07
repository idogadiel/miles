package miles.server.Entities.MileageMembership;

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
public class MileageMembershipDAO {



    public static List<MileageMembership> getMileageMemberships(String userId) {
        ApplicationContext ctx = new AnnotationConfigApplicationContext(SpringMongoConfig.class);
        MongoOperations mongoOperation = (MongoOperations) ctx.getBean("mongoTemplate");
        Query searchDestinationQuery = new Query(Criteria.where("userId").is(userId));
        List< MileageMembership>  mileageMemberships = mongoOperation.find(searchDestinationQuery,  MileageMembership.class);
        return mileageMemberships;
    }

    public static void addMileageMembership(MileageMembership mileageMembership){
        ApplicationContext ctx = new AnnotationConfigApplicationContext(SpringMongoConfig.class);
        MongoOperations mongoOperation = (MongoOperations) ctx.getBean("mongoTemplate");
        mongoOperation.save(mileageMembership);
    }


}
