package Default.Bezorgers;

import Default.Login;
import SQL.SQLFuncties;

import javax.swing.*;
import java.awt.*;
import java.sql.SQLException;

public class BezorgerGemaakteRitten extends JFrame {

    private SQLFuncties sql = new SQLFuncties();

    public BezorgerGemaakteRitten(int id) throws SQLException {
        super("Alle ritten inzien voor: "+ Login.voornaam);

        JList listGemaakte = new JList<>(sql.inzienBezorgdeRouteBijBezorger(id));
        JList listOpenstaand = new JList<>(sql.inzienOpenstaandeRouteBijBezorger(id));

        JScrollPane scrollGemaakteRoutes = new JScrollPane(listGemaakte);
        JScrollPane scrollOpenstaandeRoutes = new JScrollPane(listOpenstaand);

        JPanel p1 = new JPanel(new BorderLayout());
        JPanel p2 = new JPanel(new BorderLayout());

        p1.add(scrollGemaakteRoutes);
        p2.add(scrollOpenstaandeRoutes);

        JTabbedPane tp = new JTabbedPane();

        tp.setBounds(50, 50, 200, 200);
        tp.add("Gemaakte ritten", p1);
        tp.add("Openstaande ritten", p2);

        add(tp);
        setSize(400, 400);
        setResizable(false);
        setVisible(true);
    }

}
