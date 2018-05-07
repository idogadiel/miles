package miles.server.Entities.MileageMembership;

import miles.server.Entities.Goal.Goal;
import miles.server.Entities.Goal.GoalDAO;
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
@RequestMapping("/mileagemembership")
public class MileageMembershipRest {


    @RequestMapping(value = "/addMileageMembership", method = RequestMethod.POST)
    public String addMileageMembership(@RequestBody String mileageMembershipString) {
        MileageMembership mileageMembership = new MileageMembership(mileageMembershipString);
        NuvolaUserDetails user = (NuvolaUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String userId = user.getUser().getId();
        mileageMembership.setUserId(userId);
        MileageMembershipDAO.addMileageMembership(mileageMembership);
        return JSONUtills.getSuccessJSON();
    }

    @RequestMapping(value = "/getAllMileageMemberships", method = RequestMethod.GET)
    public String getAllMileageMemberships() {
        NuvolaUserDetails user = (NuvolaUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String userId = user.getUser().getId();
        List<MileageMembership> allMileageMemberships = MileageMembershipDAO.getMileageMemberships(userId);
        return allMileageMemberships.toString();
    }





}
