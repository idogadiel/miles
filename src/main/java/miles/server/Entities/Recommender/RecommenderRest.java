package miles.server.Entities.Recommender;

import miles.server.Entities.Goal.Goal;
import miles.server.Entities.Goal.GoalDAO;
import miles.server.Entities.TakenFlight.TakenFlight;
import miles.server.Entities.TakenFlight.TakenFlightDAO;
import miles.server.Security.Others.NuvolaUserDetails;
import miles.server.Utills.JSONUtills;
import org.json.JSONObject;
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
@RequestMapping("/recommender")
public class RecommenderRest {

    // recommend
    @RequestMapping(value = "/recommend", method = RequestMethod.GET)
    public String getRecommend() {

        NuvolaUserDetails user = (NuvolaUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String userId = user.getUser().getId();
        List<Goal> allGoals = GoalDAO.getAllGoals(userId);
        List<TakenFlight> allFlights = TakenFlightDAO.getAllVisitedDestinations(userId);

        Recommender recommender = new Recommender(allGoals.get(0));
        recommender.addTakenFlights(allFlights);

        String recommendedAirline = recommender.recommend();

        JSONObject result = new JSONObject();
        result.put("mostBeneficialAirline", recommendedAirline);

        return result.toString();
    }


}
