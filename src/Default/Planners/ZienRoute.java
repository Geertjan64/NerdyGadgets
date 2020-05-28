package Default.Planners;

import SQL.DatabaseConnector;


import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

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

        DatabaseConnector acc = new DatabaseConnector();
        Connection dbc = acc.getConnection();
        String query = "SELECT * FROM `optimal_route` WHERE `Route_ID` = " + id + "";
        Statement st = dbc.createStatement();
        ResultSet r = st.executeQuery(query);
        r.first();
        String R = r.getString("Route");

        temp = R.split("(?= \\[)");

        for (int i = 0; i < temp.length; i++) {
            model.addElement(temp[i]);
        }

        verwijderen.addActionListener(this);
        wijzigen.addActionListener(this);

        JScrollPane scrollPane1 = new JScrollPane(list);
        scrollPane1.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);

        add(scrollPane1);
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
            int index = list.getSelectedIndex();
            temp[index] = "";
            model.remove(index);
            updateRoute(temp);
        }
        if (e.getSource() == wijzigen) {
            int index = list.getSelectedIndex();
            temp[index] = JOptionPane.showInputDialog(temp[index], temp[index]);
            updateRoute(temp);
        }
    }

    public static String convertArrayToStringUsingStreamAPI(String[] strArray) {
        String joinedString = String.join(" ", strArray);
        return joinedString;
    }

    public void updateRoute (String[] temp){
        String newRoute;
        newRoute = convertArrayToStringUsingStreamAPI(temp);
        DatabaseConnector acc = new DatabaseConnector();
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
}
