import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class SQLFuncties {

    int teller;

    int id;
    String voornaam;
    String achternaam;
    String tussenvoegsel;
    String email;
    String password;
    String functie;

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

            bezorgerLijst.addBezorger(new Bezorger(id,voornaam,achternaam, tussenvoegsel, email));
            teller++;

        }
    }

}
