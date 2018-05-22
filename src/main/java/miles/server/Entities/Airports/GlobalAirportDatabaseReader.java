package miles.server.Entities.Airports;

import org.json.JSONObject;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class GlobalAirportDatabaseReader {

    public static JSONObject getMatrixFromCSV() {

        Resource resource = new ClassPathResource("GlobalAirportDatabase.txt");
        BufferedReader br = null;
        String line = "";

        String stringJson = "";
        try {
            br = new BufferedReader(new FileReader(resource.getFile()));
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