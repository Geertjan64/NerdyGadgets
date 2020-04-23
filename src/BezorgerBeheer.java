import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class BezorgerBeheer extends JFrame {

    private JLabel titel;
    private JLabel naam;
    private JLabel aantalBezorgingen;
    private BezorgerLijst bezorgerLijst = new BezorgerLijst();
    private JList<String> bezorglijstInactief;
    private JList<String> bezorglijstActief;
    private DefaultListModel modelInactief;
    private DefaultListModel modelActief;

    public BezorgerBeheer() {
        setTitle("Bezorgers beheren");
        setSize(1600, 900);
        setResizable(false);
        setMinimumSize(new Dimension(1600, 900));
        setLayout(new GridLayout());
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        titel = new JLabel("Beheren bezorgers");
        naam = new JLabel("Naam");
        aantalBezorgingen = new JLabel("Aantal bezorgingen");

        // example employees
        Bezorger peter = new Bezorger(1234, "peter", "reter");
        Bezorger peter2 = new Bezorger(1235, "peter2", "reter");
        Bezorger peter3 = new Bezorger(1236, "peter3", "reter");

        bezorgerLijst.addBezorger(peter);
        bezorgerLijst.addBezorger(peter2);
        bezorgerLijst.addBezorger(peter3);
        // end example

        bezorglijstInactief = new JList<>(new DefaultListModel<>());
        bezorglijstActief = new JList<>(new DefaultListModel<>());

        modelInactief = (DefaultListModel) bezorglijstInactief.getModel();
        modelActief = (DefaultListModel) bezorglijstActief.getModel();

        for(int i = 0; i < bezorgerLijst.getBezorgers().size(); i++) {
            modelInactief.addElement(bezorgerLijst.getBezorgers().get(i).toString());
        }

        bezorglijstInactief.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 2) {
                    if (!bezorglijstInactief.isSelectionEmpty()) {
                        String selectedItem = bezorglijstInactief.getSelectedValue();
                        for (int i = 0; i < bezorgerLijst.getBezorgers().size(); i++) {
                            if (selectedItem.substring(1, 5).equals(bezorgerLijst.getBezorgers().get(i).toString().substring(1, 5))) {
                                bezorgerLijst.getBezorgers().get(i).setActief(true);
                                System.out.println(bezorgerLijst.getBezorgers().get(i).getActief());
                            }
                        }
                        // add selectedItem to your second list.
                        modelInactief.removeElement(selectedItem);
                        modelActief.addElement(selectedItem);
                    }
                }
            }

            @Override
            public void mousePressed(MouseEvent e) {}

            @Override
            public void mouseReleased(MouseEvent e) {}

            @Override
            public void mouseEntered(MouseEvent e) {}

            @Override
            public void mouseExited(MouseEvent e) {}
        });

        bezorglijstActief.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 2) {
                    if (!bezorglijstActief.isSelectionEmpty()) {
                        String selectedItem = bezorglijstActief.getSelectedValue();
                        for(int i = 0; i < bezorgerLijst.getBezorgers().size(); i++) {
                            if (selectedItem.substring(1, 5).equals(bezorgerLijst.getBezorgers().get(i).toString().substring(1, 5))) {
                                bezorgerLijst.getBezorgers().get(i).setActief(false);
                                System.out.println(bezorgerLijst.getBezorgers().get(i).getActief());
                            }
                        }
                        // add selectedItem to your first list.
                        modelInactief.addElement(selectedItem);
                        modelActief.removeElement(selectedItem);

                    }
                }
            }

            @Override
            public void mousePressed(MouseEvent e) {}

            @Override
            public void mouseReleased(MouseEvent e) {}

            @Override
            public void mouseEntered(MouseEvent e) {}

            @Override
            public void mouseExited(MouseEvent e) {}
        });

        add(titel);
        add(naam);
        add(aantalBezorgingen);
        add(bezorglijstInactief);
        add(bezorglijstActief);
        setVisible(true);

    }

    public void getDataRows() throws SQLException {
        DatabaseReader bezorger = new DatabaseReader();
        Connection dbc = bezorger.getConnection();

        Statement st = dbc.createStatement();
        ResultSet r = st.executeQuery("SELECT * FROM customers");

        while (r.next()) {
            int id = r.getInt("CustomerID");
            String voornaam = r.getString("CustomerName");
            String telefoonnummer = r.getString("PhoneNumber");

            System.out.format( "\n ID: %s \n Voornaam: %s \n Telefoonnummer: %s" , id, voornaam, telefoonnummer);
        }
    }

    public static void main(String[] args) throws SQLException {

        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            e.printStackTrace();
        }
        BezorgerBeheer s = new BezorgerBeheer();
//        s.getDataRows();
        
    }
}
