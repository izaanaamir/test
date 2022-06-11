import java.io.FileNotFoundException;

import org.json.JSONException;

public class DataRetrieval {
    
    public static void main(String[] args) throws FileNotFoundException, JSONException {
        new JiraData(47, 
                    130, 
                    "https://spiky-dev.atlassian.net/rest", 
                    "izaan@spiky.ai", 
                    "y9YODHxEIuW38jf7dQUv04FE");
    }
}
