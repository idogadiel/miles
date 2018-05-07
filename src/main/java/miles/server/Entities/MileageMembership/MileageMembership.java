package miles.server.Entities.MileageMembership;

import org.json.JSONObject;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "MileageMemberships")
public class MileageMembership {

    @Id
    private String id;  // entity id -> auto generated
    private String userId; // userId -> relation to the user who own the account
    private String companyId;
    private String username; // part of the user credential
    private String password; // un-encrypted & un-salted -> need to figure out how to deal with it

    public MileageMembership() {
    }

    public MileageMembership(String jsonUser) {
        JSONObject jsonObject = new JSONObject(jsonUser);
        this.username = (String) jsonObject.get("username");
        this.password = (String) jsonObject.get("password");
        this.companyId = (String) jsonObject.get("companyId");
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getCompanyId() {
        return companyId;
    }

    public void setCompanyId(String companyId) {
        this.companyId = companyId;
    }

}
