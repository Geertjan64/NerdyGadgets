package Default.Planners;

import SQL.DatabaseReader;


import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Arrays;

public class ZienRoute extends JFrame implements ActionListener{
    private JPanel buttonPanel;
    private JButton verwijderen = new JButton("Verwijderen");
    private JButton wijzigen = new JButton("Wijzigen");
    private DefaultListModel<String> model = new DefaultListModel<>();
    private JList<String> list = new JList<>(model);
    private String[] temp;
    private int id;

    public ZienRoute(int id) throws SQLException {
        this.id = id;
        setTitle("Inzien route adressen");
        setSize(1200, 800);
        setResizable(false);
        setMinimumSize(new Dimension(1200, 800));
        buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(0,1));

        list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        list.setFixedCellHeight(50);
        list.setBorder(new EmptyBorder(10, 10, 10, 10));

        DatabaseReader acc = new DatabaseReader();
        Connection dbc = acc.getConnection();
        String query = "SELECT * FROM `optimal_route` WHERE `Route_ID` = " + id + "";
        Statement st = dbc.createStatement();
        ResultSet r = st.executeQuery(query);
        r.first();
        String R = r.getString("Route");

        String delimiter = " \\[";
        temp = R.split(delimiter);

        for (int i = 0; i < temp.length; i++) {
            model.addElement(temp[i]);
        }

        verwijderen.addActionListener(this);
        wijzigen.addActionListener(this);

        add(list);
        buttonPanel.add(verwijderen);
        buttonPanel.add(wijzigen);
        add(buttonPanel);

        validate();
        repaint();
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new FlowLayout());
        setLocationRelativeTo(null);
        setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == verwijderen) {
            String newRoute;
            int index = list.getSelectedIndex();
            temp[index] = "";
            System.out.println(temp[index]);
            newRoute = convertArrayToStringUsingStreamAPI(temp);
            model.remove(index);

            DatabaseReader acc = new DatabaseReader();
            Connection dbc = acc.getConnection();
            String query = "UPDATE `optimal_route` SET `Route`= '"+ newRoute +"' WHERE `Route_ID` = " + id + "";

            Statement st = null;
            try {
                st = dbc.createStatement();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
            try {
                st.executeUpdate(query);
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
        if (e.getSource() == wijzigen) {
            System.out.println("ik wijzig spul UwU");
        }
    }

    public static String convertArrayToStringUsingStreamAPI(String[] strArray) {
        String joinedString = String.join(" ", strArray);
        return joinedString;
    }
}
