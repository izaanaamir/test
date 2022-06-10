import org.json.simple.JSONArray;
import org.json.simple.parser.JSONParser;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Base64;

public class AutomatedMetric {
    public static void main(String[] args) {

        int boardNumber = 47;
        int sprintNumber = 130;
        String baseURL = "https://spiky-dev.atlassian.net/rest";
        String loginUserName = "izaan@spiky.ai";
        String loginAPIToken = "NOAQjY3FkZHovRZHDI6w64A2";
        //String sprintData = getSprintData(baseURL, loginUserName, loginAPIToken, boardNumber);
        String sessionID = getIssuesData(baseURL, loginUserName, loginAPIToken, boardNumber, sprintNumber);
        //String JSON_Data = getJsonData();
        String CSV_Data = CSVFormat();
        Boolean operationSucess = writeToFile();
    }


    public static String getSprintData(String baseURL, String loginUserName, String loginAPIToken, int boardNumber ){
        String sprintDataJSON = "";
        URL url = null;
        HttpURLConnection conn = null;
        String input = null;
        OutputStream outputStream = null;
        BufferedReader bufferedReader = null;
        String output = null;
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
