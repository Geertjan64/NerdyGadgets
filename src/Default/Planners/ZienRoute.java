package Default.Planners;

import SQL.DatabaseReader;

import javax.swing.*;
import java.awt.*;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class ZienRoute extends JFrame {

    public ZienRoute(int id) throws SQLException {
        setTitle("Beheren route");
        setSize(1200, 800);
        setResizable(false);
        setMinimumSize(new Dimension(1200, 800));

        DefaultListModel<String> model = new DefaultListModel<>();
        JList<String> list = new JList<>(model);

        DatabaseReader acc = new DatabaseReader();
        Connection dbc = acc.getConnection();
        String query = "SELECT * FROM `optimal_route` WHERE `Route_ID` = " + id + "";
        Statement st = dbc.createStatement();
        ResultSet r = st.executeQuery(query);
        r.first();
        String R = r.getString("Route");
        for (String ro : R.split("\\[", 0)) {
            model.addElement(ro);
        }

        add(list);

        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new FlowLayout());
        setLocationRelativeTo(null);
        setVisible(true);
    }
}
