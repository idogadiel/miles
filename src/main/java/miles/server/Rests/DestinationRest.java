package miles.server.Rests;

import miles.server.MongoDB.Flight.DesiredDestination;
import miles.server.MongoDB.Flight.DestinationDAO;
import miles.server.MongoDB.Flight.VisitedDestination;
import miles.server.Security.Others.NuvolaUserDetails;
import miles.server.Utills.JSONUtills;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by gadiel on 11/10/2016.
 */
@RestController
@RequestMapping("/destination")
public class DestinationRest {


    // desired
    @RequestMapping(value = "/addDesiredDestination", method = RequestMethod.POST)
    public String addDesiredDestination(@RequestBody String destinationString) {
        DesiredDestination desiredDestination = new DesiredDestination(destinationString);
        NuvolaUserDetails user = (NuvolaUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String userId = user.getUser().getId();
        desiredDestination.setUserId(userId);
        DestinationDAO.addDesiredDestination(desiredDestination);
        return JSONUtills.getSuccessJSON();
    }

    @RequestMapping(value = "/getAllDesiredDestinations", method = RequestMethod.GET)
    public String getAllDesiredDestinations() {
        NuvolaUserDetails user = (NuvolaUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String userId = user.getUser().getId();
        List<DesiredDestination> allDesiredDestinations = DestinationDAO.getAllDesiredDestinations(userId);
        return allDesiredDestinations.toString();
    }


    // visited
    @RequestMapping(value = "/addVisitedDestination", method = RequestMethod.POST)
    public String addVisitedDestination(@RequestBody String destinationString) {
        VisitedDestination visitedDestination = new VisitedDestination(destinationString);
        NuvolaUserDetails user = (NuvolaUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String userId = user.getUser().getId();
        visitedDestination.setUserId(userId);
        DestinationDAO.addVisitedDestination(visitedDestination);
        return JSONUtills.getSuccessJSON();
    }

    @RequestMapping(value = "/getAllVisitedDestinations", method = RequestMethod.GET)
    public String getAllVisitedDestinations() {
        NuvolaUserDetails user = (NuvolaUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String userId = user.getUser().getId();
        List<VisitedDestination> allVisitedDestinations = DestinationDAO.getAllVisitedDestinations(userId);
        return allVisitedDestinations.toString();
    }



}
