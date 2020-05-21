package Default.Beheerders;

import Default.Planners.ZienRoute;
import SQL.DatabaseReader;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class BeheerRoute extends JFrame{

    public BeheerRoute() throws SQLException {
        setTitle("Beheren route");
        setSize(1200, 800);
        setResizable(false);
        setMinimumSize(new Dimension(1200, 800));

        DefaultListModel<String> model = new DefaultListModel<>();
        JList<String> list = new JList<>( model );

        DatabaseReader acc = new DatabaseReader();
        Connection dbc = acc.getConnection();
        String query = "SELECT * FROM `optimal_route` WHERE `Delivered` = 0";

        Statement st = dbc.createStatement();
        ResultSet r = st.executeQuery(query);

        while(r.next()) {
            String routeid = r.getString("Route_ID");
            model.addElement(routeid);
        }

        add(list);

        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new FlowLayout());
        setLocationRelativeTo(null);
        setVisible(true);

        list.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent evt) {
                JList list = (JList)evt.getSource();
                if (evt.getClickCount() == 2) {

                    // Double-click detected
                    int index = list.locationToIndex(evt.getPoint());
                    int id = index+1;
                    try {
                        ZienRoute z = new ZienRoute(id);
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
    }
}
