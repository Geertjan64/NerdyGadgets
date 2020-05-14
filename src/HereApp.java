import TSP.BezorgerSteden;
import TSP.Stad;
import org.json.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class HereApp extends JFrame implements ActionListener {
    private static String apiKey = "MLr6vmcH7IgZsaAqaSebZ42kxfRuY1SJyGdJL2GVhVk";
    private JTextField straatnaamJtf, huisnummerJtf, stadJtf;
    private JButton toevoegenAdres;
    private BezorgerSteden bezorgerSteden;
    private String url;
    private double longitude;
    private double latitude;

    public HereApp() throws IOException, JSONException {
        super("Inplannen route");

        URL apiurl = new URL("https://geocode.search.hereapi.com/v1/geocode?q=vanOldenbarneveltstraat+57%2C+Nijkerk&apiKey="+apiKey);
        HttpURLConnection hr = (HttpURLConnection) apiurl.openConnection();

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

            latitude = position.getDouble("lat");
            longitude = position.getDouble("lng");

            System.out.println(position.getDouble("lat") + " : " + position.getDouble("lng"));
        }

        setSize(1200,800);
        setLayout(new FlowLayout());

        JLabel straatnaamLbl = new JLabel("Straatnaam");
        JLabel huisnummerLbl = new JLabel("Huisnummer");
        JLabel stadLbl = new JLabel("Stad");

        straatnaamJtf = new JTextField(30);
        huisnummerJtf = new JTextField(8);
        stadJtf = new JTextField(10);

        toevoegenAdres = new JButton("Toevoegen adres");
        toevoegenAdres.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String straatnaamSpaces = straatnaamJtf.getText();
                String straatnaamStr = straatnaamSpaces.replaceAll("\\s+","");
                String huisnummerStr = huisnummerJtf.getText();
                String stadStr = stadJtf.getText();
                url = "https://geocode.search.hereapi.com/v1/geocode?q=" + straatnaamStr + "+" + huisnummerStr + "%2C+" + stadStr + "&apiKey="+apiKey;


                try {
                    System.out.println(straatnaamStr);
                    System.out.println(huisnummerStr);
                    System.out.println(stadStr);
                    getLongitudeLangitude(straatnaamStr, huisnummerStr,stadStr);
                } catch (IOException | JSONException ex) {
                    ex.printStackTrace();
                }

                System.out.println(url);
            }
        });

        add(straatnaamLbl);
        add(straatnaamJtf);
        add(huisnummerLbl);
        add(huisnummerJtf);
        add(stadLbl);
        add(stadJtf);
        add(toevoegenAdres);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(true);
        setVisible(true);

    }
    /** HERE API INFORMATION **/

    public static void main(String[] args) throws IOException, JSONException {
        HereApp h = new HereApp();
    }

    public void getLongitudeLangitude(String straatnaam, String huisnummer, String stad) throws IOException, JSONException{
        URL apiurl = new URL("https://geocode.search.hereapi.com/v1/geocode?q=" + straatnaam + "+" + huisnummer + "%2C+" + stad + "&apiKey="+apiKey);
        HttpURLConnection hr = (HttpURLConnection) apiurl.openConnection();

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

            latitude = position.getDouble("lat");
            longitude = position.getDouble("lng");

            Stad nieuweStad = new Stad(stad, latitude, longitude);
            bezorgerSteden.addStad(nieuweStad);

            System.out.println(position.getDouble("lat") + " : " + position.getDouble("lng"));
        }
    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {

    }
}
