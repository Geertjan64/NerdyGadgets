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

        JPanel p1 = new JPanel();
        JPanel p2 = new JPanel();

        p1.add(listGemaakte);
        p2.add(listOpenstaand);

        JTabbedPane tp = new JTabbedPane();

        tp.setBounds(50, 50, 200, 200);
        tp.add("Gemaakte ritten", p1);
        tp.add("Openstaande ritten", p2);

        add(tp);
        setSize(400, 400);
        setVisible(true);
    }

}
