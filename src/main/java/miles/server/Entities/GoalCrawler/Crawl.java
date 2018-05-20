package miles.server.Entities.GoalCrawler;

import miles.server.Entities.Airline.Airline;

import java.util.List;

public interface Crawl {

    List<Airline> getData(String from, String to);
}
