package miles.server.Utills;

import com.google.common.collect.RowSortedTable;
import com.google.common.collect.TreeBasedTable;
import miles.server.Entities.AirlineMatrix.AirlinesRelation;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

public class CSVReader {

    public static RowSortedTable<String, String, AirlinesRelation> getMatrixFromCSV() {

        Resource resource = new ClassPathResource("matrixCSV.csv");
        BufferedReader br = null;
        String line = "";
        String cvsSplitBy = ",";
        int linesCounter = 0;

        List<String> airlines = null;

        RowSortedTable<String, String, AirlinesRelation> matrix = TreeBasedTable.create();

        try {
            br = new BufferedReader(new FileReader(resource.getFile()));
            while ((line = br.readLine()) != null) {

                // use comma as separator
                if (linesCounter == 0) {
                    airlines = Arrays.asList(line.split(cvsSplitBy));

                } else {
                    List<String> data = Arrays.asList(line.split(cvsSplitBy));
                    for (int i = 1; i < data.size(); i++) {
                        matrix.put(data.get(0), airlines.get(i), new AirlinesRelation(data.get(i)));

                    }
                }

                linesCounter++;
            }
            return matrix;
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