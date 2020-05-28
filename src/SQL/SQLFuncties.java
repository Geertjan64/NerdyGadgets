package SQL;

import Default.Entiteit.AdressenLijst;
import Default.Entiteit.Bezorger;
import Default.Entiteit.BezorgerLijst;
import Default.Entiteit.Routes;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
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
        DatabaseConnector bezorger = new DatabaseConnector();
        Connection dbc = bezorger.getConnection();

        Statement st = dbc.createStatement();
        ResultSet r = st.executeQuery("SELECT * FROM employee WHERE function = 'Bezorger' ");

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

    public void getBezorgersActiviteit() throws SQLException {
        DatabaseConnector bezorger = new DatabaseConnector();
        Connection dbc = bezorger.getConnection();

        Statement st = dbc.createStatement();
        ResultSet r = st.executeQuery("SELECT * FROM employee WHERE function = 'Bezorger' AND active = 1 ");

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
        DatabaseConnector bezorger = new DatabaseConnector();
        Connection dbc = bezorger.getConnection();

        Statement st = dbc.createStatement();
        ResultSet r = st.executeQuery("SELECT Address_ID, City, Street_Name, House_Number, Province FROM customer " +
                "INNER JOIN orders ON customer.Customer_ID = orders.Customer_ID " +
                "INNER JOIN address ON address.Address_ID = customer.Address_1 WHERE orders.status = 0 " +
                "AND address.Province='" + provincie + "'");
        AdressenLijst al = new AdressenLijst();
        al.clearAdressen();

        while (r.next()) {
            al.addAdres(r.getInt("Address_ID"), r.getString("Street_Name"), r.getInt("House_Number"), r.getString("City"));
        }

    }

    public void updateActief(int activiteit, int werknemerID) throws SQLException {
        DatabaseConnector bezorger = new DatabaseConnector();
        Connection dbc = bezorger.getConnection();

        Statement st = dbc.createStatement();
        st.executeUpdate("UPDATE employee SET Active = " + activiteit + " WHERE Employee_ID = " + werknemerID);

    }

    public void getBezorgerGegevens(int employee_ID) throws SQLException {
        DatabaseConnector bezorgerGegevens = new DatabaseConnector();
        Connection dbc = bezorgerGegevens.getConnection();

        Statement st = dbc.createStatement();
        st.executeQuery("SELECT City, Zip_Code, Country, Street_Name, House_Number, Addition from address " +
                "WHERE Address_ID = " +
                "((SELECT Address_1 FROM customer WHERE Customer_ID IN " +
                "(SELECT Customer_ID FROM orders WHERE Deliverer_ID = " + employee_ID + "))) ");
    }

    public void insertKortsteRoute(String route, String provincie, int ID) throws SQLException {
        DatabaseConnector routeGegevens = new DatabaseConnector();
        Connection dbc = routeGegevens.getConnection();

        Statement st = dbc.createStatement();
        st.executeUpdate("INSERT INTO optimal_route (route, province, deliverer_ID, Delivered) VALUES ('" + route + "', '" + provincie + "', " + ID + ", 0)");
    }

    public void editKlant() {

    }

    public ListModel inzienOpenstaandeRouteBijBezorger(int bezorgerId) throws SQLException {
        DefaultListModel<Routes> model = new DefaultListModel<Routes>();
        DatabaseConnector db = new DatabaseConnector();
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

    public ListModel inzienBezorgdeRouteBijBezorger(int bezorgerId) throws SQLException {
        DefaultListModel<Routes> model = new DefaultListModel<Routes>();
        DatabaseConnector db = new DatabaseConnector();
        Connection dbc = db.getConnection();
        Statement st = dbc.createStatement();
        ResultSet r = st.executeQuery("SELECT * FROM optimal_route WHERE Deliverer_ID=" + bezorgerId + " AND Delivered = 1");

        while (r.next()) {
            int id = r.getInt("Route_ID");
            String route = r.getString("Route");
            model.addElement(new Routes(id, route));
        }
        return model;
    }

    public ListModel stappenRoute(int routeId) throws SQLException {
        DatabaseConnector acc = new DatabaseConnector();
        Connection dbc = acc.getConnection();
        String query = "SELECT * FROM `optimal_route` WHERE `Route_ID` = " + routeId + "";
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
        DatabaseConnector db = new DatabaseConnector();
        Connection dbc = db.getConnection();
        Statement st = dbc.createStatement();
        st.executeUpdate("UPDATE optimal_route SET Delivered=1 WHERE Route_ID="+routeId);
        st.executeUpdate("UPDATE orders SET Status=1 WHERE ");
    }

    public ListModel inzienRoutes() throws SQLException {
        DefaultListModel<String> model = new DefaultListModel<>();
        DatabaseConnector acc = new DatabaseConnector();
        Connection dbc = acc.getConnection();
        String query = "SELECT * FROM `optimal_route` WHERE `Delivered` = 0";

        Statement st = dbc.createStatement();
        ResultSet r = st.executeQuery(query);

        while(r.next()) {
            String routeid = r.getString("Route_ID");
            model.addElement("Route " + routeid);
        }
        return model;
    }

    public TableModel bezorgerActiviteit(int werknemer_ID) throws SQLException {
        // Temporary data
        Object[][] rowData = {{"Row1-Column1", "Row1-Column2", "Row1-Column3", "Row1-Column4", "Row1-Column5", "Row1-Column6"}};
        // Array for columnNames
        Object[] columnNames = {"Route", "Provincie"};
        DefaultTableModel mTableModel = new DefaultTableModel(rowData, columnNames);
        DatabaseConnector bezorgerGegevens = new DatabaseConnector();
        Connection dbc = bezorgerGegevens.getConnection();

        Statement st = dbc.createStatement();
        ResultSet rs = st.executeQuery("SELECT Route, Province FROM optimal_route WHERE Deliverer_ID="+werknemer_ID);

        // remove the temp row previously created
        mTableModel.removeRow(0);

        Object[] rows;
        // For each row
        while (rs.next()) {
            // adding values to temporary rows
            rows = new Object[]{rs.getString(1), rs.getString(2)};
            mTableModel.addRow(rows);
        }
        return mTableModel;
    }


    public void veranderenKlantgegevens(int id, int column, Object value) throws SQLException {
        DatabaseConnector db = new DatabaseConnector();
        Connection dbc = db.getConnection();
        Statement st = dbc.createStatement();
        if(column == 0){
            st.executeUpdate("UPDATE address SET city= '"+value+"' WHERE Address_ID= "+id+"");
        }
        if(column == 1){
            st.executeUpdate("UPDATE address SET Zip_Code= '"+value+"' WHERE Address_ID= "+id+"");
        }
        if(column == 2){
            st.executeUpdate("UPDATE address SET Street_Name= '"+value+"' WHERE Address_ID= "+id+"");
        }
        if(column == 3){
            st.executeUpdate("UPDATE address SET House_Number= '"+value+"' WHERE Address_ID= "+id+"");
        }
        if(column == 4){
            st.executeUpdate("UPDATE customer SET First_name= '"+value+"' WHERE Customer_ID= "+id+"");
        }
        if(column == 5){
            st.executeUpdate("UPDATE customer SET Last_name= '"+value+"' WHERE Customer_ID= "+id+"");
        }
    }

    public void veranderenOrdergegevens(int id, int column, Object value) throws SQLException {
        DatabaseConnector db = new DatabaseConnector();
        Connection dbc = db.getConnection();
        Statement st = dbc.createStatement();
        if(column == 0){
            System.out.print("Deze kolom mag niet worden aangepast");
        }
        if(column == 1){
            st.executeUpdate("UPDATE customer SET First_name= '"+value+"' WHERE Customer_ID= "+id+"");
        }
        if(column == 2){
            st.executeUpdate("UPDATE customer SET Last_name= '"+value+"' WHERE Customer_ID= "+id+"");
        }
        if(column == 3){
            st.executeUpdate("UPDATE product SET Product_Name= '"+value+"' WHERE ID_Product= "+id+"");
        }
        if(column == 4){
            st.executeUpdate("UPDATE product SET Quantity= '"+value+"' WHERE ID_Product= "+id+"");
        }
    }
}
