package miles.server.Entities.GoalCrawler;


import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class SabreCrawler implements Crawl {

    private final String USER_AGENT = "Mozilla/5.0";
    private final String authorization = "Bearer T1RLAQKBnivT7ZljR7VUOF2qifcZrfM2MBCzRPZxwIEvJlfWMqJKBklEAADAzrx3c31Ocnp7R0opUzYIjjLx+7yOIuszbqONC5xyCIzi4WaI/81AP8srsvUBmu4HSH0cuLDJZ68+ZRql6l5u7+GP2pkc8Jpd5ZUsJWJIqwAIi/7HJZ2i4brk5shZCx+lPdx5FIUCAC9opMEr4Gg1UQp3lzEwji7RBfl1Vw+Q8KaaCL0J7VnkxdfexelJPsvHQRAEtKbs4D6i/uyM+3QKVPHCTPpkU8pp2v836MoZwBmz2RgdmTpfdMTySZVpk5lC";



    public JSONObject getData(String from, String to) {
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
            return json;

        } catch (Exception e) {
            return null;
        }
    }


}




