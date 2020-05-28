package Default.Planners;

import Default.Entiteit.AdressenLijst;
import Default.Entiteit.BezorgerLijst;
import SQL.DatabaseConnector;
import SQL.SQLFuncties;
import Default.TSP.AlgoritmeNB;
import Default.TSP.BezorgerSteden;
import Default.TSP.Stad;
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
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class InplannenRoute extends JFrame implements ActionListener {
    private static String apiKey = "MLr6vmcH7IgZsaAqaSebZ42kxfRuY1SJyGdJL2GVhVk";
    private InplannenRoute inplannenRoute;
    public static int bezorgerID;
    private JLabel gekozenBezorger = new JLabel("Let op, je hebt geen bezorger gekozen!");
    private JList<String> bezorglijst;
    private JList<String> adresLijst;
    private DefaultListModel model;
    private DefaultListModel lijstItem;
    private BezorgerLijst bezorgerLijst = new BezorgerLijst();
    private AdressenLijst adressenLijst = new AdressenLijst();
    private String straatnaamStr;
    private int huisnummerint;
    private String stadStr;
    private JButton startenRoute;
    private BezorgerSteden bezorgerSteden = new BezorgerSteden();
    private double longitude;
    private double latitude;
    private String provincie;
    private static SQLFuncties f = new SQLFuncties();
    private ArrayList<String> list = new ArrayList<>();
    private ArrayList<String> route = new ArrayList<>();


    public InplannenRoute() throws SQLException {
        super("Inplannen route");
        list.add("Noord-holland");
        list.add("Zuid-holland");
        list.add("Utrecht");
        list.add("Drenthe");
        list.add("Overijssel");
        list.add("Gelderland");
        list.add("Groningen");
        list.add("Noord-Brabant");
        list.add("Limburg");
        list.add("Friesland");
        list.add("Flevoland");
        list.add("Zeeland");
        JLabel keuze1 = new JLabel("Kies een adres om toe te voegen aan uw route:");
        JLabel keuze2 = new JLabel("Kies uw bezorger:");

        JLabel gemaakteRoute = new JLabel("Opgestelde route: ");
        setLayout(new FlowLayout());
        JPanel panel = new JPanel(new BorderLayout());
        JPanel panel2 = new JPanel(new BorderLayout());
        JPanel panel3 = new JPanel(new BorderLayout());
        setSize(1200, 800);

        /** Ophalen openstaande bezorg adressen **/
        adresLijst = new JList<>(new DefaultListModel<>());
        lijstItem = (DefaultListModel) adresLijst.getModel();

        /** Ophalen provincies beschikbaar **/
        JComboBox provincies = new JComboBox();
        provincies.setModel(new DefaultComboBoxModel<String>(list.toArray(new String[0])));
        String selectedProvincie = String.valueOf(provincies.getSelectedItem());
        f.getAdressenBijProvincie("Noord-Holland");

        provincies.addActionListener(e -> {
            JComboBox comboBox = (JComboBox) e.getSource();
            Object selected = comboBox.getSelectedItem();
            getAdressen(selected.toString());
        });

        JScrollPane scrollijst = new JScrollPane(adresLijst);
        scrollijst.setPreferredSize(new Dimension(250, 125));

        adresLijst.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 2 && !adresLijst.isSelectionEmpty()) {
                    String selectedItem = adresLijst.getSelectedValue();
                    if (!route.contains(selectedItem)) {
                        route.add(selectedItem);

                        String[] temp;
                        String delimiter = " ";

                        temp = selectedItem.split(delimiter);
                        if (temp[1].matches("[0-9]+")) {
                            straatnaamStr = temp[0];
                            huisnummerint = Integer.parseInt(temp[1]);
                        } else {
                            straatnaamStr = temp[0] + " " + temp[1];
                            huisnummerint = Integer.parseInt(temp[2]);
                        }


                        DatabaseConnector acc = new DatabaseConnector();
                        Connection dbc = acc.getConnection();
                        String query = "SELECT * FROM `address` WHERE `Street_Name` = '" + straatnaamStr + "' AND `House_Number` = " + huisnummerint + "";

                        Statement st;

                        try {
                            st = dbc.createStatement();

                            ResultSet r;
                            r = st.executeQuery(query);
                            if(r.next()) {

                                straatnaamStr = r.getString("Street_Name");


                                huisnummerint = r.getInt("House_Number");
                                stadStr = r.getString("City");


                                getLongitudeLangitude(straatnaamStr, huisnummerint, stadStr);

                            }
                        } catch (IOException | JSONException | SQLException ex) {
                            ex.printStackTrace();
                        }

                    } else {
                        JOptionPane.showMessageDialog(inplannenRoute,"Het adres is al toegevoegd aan de lijst!");
                    }
                    gemaakteRoute.setText("Opgestelde route: " + route.toString());
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
        panel3.add(provincies);


        /** Ophalen bezorgers en in een Scrollable list zetten **/
        f.getBezorgersActiviteit();
        bezorglijst = new JList<>(new DefaultListModel<>());
        model = (DefaultListModel) bezorglijst.getModel();

        for (int i = 0; i < bezorgerLijst.getBezorgers().size(); i++) {
            model.addElement(bezorgerLijst.getBezorgers().get(i).toString());
        }
        JScrollPane scrollableList = new JScrollPane(bezorglijst);
        scrollableList.setPreferredSize(new Dimension(250, 125));

        keuze1.setLabelFor(scrollijst);
        panel.add(keuze1, BorderLayout.NORTH);
        panel.add(gemaakteRoute, BorderLayout.SOUTH);
        panel.add(scrollijst);

        keuze2.setLabelFor(scrollableList);
        panel2.add(keuze2, BorderLayout.NORTH);
        panel2.add(gekozenBezorger, BorderLayout.SOUTH);
        panel2.add(scrollableList);

        startenRoute = new JButton("Start");

        startenRoute.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ArrayList<Stad> steden = new ArrayList<Stad>();
                steden.addAll(bezorgerSteden.initialSteden);
                if (bezorgerID == 0) {
                    JOptionPane.showMessageDialog(inplannenRoute, "U moet een bezorger kiezen");
                }
                if (steden.size() < 3) {
                    JOptionPane.showMessageDialog(inplannenRoute, "U moet meer dan 2 adressen opgeven.");
                } else {
                    try {
                        int kortsterouteafstand = bezorgerSteden.getAfstandKortsteRoute(new AlgoritmeNB().vindKortsteRoute(steden));
                        JOptionPane.showMessageDialog(inplannenRoute, "De afstand van de route: "+ kortsterouteafstand+"km");
                    } catch (SQLException ex) {
                        ex.printStackTrace();
                    } catch (JSONException ex) {
                        ex.printStackTrace();
                    } catch (IOException ex) {
                        ex.printStackTrace();
                    }
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
        add(startenRoute);
        add(panel3);
        add(panel);
        add(panel2);

        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setResizable(true);
        setVisible(true);

    }

    public static void main(String[] args) throws IOException, JSONException, SQLException {
        InplannenRoute h = new InplannenRoute();
        f.getAdressenBijProvincie("Gelderland");
    }

    public void getLongitudeLangitude(String straatnaam, int huisnummer, String stad) throws IOException, JSONException {

        URL apiurl = new URL("https://geocode.search.hereapi.com/v1/geocode?q=" + straatnaam.replaceAll("\\s+", "") + "+" + huisnummer + "%2C+" + stad + "&apiKey=" + apiKey);
        HttpURLConnection hr = (HttpURLConnection) apiurl.openConnection();

        if (hr.getResponseCode() == 200) {
            InputStream im = hr.getInputStream();
            StringBuffer sb = new StringBuffer();
            BufferedReader br = new BufferedReader(new InputStreamReader(im));
            String line = br.readLine();

            JSONObject response = new JSONObject(line);


            JSONArray array = response.getJSONArray("items");

            JSONObject arrayItems = array.getJSONObject(0);

            JSONObject position = arrayItems.getJSONObject("position");

            JSONObject adressinfo = arrayItems.getJSONObject("address");

            latitude = position.getDouble("lat");
            longitude = position.getDouble("lng");
            provincie = adressinfo.getString("state");

            Stad nieuweStad = new Stad(stad, latitude, longitude, this.straatnaamStr, this.huisnummerint, provincie);
            bezorgerSteden.addStad(nieuweStad);


        }
    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {

    }

    public void getAdressen(String provincie) {
        try {
            lijstItem.clear();
            f.getAdressenBijProvincie(provincie);
            for (int i = 0; i < adressenLijst.getAdressen().size(); i++) {
                lijstItem.addElement(adressenLijst.getAdressen().get(i).toString());
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
}
