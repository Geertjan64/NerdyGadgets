package Default;

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
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;

public class HereApp extends JFrame implements ActionListener {
    private static String apiKey = "MLr6vmcH7IgZsaAqaSebZ42kxfRuY1SJyGdJL2GVhVk";
    private JLabel gekozenBezorger;
    private int bezorgerID;
    private JList<String> bezorglijst;
    private DefaultListModel model;
    private BezorgerLijst bezorgerLijst = new BezorgerLijst();
    private JTextField straatnaamJtf, huisnummerJtf, stadJtf;
    private String straatnaamStr;
    private String huisnummerStr;
    private JButton toevoegenAdres;
    private JButton startenRoute;
    private BezorgerSteden bezorgerSteden = new BezorgerSteden();
    private String url;
    private double longitude;
    private double latitude;
    private String provincie;

    public HereApp() throws  IOException, JSONException, SQLException {
        super("Inplannen route");
        JPanel panel = new JPanel();

        setSize(1200,800);
        setLayout(new FlowLayout());

        JLabel straatnaamLbl = new JLabel("Straatnaam");
        JLabel huisnummerLbl = new JLabel("Huisnummer");
        JLabel stadLbl = new JLabel("Stad");

        SQLFuncties f = new SQLFuncties();
        f.getDataRows();

        bezorglijst = new JList<>(new DefaultListModel<>());
        model = (DefaultListModel) bezorglijst.getModel();

        System.out.println(bezorgerLijst.getBezorgers().size());

        for (int i = 0; i < bezorgerLijst.getBezorgers().size(); i++) {
            model.addElement(bezorgerLijst.getBezorgers().get(i).toString());
        }

        JScrollPane scrollableList = new JScrollPane(bezorglijst);
        scrollableList.setPreferredSize(new Dimension(250, 125));

        JLabel kiesBezorger = new JLabel("Kies een bezorger uit de lijst:");
        add(kiesBezorger);

        panel.add(scrollableList);
        add(panel);

        startenRoute = new JButton("Start");

        straatnaamJtf = new JTextField(30);
        huisnummerJtf = new JTextField(8);
        stadJtf = new JTextField(10);

        gekozenBezorger = new JLabel("Let op, je hebt geen bezorger gekozen!");

        startenRoute.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ArrayList<Stad> steden = new ArrayList<Stad>();
                steden.addAll(bezorgerSteden.initialSteden);
                try {
                    bezorgerSteden.printKortsteRoute(new AlgoritmeNB().vindKortsteRoute(steden));
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
        });

        bezorglijst.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 2 && !bezorglijst.isSelectionEmpty()) {
                    String selectedItem = bezorglijst.getSelectedValue();
                    gekozenBezorger.setText("Gekozen bezorger: " + selectedItem);
                    for (int i = 0; i < bezorgerLijst.getBezorgers().size(); i++) {
                        if (selectedItem.substring(1, 5).equals(bezorgerLijst.getBezorgers().get(i).toString().substring(1, 5))) {
                            bezorgerID = bezorgerLijst.getBezorgers().get(i).getWerknemerID();
                        }
                    }
//                    repaint();
                }
            }

            @Override
            public void mousePressed(MouseEvent e) {

            }

            @Override
            public void mouseReleased(MouseEvent e) {

            }

            @Override
            public void mouseEntered(MouseEvent e) {

            }

            @Override
            public void mouseExited(MouseEvent e) {

            }
        });

        toevoegenAdres = new JButton("Toevoegen adres");
        toevoegenAdres.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String straatnaamSpaces = straatnaamJtf.getText();
                straatnaamStr = straatnaamSpaces.replaceAll("\\s+","");
                huisnummerStr = huisnummerJtf.getText();
                String stadStr = stadJtf.getText();
                url = "https://geocode.search.hereapi.com/v1/geocode?q=" + straatnaamStr + "+" + huisnummerStr + "%2C+" + stadStr + "&apiKey="+apiKey;


                try {
                    getLongitudeLangitude(straatnaamStr, huisnummerStr, stadStr);
                } catch (IOException | JSONException ex) {
                    ex.printStackTrace();
                }

                System.out.println(bezorgerSteden.getInitialSteden());
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
        add(gekozenBezorger);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(true);
        setVisible(true);

    }

    public int getBezorgerID() {
        return this.bezorgerID;
    }

    public static void main(String[] args) throws IOException, JSONException, SQLException {
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

            JSONObject adressinfo = arrayItems.getJSONObject("address");

            latitude = position.getDouble("lat");
            longitude = position.getDouble("lng");
            provincie = adressinfo.getString("state");

            Stad nieuweStad = new Stad(stad, latitude, longitude, this.straatnaamStr, this.huisnummerStr, provincie);
            bezorgerSteden.addStad(nieuweStad);

            System.out.println(position.getDouble("lat") + " : " + position.getDouble("lng") + " : " + adressinfo.getString("state"));
        }
    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {

    }
}
