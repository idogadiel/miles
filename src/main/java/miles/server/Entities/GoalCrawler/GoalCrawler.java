package miles.server.Entities.GoalCrawler;

import miles.server.Entities.Airline.Airline;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class GoalCrawler {

    Crawl crawl;

    public GoalCrawler(Crawl crawl){
        this.crawl = crawl;
    }

    // return airline that do the desired flight
    public List<Airline> doCrawl(String from, String to){
        JSONObject json = this.crawl.getData(from, to);

        // create concrete goals
        List<Airline> goals = new ArrayList<>();
        return goals;

    }
}
