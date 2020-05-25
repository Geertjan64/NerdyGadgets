package SQL;

import Default.Entiteit.AdressenLijst;
import Default.Entiteit.Bezorger;
import Default.Entiteit.BezorgerLijst;
import Default.Entiteit.Routes;

import javax.swing.*;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class SQLFuncties {

    private int teller;

    private int id;
    private String voornaam;
    private String achternaam;
    private String tussenvoegsel;
    private String email;
    private String password;
    private String functie;
    private int actief;

    public void getBezorgers() throws SQLException {
        DatabaseReader bezorger = new DatabaseReader();
        Connection dbc = bezorger.getConnection();

        Statement st = dbc.createStatement();
        ResultSet r = st.executeQuery("SELECT * FROM employee");

        BezorgerLijst bezorgerLijst = new BezorgerLijst();

        while (r.next()) {
            id = r.getInt("Employee_ID");
            voornaam = r.getString("Firstname");
            achternaam = r.getString("Lastname");
            tussenvoegsel = r.getString("Middle_Name");
            email = r.getString("Email");
            password = r.getString("Password");
            functie = r.getString("Function");
            actief = r.getInt("Active");

            bezorgerLijst.addBezorger(new Bezorger(id, voornaam, achternaam, tussenvoegsel, email, actief));
            teller++;

        }
    }

    public void getAdressenBijProvincie(String provincie) throws SQLException {
        DatabaseReader bezorger = new DatabaseReader();
        Connection dbc = bezorger.getConnection();

        Statement st = dbc.createStatement();
        ResultSet r = st.executeQuery("SELECT City, Street_Name, House_Number, Province FROM customer " +
                "INNER JOIN orders ON customer.Customer_ID = orders.Customer_ID " +
                "INNER JOIN address ON address.Address_ID = customer.Address_1 WHERE orders.status = 0 AND address.Province='" + provincie + "'");
        System.out.println("SELECT City, Street_Name, House_Number, Province FROM customer INNER JOIN orders ON customer.Customer_ID = orders.Customer_ID INNER JOIN address ON address.Address_ID = customer.Address_1 WHERE orders.status = 0 AND address.Province='" + provincie + "'");
        AdressenLijst al = new AdressenLijst();
        al.clearAdressen();

        while (r.next()) {
            al.addAdres(r.getString("Street_Name"), r.getInt("House_Number"), r.getString("City"));
        }

    }

    public void updateActief(int activiteit, int werknemerID) throws SQLException {
        DatabaseReader bezorger = new DatabaseReader();
        Connection dbc = bezorger.getConnection();

        Statement st = dbc.createStatement();
        st.executeUpdate("UPDATE employee SET Active = " + activiteit + " WHERE Employee_ID = " + werknemerID);

    }

    public void getBezorgerGegevens(int employee_ID) throws SQLException {
        DatabaseReader bezorgerGegevens = new DatabaseReader();
        Connection dbc = bezorgerGegevens.getConnection();

        Statement st = dbc.createStatement();
        st.executeQuery("SELECT City, Zip_Code, Country, Street_Name, House_Number, Addition from address " +
                "WHERE Address_ID = " +
                "((SELECT Address_1 FROM customer WHERE Customer_ID IN " +
                "(SELECT Customer_ID FROM orders WHERE Deliverer_ID = " + employee_ID + "))) ");
    }

    public void insertKortsteRoute(String route, String provincie, int ID) throws SQLException {
        DatabaseReader routeGegevens = new DatabaseReader();
        Connection dbc = routeGegevens.getConnection();

        Statement st = dbc.createStatement();
        st.executeUpdate("INSERT INTO optimal_route (route, province, deliverer_ID) VALUES ('" + route + "', '" + provincie + "', " + ID + ")");
    }

    public void editKlant() {

    }

    public ListModel inzienRouteBijBezorger(int bezorgerId) throws SQLException {
        DefaultListModel<Routes> model = new DefaultListModel<Routes>();
        DatabaseReader db = new DatabaseReader();
        Connection dbc = db.getConnection();
        Statement st = dbc.createStatement();
        ResultSet r = st.executeQuery("SELECT * FROM optimal_route WHERE Deliverer_ID=" + bezorgerId + " AND Delivered = 0");

        while (r.next()) {
            int id = r.getInt("Route_ID");
            String route = r.getString("Route");
            model.addElement(new Routes(id, route));
        }
        return model;
    }

    public ListModel stappenRoute(int routeid) throws SQLException {
        DatabaseReader acc = new DatabaseReader();
        Connection dbc = acc.getConnection();
        String query = "SELECT * FROM `optimal_route` WHERE `Route_ID` = " + routeid + "";
        Statement st = dbc.createStatement();
        ResultSet r = st.executeQuery(query);
        r.first();

        String temp[];
        DefaultListModel<String> model = new DefaultListModel<String>();
        String R = r.getString("Route");
        String delimiter = " \\[";
        temp = R.split(delimiter);
        for (int i = 0; i < temp.length; i++) {
            model.addElement(temp[i]);
        }
        return model;
    }

    public void updateRoute(int routeId) throws SQLException {
        DatabaseReader db = new DatabaseReader();
        Connection dbc = db.getConnection();
        Statement st = dbc.createStatement();
        st.executeUpdate("UPDATE optimal_route SET Delivered=1 WHERE Route_ID="+routeId);
    }

}
