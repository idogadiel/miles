package miles.server.Entities.Goal;

import miles.server.DB.SpringMongoConfig;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

import java.util.List;

/**
 * Created by gadiel on 12/10/2016.
 */
public class GoalDAO {



    public static List<Goal> getAllGoals(String userId) {
        ApplicationContext ctx = new AnnotationConfigApplicationContext(SpringMongoConfig.class);
        MongoOperations mongoOperation = (MongoOperations) ctx.getBean("mongoTemplate");
        Query searchDestinationQuery = new Query(Criteria.where("userId").is(userId));
        List<Goal> goals = mongoOperation.find(searchDestinationQuery, Goal.class);
        return goals;
    }

    public static void addGoal(Goal goal){
        ApplicationContext ctx = new AnnotationConfigApplicationContext(SpringMongoConfig.class);
        MongoOperations mongoOperation = (MongoOperations) ctx.getBean("mongoTemplate");
        mongoOperation.save(goal);
    }


}
