//import org.springframework.core.io.ClassPathResource;
//import org.springframework.core.io.Resource;
//
//import java.io.*;
//
//public class Sandbox {
//    static public void main(String[] arg) {
//        System.out.println("start sandbox");
//
////        Goal goal = new Goal("JFK", "TLV", 1);
////        Recommender recommender = new Recommender(goal);
////
////        TakenFlight t1 = new TakenFlight("tlv", "lax", "A", "EZY643", 234234L, 1000D);
////        TakenFlight t2 = new TakenFlight("tlv", "jfk", "B", "EZY353", 234234L, 500D);
////        recommender.addTakenFlight(t1).addTakenFlight(t2);
////
////        String recommend = recommender.recommend();
////        System.out.println(recommend);
//        InputStream in = Sandbox.class.getResourceAsStream("GlobalAirportDatabase.txt");
//
//        BufferedReader br = null;
//        String line = "";
//    try {
//        String stringJson = "";
//        br = new BufferedReader(new InputStreamReader(in));
//
//        while ((line = br.readLine()) != null) {
//            stringJson = stringJson.concat(line);
//        }
//    } catch (Exception e){
//        System.out.println(e);
//    }
//
//        System.out.println("end of sandbox");
//    }
//}
