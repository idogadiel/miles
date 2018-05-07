package miles.server.Entities.TakenFlight;

import miles.server.Entities.Goal.Goal;
import miles.server.Security.Others.NuvolaUserDetails;
import miles.server.Utills.JSONUtills;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by gadiel on 11/10/2016.
 */
@RestController
@RequestMapping("/takenflight")
public class TakenFlightRest {



    // visited add
    @RequestMapping(value = "/addTakenFlight", method = RequestMethod.POST)
    public String addTakenFlight(@RequestBody String destinationString) {
        TakenFlight takenFlight = new TakenFlight(destinationString);
        NuvolaUserDetails user = (NuvolaUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String userId = user.getUser().getId();
        takenFlight.setUserId(userId);
        TakenFlightDAO.addVisitedDestination(takenFlight);
        return JSONUtills.getSuccessJSON();
    }

    // visited read
    @RequestMapping(value = "/getAllTakenFlights", method = RequestMethod.GET)
    public String getAllTakenFlights() {
        NuvolaUserDetails user = (NuvolaUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String userId = user.getUser().getId();
        List<TakenFlight> allTakenFlights = TakenFlightDAO.getAllVisitedDestinations(userId);
        return allTakenFlights.toString();
    }


}
