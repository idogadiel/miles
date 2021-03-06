package miles.server.Utills;


import org.json.JSONObject;

/**
 * Created by gadiel on 14/10/2016.
 */
public class JSONUtills {

    static String USER_NOT_ACTIVATED = "user not activated";

    public static String getSuccessJSON() {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("result", true);
        return jsonObject.toString();
    }

    public static String getFailedJSON() {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("result", false);
        return jsonObject.toString();
    }

    public static String getUserNotActivatedJSON() {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("result", false);
        jsonObject.put("reason", USER_NOT_ACTIVATED);
        return jsonObject.toString();
    }

    public static String getBadCredentialsJSON() {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("result", false);
        jsonObject.put("reason", "Wrong username and/or password");
        return jsonObject.toString();
    }

    public static String getCustomizedReasonJSON(boolean result, String reason) {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("result", result);
        jsonObject.put("reason", reason);
        return jsonObject.toString();
    }

    public static String getExceptionJson(Exception e) {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("result", false);
        jsonObject.put("exception", e.toString());
        return jsonObject.toString();
    }
}
