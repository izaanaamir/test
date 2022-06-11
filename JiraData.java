import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Base64;

import org.json.simple.JSONArray;
import org.json.simple.parser.JSONParser;

/**
 * Uses Rest APIs and retrieves data from Jira Servers and parses the data
 */
public class JiraData {

    int boardNumber;
    int sprintNumber;
    String baseURL;
    String loginUserName;
    String loginAPIToken;

    public JiraData(int boardNumber, int sprintNumber, String baseURL, String loginUserName, String loginAPIToken) {
        this.boardNumber = boardNumber;
        this.sprintNumber = sprintNumber;
        this.baseURL = baseURL;
        this.loginUserName = loginUserName;
        this.loginAPIToken = loginAPIToken;
        getSprintData(baseURL, loginUserName, loginAPIToken, boardNumber);
        getIssuesData(baseURL, loginUserName, loginAPIToken, boardNumber, sprintNumber);
    }



    public static String getSprintData(String baseURL, String loginUserName, String loginAPIToken, int boardNumber ){
        String sprintDataJSON = "";
        URL url = null;
        HttpURLConnection conn = null;
        String extendedURL = "/agile/1.0/board/" + boardNumber +"/sprint";

        try {

            url = new URL (baseURL + extendedURL);
            String userPass = loginUserName + ":" + loginAPIToken;
            String encoding = Base64.getEncoder().encodeToString((userPass).getBytes("UTF-8"));

            conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.setDoOutput(true);
            conn.setRequestProperty  ("Authorization", "Basic " + encoding);
            InputStream content = conn.getInputStream();

            if(conn.getResponseCode() == 200) {
                BufferedReader in =
                        new BufferedReader(new InputStreamReader(content));
                String line;
                while ((line = in.readLine()) != null) {
                    sprintDataJSON += line;
                }
                conn.disconnect();
            }

            JSONtoCSVParser(sprintDataJSON);

        }
        catch (Exception ex){
            System.out.println(ex.getMessage());
        }
        System.out.println(sprintDataJSON);
        return sprintDataJSON;
    }

    public static String JSONtoCSVParser(String loginResponse){
        JSONParser parser = new JSONParser();
        JSONArray array = null;
        String dataRows = "";
        String headerRows = "";

        return "";
    }
    public static String getIssuesData(String baseURL, String loginUserName, String loginAPIToken, int boardNumber,
    int sprintNumber) {


        String extendedURL = "/agile/1.0/board/" + boardNumber + "/sprint/" + sprintNumber + "/issue";
        String IssuesDataJSON = "";
        URL url = null;
        HttpURLConnection conn = null;
        String input = null;
        OutputStream outputStream = null;
        BufferedReader bufferedReader = null;
        String output = null;

        try {

            url = new URL(baseURL + extendedURL);
            String userPass = loginUserName + ":" + loginAPIToken;
            String encoding = Base64.getEncoder().encodeToString((userPass).getBytes("UTF-8"));

            conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.setDoOutput(true);
            conn.setRequestProperty("Authorization", "Basic " + encoding);
            InputStream content = conn.getInputStream();

            if (conn.getResponseCode() == 200) {
                BufferedReader in =
                        new BufferedReader(new InputStreamReader(content));
                String line;
                while ((line = in.readLine()) != null) {
                    IssuesDataJSON += line;
                }
                conn.disconnect();
            }

            JSONtoCSVParser(IssuesDataJSON);

        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
        System.out.println(IssuesDataJSON);
        return IssuesDataJSON;

    }

    public static String getBoardReports(String baseURL, String loginUserName, String loginAPIToken, String boardNumber, String sprintNumber){
        String extendedURL = "/agile/1.0/board/" + boardNumber + "/sprint/" + sprintNumber + "/issue";
        String IssuesDataJSON = "";
        URL url = null;
        HttpURLConnection conn = null;
        String input = null;
        OutputStream outputStream = null;
        BufferedReader bufferedReader = null;
        String output = null;

        try {

            url = new URL(baseURL + extendedURL);
            String userPass = loginUserName + ":" + loginAPIToken;
            String encoding = Base64.getEncoder().encodeToString((userPass).getBytes("UTF-8"));

            conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.setDoOutput(true);
            conn.setRequestProperty("Authorization", "Basic " + encoding);
            InputStream content = conn.getInputStream();

            if (conn.getResponseCode() == 200) {
                BufferedReader in =
                        new BufferedReader(new InputStreamReader(content));
                String line;
                while ((line = in.readLine()) != null) {
                    IssuesDataJSON += line;
                }
                conn.disconnect();
            }

            JSONtoCSVParser(IssuesDataJSON);

        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
        System.out.println(IssuesDataJSON);
        return IssuesDataJSON;
    }
    public static String CSVFormat(){return "";}
    public static boolean writeToFile(){return true;}

}