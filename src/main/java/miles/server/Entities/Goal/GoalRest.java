package miles.server.Entities.Goal;

import miles.server.Security.Others.NuvolaUserDetails;
import miles.server.Utills.JSONUtills;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by gadiel on 11/10/2016.
 */
@RestController
@RequestMapping("/goal")
public class GoalRest {


    // desired add
    @RequestMapping(value = "/addGoal", method = RequestMethod.POST)
    public String addGoal(@RequestBody String goalString) {
        Goal goal = new Goal(goalString);
        NuvolaUserDetails user = (NuvolaUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String userId = user.getUser().getId();
        goal.setUserId(userId);
        GoalDAO.addGoal(goal);
        return JSONUtills.getSuccessJSON();
    }

    // desired get
    @RequestMapping(value = "/getAllGoals", method = RequestMethod.GET)
    public String getAllGoals() {
        NuvolaUserDetails user = (NuvolaUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String userId = user.getUser().getId();
        List<Goal> allGoals = GoalDAO.getAllGoals(userId);
        return allGoals.toString();
    }





}
