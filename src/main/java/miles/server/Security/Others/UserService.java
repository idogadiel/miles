package miles.server.Security.Others;

import miles.server.MongoDB.User.User;

import java.util.List;

public interface UserService {
    User getUserByUsername(String username);

    List<String> getPermissions(String username);

    User getCurrentUser();

    Boolean isCurrentUserLoggedIn();
}
