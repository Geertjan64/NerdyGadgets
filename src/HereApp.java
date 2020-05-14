import TSP.AlgoritmeNB;
import TSP.BezorgerSteden;
import TSP.Stad;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

public class HereApp extends JFrame implements ActionListener {
    private static String apiKey = "MLr6vmcH7IgZsaAqaSebZ42kxfRuY1SJyGdJL2GVhVk";
    private JTextField straatnaamJtf, huisnummerJtf, stadJtf;
    private JButton toevoegenAdres;
    private JButton startenRoute;
    private BezorgerSteden bezorgerSteden = new BezorgerSteden();
    private String url;
    private double longitude;
    private double latitude;

    public HereApp() throws IOException, JSONException {
        super("Inplannen route");

        setSize(1200,800);
        setLayout(new FlowLayout());

        JLabel straatnaamLbl = new JLabel("Straatnaam");
        JLabel huisnummerLbl = new JLabel("Huisnummer");
        JLabel stadLbl = new JLabel("Stad");

        startenRoute = new JButton("Start");

        straatnaamJtf = new JTextField(30);
        huisnummerJtf = new JTextField(8);
        stadJtf = new JTextField(10);

        startenRoute.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ArrayList<Stad> steden = new ArrayList<Stad>();
                steden.addAll(bezorgerSteden.initialSteden);
                bezorgerSteden.printKortsteRoute(new AlgoritmeNB().vindKortsteRoute(steden));
            }
        });

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
                    getLongitudeLangitude(straatnaamStr, huisnummerStr, stadStr);
                } catch (IOException | JSONException ex) {
                    ex.printStackTrace();
                }

                System.out.println(bezorgerSteden.getInitialSteden());
//                System.out.println(url);
            }
        });

        add(straatnaamLbl);
        add(straatnaamJtf);
        add(huisnummerLbl);
        add(huisnummerJtf);
        add(stadLbl);
        add(stadJtf);
        add(toevoegenAdres);
        add(startenRoute);

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
