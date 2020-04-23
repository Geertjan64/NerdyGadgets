import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Login extends JFrame implements ActionListener {
    JButton blogin;
    JPanel loginpanel;
    JTextField txuser;
    JTextField pass;
    JLabel username;
    JLabel password;

    public Login() {
        super("Login Autentification");

        blogin = new JButton("Login");
        loginpanel = new JPanel();
        txuser = new JTextField(15);
        pass = new JPasswordField(15);
        username = new JLabel("User - ");
        password = new JLabel("Pass - ");

        setSize(1000, 800);
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
        setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {
        //login button
        if (e.getSource() == blogin) {
            String userText;
            String pwdText;
            userText = txuser.getText();
            pwdText = pass.getText();
            if (userText.equalsIgnoreCase("testmed") && pwdText.equalsIgnoreCase("test123")) {
                //JOptionPane.showMessageDialog(this, "Login Successful");

            } else {
                JOptionPane.showMessageDialog(this, "Invalid Username or Password");
                pass.setText(null);
            }
        }
    }

    public static void main(String[] args) {
        new Login();
    }

}
