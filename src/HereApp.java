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
    private JTextField straatnaamJtf, huisnummerJtf, stadJtf;
    private JButton toevoegenAdres;
    private String url;

    public HereApp(){
        super("Inplannen route");
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
                String straatnaamStr = straatnaamJtf.getText();
                String huisnummerStr = huisnummerJtf.getText();
                String stadStr = stadJtf.getText();
                url = "https://geocode.search.hereapi.com/v1/geocode?q=" + straatnaamStr + "+" + huisnummerStr + "%2C+" + stadStr + "&apiKey="+apiKey;
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
    private static String apiKey = "MLr6vmcH7IgZsaAqaSebZ42kxfRuY1SJyGdJL2GVhVk";

    public static void main(String[] args) throws IOException, JSONException {

        URL url = new URL("https://geocode.search.hereapi.com/v1/geocode?q=vanOldenbarneveltstraat+57%2C+Nijkerk&apiKey="+apiKey);
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

           HereApp test = new HereApp();

        }
    }

    public void getAdress(){

    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {

    }
}
