package miles.server.Entities.User;

import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.security.authentication.encoding.ShaPasswordEncoder;
import miles.server.DB.SpringMongoConfig;
import miles.server.Security.Utills.Utills;

/**
 * Created by gadiel on 12/10/2016.
 */
public class UserDAO {

    public static User getUserByEmail(String email) {
        ApplicationContext ctx = new AnnotationConfigApplicationContext(SpringMongoConfig.class);
        MongoOperations mongoOperation = (MongoOperations) ctx.getBean("mongoTemplate");
        Query searchUserQuery = new Query(Criteria.where("email").is(email));
        User savedUser = mongoOperation.findOne(searchUserQuery, User.class);
        if (savedUser != null) {
            return savedUser;
        }
        return null;
    }

    public static boolean isEmailExist(String email) {
        ApplicationContext ctx = new AnnotationConfigApplicationContext(SpringMongoConfig.class);
        MongoOperations mongoOperation = (MongoOperations) ctx.getBean("mongoTemplate");
        Query searchUserQuery = new Query(Criteria.where("email").is(email));
        User savedUser = mongoOperation.findOne(searchUserQuery, User.class);
        if (savedUser != null) {
            return true;
        }
        return false;
    }

    public static void insertUserByEntity(User user) {
        ApplicationContext ctx = new AnnotationConfigApplicationContext(SpringMongoConfig.class);
        MongoOperations mongoOperation = (MongoOperations) ctx.getBean("mongoTemplate");
        mongoOperation.save(user);
    }

    public static boolean activateUser(String code) {
        ApplicationContext ctx = new AnnotationConfigApplicationContext(SpringMongoConfig.class);
        MongoOperations mongoOperation = (MongoOperations) ctx.getBean("mongoTemplate");
        Query searchUserQuery = new Query(Criteria.where("activationKey").is(code));
        User savedUser = mongoOperation.findOne(searchUserQuery, User.class);
        if (savedUser != null && !savedUser.activated) {
            savedUser.activated = true;
            savedUser.setActivationKey(Utills.createActivationKey());
            mongoOperation.save(savedUser);
            return true;
        }
        return false;
    }

    public static User changeActivationKey(String email) {
        ApplicationContext ctx = new AnnotationConfigApplicationContext(SpringMongoConfig.class);
        MongoOperations mongoOperation = (MongoOperations) ctx.getBean("mongoTemplate");
        Query searchUserQuery = new Query(Criteria.where("email").is(email));
        User savedUser = mongoOperation.findOne(searchUserQuery, User.class);
        if (savedUser != null) {
            savedUser.setActivationKey(Utills.createActivationKey());
            mongoOperation.save(savedUser);
            return savedUser;
        }
        return null;
    }

    public static boolean changeUserPassword(String body) {
        String code = "";
        String password = "";
        try {
            JSONObject jsonObject = new JSONObject(body);
            code = (String) jsonObject.get("code");
            password = (String) jsonObject.get("password");
        } catch (JSONException e) {
            return false;
        }
        ApplicationContext ctx = new AnnotationConfigApplicationContext(SpringMongoConfig.class);
        MongoOperations mongoOperation = (MongoOperations) ctx.getBean("mongoTemplate");
        Query searchUserQuery = new Query(Criteria.where("activationKey").is(code));
        User savedUser = mongoOperation.findOne(searchUserQuery, User.class);
        if (savedUser != null) {
            savedUser.setActivationKey(Utills.createActivationKey());
            savedUser.setPassword(new ShaPasswordEncoder().encodePassword(password, null));
            mongoOperation.save(savedUser);
            return true;
        }
        return false;
    }
}
