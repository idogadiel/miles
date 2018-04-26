package miles.server;


import miles.server.MongoDB.SpringMongoConfig;
import miles.server.MongoDB.User.AdminUser;
import miles.server.MongoDB.User.SimpleUser;
import miles.server.MongoDB.User.User;
import miles.server.MongoDB.User.UserUtills;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.data.mongodb.core.MongoOperations;

import java.util.Date;

/**
 * Created by gadiel on 15/10/2016.
 */
public class IdosTests {

    public static void main(String[] args) {

        Date date = new Date();
        User adminUser = new AdminUser("123","admin-user3",date);
        User simpleUser = new SimpleUser("123","simple-user3",date);
        ApplicationContext ctx = new AnnotationConfigApplicationContext(SpringMongoConfig.class);
        MongoOperations mongoOperation = (MongoOperations) ctx.getBean("mongoTemplate");
        mongoOperation.save(adminUser);
        mongoOperation.save(simpleUser);
        User u1 = UserUtills.getUserByEmail("admin-user3");
        System.out.println(u1.getEmail()+":"+u1.getRole());
        User u2 = UserUtills.getUserByEmail("simple-user3");
        System.out.println(u2.getEmail()+":"+u2.getRole());
    }
}
