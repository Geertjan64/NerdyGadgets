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
import java.util.ArrayList;
import java.util.List;

public class ZienRoute extends JFrame {
    private JPanel buttonPanel;

    public ZienRoute(int id) throws SQLException {
        setTitle("Beheren route");
        setSize(1200, 800);
        setResizable(false);
        setMinimumSize(new Dimension(1200, 800));
        buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(0,1));
        JPanel listPanel = new JPanel();

        JButton verwijderen = new JButton("Verwijderen");
        JButton wijzigen = new JButton("Wijzigen");

        DefaultListModel<String> model = new DefaultListModel<>();
        JList<String> list = new JList<>(model);


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

        String[] temp;
        String delimiter = " \\[";
        temp = R.split(delimiter);

        JButton[] buttons  = new JButton[temp.length*2];

        for (int i = 0; i < temp.length; i++) {
            model.addElement(temp[i]);
        }

        for (int i = 0; i < temp.length; i++) {
            JButton button = new JButton("Verwijderen");
            button.addActionListener((ActionListener) this);
            JButton button2 = new JButton("Wijzigen");
            button2.addActionListener((ActionListener) this);
        }

        listPanel.add(list);
        add(listPanel);
        add(buttonPanel);

        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new FlowLayout());
        setLocationRelativeTo(null);
        setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == "Verwijderen") {
            System.out.println("imma delete UwU");
        }
    }
}
