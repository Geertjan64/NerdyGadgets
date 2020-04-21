import javax.swing.*;
import java.awt.*;

public class Bezorgers extends JFrame {

    private JLabel titel;
    private JLabel naam;
    private JLabel aantalBezorgingen;
    private JList bezorgerlijst;

    public Bezorgers() {
        titel = new JLabel("Beheren bezorgers");
        naam = new JLabel("Naam");
        aantalBezorgingen = new JLabel("Aantal bezorgingen");
        bezorgerlijst = new JList();

        setTitle("Bezorgers beheren");
        setSize(1000, 600);
        setLayout(new GridLayout(1, 3));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        add(titel);
        add(naam);
        add(aantalBezorgingen);

        setVisible(true);

    }
}
