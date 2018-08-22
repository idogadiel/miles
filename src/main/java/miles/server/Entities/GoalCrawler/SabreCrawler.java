package miles.server.Entities.GoalCrawler;

import miles.server.Entities.Airline.Airline;
import miles.server.Entities.Airline.AirlineFactory;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

public class SabreCrawler implements Crawl {

    private final String USER_AGENT = "Mozilla/5.0";
    private final String authorization = "Bearer T1RLAQLsPqEEm+2jNMDvlZAYYdL/O0u0FxBKfS19ptaJ8TP75eO897xgAADAurjApDzl/LE1vXxDC9CC4xxJCbMMx/pXyZQPd16x0Sd8IU0qfWntezVei38PQYK4hXNcQTnXx24KMX7iUmZH55n4HPTyOU/t27mlCsevQ0qNsJ0kAljNR0es2lXPDue9qOALVXgS33zHBztryid5FImguEko8DDfD63/r7Rcx0Mjh8gDZsQ4nMM6ugqc+R0XwQq5TYC9XrmQco4IyPUpU2bgv0Epxd/MscenYTrWCm2kzPL78eBYXGuFvJdsizwJ";

    public List<Airline> getData(String from, String to) {
        try {
            String url = "https://api-crt.cert.havail.sabre.com/v2/shop/flights/fares?origin=" + from + "&destination=" + to + "&lengthofstay=5&pointofsalecountry=US";

            URL obj = new URL(url);
            HttpURLConnection con = (HttpURLConnection) obj.openConnection();

            // optional default is GET
            con.setRequestMethod("GET");

            //add request header
            con.setRequestProperty("User-Agent", USER_AGENT);
            con.setRequestProperty("Authorization", authorization);

            int responseCode = con.getResponseCode();

            BufferedReader in = new BufferedReader(
                    new InputStreamReader(con.getInputStream()));
            String inputLine;
            StringBuffer response = new StringBuffer();

            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
            in.close();

            JSONObject json = new JSONObject(response.toString());
            Set<Airline> result = new HashSet<>();
            JSONArray fareInfo = json.getJSONArray("FareInfo");
            int index = 0;

            while (result.size() <= 10) {
                try {
                    JSONObject currentFare = (JSONObject) fareInfo.get(index);
                    JSONObject lowestRare = (JSONObject) currentFare.get("LowestFare");
                    JSONArray arr = (JSONArray) lowestRare.get("AirlineCodes");
                    String airlineAcro = (String) arr.get(0);
                    Airline currentAirline = AirlineFactory.getInstance().getAirline(airlineAcro);
                    result.add(currentAirline);
                    index++;
                } catch (Exception e) {
                    return new LinkedList<>(result);
                }
            }

            return new LinkedList<>(result);

        } catch (Exception e) {
            System.out.println(e); // some logger
        }
        return new LinkedList<>();
    }


}




