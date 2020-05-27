package Default;

import Default.Beheerders.BeheerRoute;
import Default.Beheerders.EditCustomer;
import Default.Beheerders.EditOrder;
import Default.Bezorgers.BezorgerBeheer;
import Default.Bezorgers.BezorgerGemaakteRitten;
import Default.Bezorgers.BezorgerRoutes;
import Default.Planners.InplannenRoute;
import Default.Planners.Planner;
import SQL.DatabaseReader;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Login extends JFrame implements ActionListener {

    /** Login information storage **/
    public static String voornaam;
    public static String achternaam;
    public static String functie;
    public static int acc_id;

    private KeuzeMenu newScherm = new KeuzeMenu();
    private JButton blogin;
    private JPanel loginpanel;
    private JTextField txuser;
    private JTextField pass;
    private JLabel username;
    private JLabel password;
    private Planner account;

    JButton hroutes = new JButton("Route starten");
    JButton routeinplannen = new JButton("Route inplannen");
    JButton gemaakteritten = new JButton("Bijhouden ritten");
    JButton beherenroute = new JButton("Beheren route");
    JButton beherenadressen = new JButton("Beheren adressen");
    JButton beherenbezorgers = new JButton("Beheren bezorgers");
    JButton beherenklantgegevens = new JButton("Beheren klantgegevens");
    JButton beherenorders = new JButton("Beheren orders");

    public Login() {
        super("Inloggen medewerker");

        blogin = new JButton("Inloggen");
        loginpanel = new JPanel();
        txuser = new JTextField(15);
        pass = new JPasswordField(15);
        username = new JLabel("Gebruikersnaam:");
        password = new JLabel("Wachtwoord:");

        setSize(300, 200);
        setLocation(500, 280);
        loginpanel.setLayout(null);

        txuser.setBounds(120, 30, 150, 20);
        pass.setBounds(120, 65, 150, 20);
        blogin.setBounds(70, 100, 150, 20);
        username.setBounds(20, 28, 150, 20);
        password.setBounds(20, 63, 150, 20);

        loginpanel.add(blogin);
        loginpanel.add(txuser);
        loginpanel.add(pass);
        loginpanel.add(username);
        loginpanel.add(password);
        blogin.addActionListener(this);
        routeinplannen.addActionListener(this);
        beherenbezorgers.addActionListener(this);
        beherenklantgegevens.addActionListener(this);
        beherenroute.addActionListener(this);
        beherenorders.addActionListener(this);
        hroutes.addActionListener(this);
        gemaakteritten.addActionListener(this);

        getContentPane().add(loginpanel);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(true);
        setVisible(true);
    }

    public void checkAccount(String user, String pass) throws SQLException {
        DatabaseReader acc = new DatabaseReader();
        Connection dbc = acc.getConnection();
        String query = "SELECT * FROM `employee` WHERE `Email` = '" + user + "' AND `Password` = '" + pass + "'";

            Statement st = dbc.createStatement();
            ResultSet r = st.executeQuery(query);
        if(r.isBeforeFirst()){

            while (r.next()) {

                int Employee_ID = r.getInt("Employee_ID");
                String Firstname = r.getString("Firstname");
                String Lastname = r.getString("Lastname");
                String Middle_Name = r.getString("Middle_Name");
                String Email = r.getString("Email");
                String Password = r.getString("Password");
                String Function = r.getString("Function");

                if (user.equalsIgnoreCase(Email) && pass.equalsIgnoreCase(Password)) {

                    if (Function.equalsIgnoreCase("bezorger")) {
                        /** Filling variables with login information **/
                        acc_id = Employee_ID;
                        functie = Function;
                        achternaam = Lastname;
                        voornaam = Firstname;

                        account = new Planner(Employee_ID, Firstname, Lastname, Middle_Name, Email, Password, Function);
                        newScherm.add(hroutes);
                        newScherm.add(gemaakteritten);
                        newScherm.setTitle(newScherm.getTitle() + account.getVoornaam() + " : " + account.getFunction());
                    }

                    if (Function.equalsIgnoreCase("beheerder")) {
                        account = new Planner(Employee_ID, Firstname, Lastname, Middle_Name, Email, Password, Function);
                        newScherm.add(beherenadressen);
                        newScherm.add(beherenbezorgers);
                        newScherm.add(beherenroute);
                        newScherm.add(beherenklantgegevens);
                        newScherm.add(beherenorders);
                        newScherm.setTitle(newScherm.getTitle() + account.getVoornaam() + " : " + account.getFunction());
                    }

                    if (Function.equalsIgnoreCase("planner")) {
                        account = new Planner(Employee_ID, Firstname, Lastname, Middle_Name, Email, Password, Function);
                        newScherm.add(routeinplannen);
                        newScherm.add(beherenroute);
                        newScherm.setTitle(newScherm.getTitle() + account.getVoornaam() + " : " + account.getFunction());
                    }

                    this.setVisible(false);
                    newScherm.setVisible(true);
                    JOptionPane.showMessageDialog(this, "Welkom " + Function + "!");
                }
            }
        }
        else {
            JOptionPane.showMessageDialog(this, "Verkeerde Email of Wachtwoord");
            this.pass.setText(null);
        }
    }

    public void actionPerformed(ActionEvent e) {
        //login button
        if (e.getSource() == blogin) {
            String userText;
            String pwdText;
            userText = txuser.getText();
            pwdText = pass.getText();
            try {
                checkAccount(userText, pwdText);
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
        if (e.getSource() == hroutes) {
            try {
                BezorgerRoutes r = new BezorgerRoutes();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }

        if (e.getSource() == routeinplannen) {
            try {
                InplannenRoute h = new InplannenRoute();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }

        }
        if (e.getSource() == gemaakteritten) {
            try {
                BezorgerGemaakteRitten b = new BezorgerGemaakteRitten(Login.acc_id);
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }

        if (e.getSource() == beherenadressen) {

        }

        if (e.getSource() == beherenklantgegevens) {
            try {
                EditCustomer ec = new EditCustomer();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }

        if (e.getSource() == beherenorders) {
            try {
                EditOrder eo = new EditOrder();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }

        if (e.getSource() == beherenbezorgers) {
            try {
                BezorgerBeheer b = new BezorgerBeheer();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }

        if (e.getSource() == beherenroute) {
            try {
                BeheerRoute br = new BeheerRoute();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }

    }
    public static void main(String[] args) {
        new Login();
    }

}
