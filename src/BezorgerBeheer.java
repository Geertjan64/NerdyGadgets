import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

public class BezorgerBeheer extends JFrame implements ActionListener {

    private JLabel titel;
    private JLabel naam;
    private JLabel aantalBezorgingen;
    private JList bezorgerlijst;
    private BezorgerLijst bezorgerLijst = new BezorgerLijst();
    private JButton actiefZetten;
    private JButton inActiefZetten;

    public BezorgerBeheer() {
        titel = new JLabel("Beheren bezorgers");
        naam = new JLabel("Naam");
        aantalBezorgingen = new JLabel("Aantal bezorgingen");

        Bezorger peter = new Bezorger(1234, "peter", "reter");
        bezorgerLijst.addBezorger(peter);

        bezorgerlijst = new JList((ListModel) bezorgerLijst.getBezorgers());

        setTitle("Bezorgers beheren");
        setSize(1000, 600);
        setLayout(new GridLayout(1, 3));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        add(titel);
        add(naam);
        add(aantalBezorgingen);
        add(bezorgerlijst);

        setVisible(true);

    }

    public static void main(String[] args) throws SQLException {

        BezorgerBeheer test = new BezorgerBeheer();
//        DatabaseReader dbr = new DatabaseReader();
//        dbr.getDataRows();

    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }
}
