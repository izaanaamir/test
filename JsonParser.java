import org.json.*;

public class JsonParser {
    
    public void jiraJsonParser(String responseBody) throws JSONException{
        JSONObject issue = new JSONObject(responseBody);
        JSONArray issues = issue.getJSONArray("issues");
        JSONArray issuesInfo = issues.getJSONObject(0).getJSONObject("fields").getJSONArray("customfield_10020");
        System.out.println(issuesInfo.length());
        
    }
}
