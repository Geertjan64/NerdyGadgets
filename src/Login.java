import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Login extends JFrame implements ActionListener {
    private KeuzeMenu newScherm = new KeuzeMenu();
    private JButton blogin;
    private JPanel loginpanel;
    private JTextField txuser;
    private JTextField pass;
    private JLabel username;
    private JLabel password;

    public Login() {
        super("Login Authentication");

        blogin = new JButton("Login");
        loginpanel = new JPanel();
        txuser = new JTextField(15);
        pass = new JPasswordField(15);
        username = new JLabel("User - ");
        password = new JLabel("Pass - ");

        setSize(300,200);
        setLocation(500, 280);
        loginpanel.setLayout(null);


        txuser.setBounds(70, 30, 150, 20);
        pass.setBounds(70, 65, 150, 20);
        blogin.setBounds(110, 100, 80, 20);
        username.setBounds(20, 28, 80, 20);
        password.setBounds(20, 63, 80, 20);

        loginpanel.add(blogin);
        loginpanel.add(txuser);
        loginpanel.add(pass);
        loginpanel.add(username);
        loginpanel.add(password);
        blogin.addActionListener(this);

        getContentPane().add(loginpanel);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(true);
        setVisible(true);
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
    }

    public void checkAccount(String user, String pass) throws SQLException {
        DatabaseReader acc = new DatabaseReader();
        Connection dbc = acc.getConnection();
        String query = "SELECT * FROM `employee` WHERE `Email` = '"+user+"' AND `Password` = '"+pass+"'";

        Statement st = dbc.createStatement();
        ResultSet r = st.executeQuery(query);

        while (r.next()) {

            int Employee_ID = r.getInt("Employee_ID");
            String Firstname = r.getString("Firstname");
            String Lastname = r.getString("Lastname");
            String Middle_Name = r.getString("Middle_Name");
            String Email = r.getString("Email");
            String Password = r.getString("Password");
            String Function = r.getString("Function");

            if (user.equalsIgnoreCase(Email) && pass.equalsIgnoreCase(Password)) {
                if(Function.equalsIgnoreCase("planner")) {
                    Planner planner = new Planner(Employee_ID, Firstname, Lastname, Middle_Name, Email, Password, Function);
                    newScherm.setTitle(newScherm.getTitle() + planner.getVoornaam() + " : " + planner.getFunction());
                }
                this.setVisible(false);
                newScherm.setVisible(true);
                JOptionPane.showMessageDialog(this, "Welkom "+Function+"!");
            } else {
                JOptionPane.showMessageDialog(this, "Invalid Username or Password");
                this.pass.setText(null);
            }

        }
    }

    public static void main(String[] args) {
        new Login();
    }

}
