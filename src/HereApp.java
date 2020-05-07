import org.json.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;


public class HereApp {

    /** HERE API INFORMATION **/
    private static String apiKey = "MLr6vmcH7IgZsaAqaSebZ42kxfRuY1SJyGdJL2GVhVk";

    public static void main(String[] args) throws IOException, JSONException {

        URL url = new URL("https://geocode.search.hereapi.com/v1/geocode?q=Invalidenstr+117%2C+Berlin&apiKey="+apiKey);
        HttpURLConnection hr = (HttpURLConnection) url.openConnection();

        if(hr.getResponseCode() == 200) {
           InputStream im = hr.getInputStream();
           StringBuffer sb = new StringBuffer();
           BufferedReader br = new BufferedReader(new InputStreamReader(im));
           String line = br.readLine();

           JSONObject response = new JSONObject(line);
           System.out.println(response);

           JSONArray array = response.getJSONArray("items");

           JSONObject arrayItems = array.getJSONObject(0);

           JSONObject position = arrayItems.getJSONObject("position");
           System.out.println(position.getDouble("lat") + " : " + position.getDouble("lng"));

        }
    }

}
