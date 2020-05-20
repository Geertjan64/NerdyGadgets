package Default;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.SQLException;

public class BeheerRoute extends JFrame{

    public BeheerRoute() throws SQLException {
        setTitle("Bezorgers beheren");
        setSize(1200, 800);
        setResizable(false);
        setMinimumSize(new Dimension(1200, 800));
//        String[] items = {"A", "B", "C", "D"};
//        JList list = new JList(items);
//
//        list.addMouseListener(new MouseAdapter() {
//            public void mouseClicked(MouseEvent evt) {
//                JList list = (JList)evt.getSource();
//                if (evt.getClickCount() == 2) {
//
//                    // Double-click detected
//                    int index = list.locationToIndex(evt.getPoint());
//                }
//            }
//        });
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new FlowLayout());
        setLocationRelativeTo(null);
        setVisible(true);
    }

    public static void main(String[] args) throws SQLException {
        BeheerRoute br = new BeheerRoute();
    }
}
