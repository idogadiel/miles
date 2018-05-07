package miles.server.Security.Others;

import miles.server.Entities.User.User;
import miles.server.Entities.User.UserDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    private final LoggedInChecker loggedInChecker;

    @Autowired
    UserServiceImpl(LoggedInChecker loggedInChecker) {
        this.loggedInChecker = loggedInChecker;
    }

    @Override
    public User getUserByUsername(String email) {
        User DBUser = UserDAO.getUserByEmail(email);
        if (DBUser != null) {
            return DBUser;
        }
            return null;
    }

    @Override
    public List<String> getPermissions(String username) {
        List<String> permissions = new ArrayList<>();
        permissions.add("ROLE_ADMIN");
        return permissions;
    }

    @Override
    public User getCurrentUser() {
        return loggedInChecker.getLoggedInUser();
    }

    @Override
    public Boolean isCurrentUserLoggedIn() {
        return loggedInChecker.getLoggedInUser() != null;
    }

}
