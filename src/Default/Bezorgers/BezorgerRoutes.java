package Default.Bezorgers;

import Default.Entiteit.Routes;
import Default.Login;
import SQL.SQLFuncties;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.SQLException;

public class BezorgerRoutes extends JFrame implements ActionListener  {

    private SQLFuncties sql = new SQLFuncties();

    private JButton jb = new JButton("Route afronden");
    private JLabel routeTitle = new JLabel("Routes voor: "+Login.voornaam + " " + Login.achternaam);
    private Routes route;
    private JList<String> list = new JList<>(sql.inzienOpenstaandeRouteBijBezorger(Login.acc_id));

    public BezorgerRoutes() throws SQLException {
        super("Route inzien voor: "+Login.voornaam);
        setLayout(new FlowLayout());
        JPanel routepanel = new JPanel(new BorderLayout());
        jb.addActionListener(this);

        list.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                if (e.getValueIsAdjusting()) {
                    route = (Routes) ((JList) e.getSource())
                            .getSelectedValue();
                }
            }
        });

        list.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent evt) {
                JList list = (JList)evt.getSource();
                if (evt.getClickCount() == 2 && !list.isSelectionEmpty()) {
                    try {
                        BezorgerRoutesInzien b = new BezorgerRoutesInzien(route.getId());
                    } catch (SQLException throwables) {
                        throwables.printStackTrace();
                    }
                }
            }
        });

        routeTitle.setLabelFor(list);
        routepanel.add(routeTitle, BorderLayout.NORTH);
        routepanel.add(new JScrollPane(list));
        add(routepanel, BorderLayout.WEST);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        add(jb);

        setSize(new Dimension(450, 450));
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == jb) {
            try {
                int a = JOptionPane.showConfirmDialog(this, "Weet je zeker dat je Route "+route.getId()+" wilt afronden?", "Route "+route.getId(), JOptionPane.OK_CANCEL_OPTION);
                if(a == JOptionPane.YES_OPTION) {
                    JOptionPane.showMessageDialog(this,"Route "+route.getId()+" is afgerond!");
                    sql.updateRoute(route.getId());
                    repaint();
                    dispose();
                }
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
    }
}
