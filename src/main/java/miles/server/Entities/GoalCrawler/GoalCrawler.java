package miles.server.Entities.GoalCrawler;

import miles.server.Entities.Airline.Airline;

import java.util.List;

public class GoalCrawler {

    Crawl crawl;

    public GoalCrawler(Crawl crawl) {
        this.crawl = crawl;
    }

    // return airline that do the desired flight
    public List<Airline> doCrawl(String from, String to) {

        // create concrete goals
        List<Airline> goals = this.crawl.getData(from, to);
        return goals;

    }


}
