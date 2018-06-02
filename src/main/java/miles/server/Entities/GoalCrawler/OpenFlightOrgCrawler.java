package miles.server.Entities.GoalCrawler;

import miles.server.Entities.Airline.Airline;
import miles.server.Entities.Airline.AirlineFactory;

import java.io.*;
import java.util.LinkedList;
import java.util.List;

public class OpenFlightOrgCrawler implements Crawl {

    public List<Airline> getData(String from, String to) {
        InputStream in = OpenFlightOrgCrawler.class.getClassLoader().getResourceAsStream("routes.txt");

        BufferedReader br = null;
        String line = "";

        String stringJson = "";
        try {
            br = new BufferedReader(new InputStreamReader(in));
            List<Airline> airlines = new LinkedList<>();
            while ((line = br.readLine()) != null) {
                String[] split = line.split(",");
                String toRaw = split[2];
                String fromRaw = split[4];
                if (toRaw.equalsIgnoreCase(to) && fromRaw.equalsIgnoreCase(from)) {
                    Airline airline = AirlineFactory.getInstance().getAirline(split[0]);
                    airlines.add(airline);
                }
            }
            return airlines;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (br != null) {
                try {
                    br.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return null;
    }


}




