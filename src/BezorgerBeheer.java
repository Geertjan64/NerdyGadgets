import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class BezorgerBeheer extends JFrame implements ActionListener {

    private JLabel titel;
    private JLabel naam;
    private JLabel aantalBezorgingen;
    private BezorgerLijst bezorgerLijst = new BezorgerLijst();
    private JButton actiefZetten;
    private JButton inActiefZetten;

    public BezorgerBeheer() {
        titel = new JLabel("Beheren bezorgers");
        naam = new JLabel("Naam");
        aantalBezorgingen = new JLabel("Aantal bezorgingen");

        Bezorger peter = new Bezorger(1234, "peter", "reter");
        Bezorger peter2 = new Bezorger(1234, "peter2", "reter");
        Bezorger peter3 = new Bezorger(1234, "peter3", "reter");

        bezorgerLijst.addBezorger(peter);
        bezorgerLijst.addBezorger(peter2);
        bezorgerLijst.addBezorger(peter3);

        //bezorgerlijst = new JList((ListModel) bezorgerLijst.getBezorgers());
        //System.out.println(bezorgerLijst.getBezorgers().size());
        JList<String> bezorglijst = new JList<String>(new DefaultListModel<String>());

        for(int i = 0; i < bezorgerLijst.getBezorgers().size(); i++)
        {
            ((DefaultListModel)bezorglijst.getModel()).addElement(bezorgerLijst.getBezorgers().get(i).getVoornaam());
            //System.out.println(bezorgerLijst.getBezorgers().get(i).getVoornaam());
        }

        setTitle("Bezorgers beheren");
        setSize(1000, 600);
        setLayout(new GridLayout(1, 3));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        add(titel);
        add(naam);
        add(aantalBezorgingen);
        add(bezorglijst);
        setVisible(true);

    }

//    public void getDataRows() throws SQLException {
//        DatabaseReader bezorger = new DatabaseReader();
//        Connection dbc = bezorger.getConnection();
//        Statement st = dbc.createStatement();
//        ResultSet r = st.executeQuery("SELECT * FROM customer");
//
//        while (r.next()) {
//            String voornaam = r.getString("First_Name");
//            System.out.format("--------- \n Account number " + r.getRow() + " \n Voornaam: %s", voornaam);
//        }
//
//        bezorger.getDataRows();
//    }

    public static void main(String[] args) throws SQLException {
//          BezorgerBeheer s = new BezorgerBeheer();
////        DatabaseReader bezorger = new DatabaseReader();
//          s.getDataRows();

    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }
}
