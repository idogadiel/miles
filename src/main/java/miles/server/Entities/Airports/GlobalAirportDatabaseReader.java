package miles.server.Entities.Airports;

import org.json.JSONObject;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

import java.io.*;

public class GlobalAirportDatabaseReader {

    public static JSONObject getDataFromTXT() {

        BufferedReader br = null;
        String line = "";
        InputStream in = GlobalAirportDatabaseReader.class.getResourceAsStream("GlobalAirportDatabase.txt");

        String stringJson = "";
        try {
            br = new BufferedReader(new InputStreamReader(in));
            while ((line = br.readLine()) != null) {
                stringJson = stringJson.concat(line);
            }
            return new JSONObject(stringJson);
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