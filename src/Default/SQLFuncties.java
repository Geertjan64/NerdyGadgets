package Default;

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

    public void getDataRows() throws SQLException {
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

            bezorgerLijst.addBezorger(new Bezorger(id,voornaam,achternaam, tussenvoegsel, email, actief));
            teller++;

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
}
