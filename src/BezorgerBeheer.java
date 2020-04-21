import javax.swing.*;
import java.awt.*;

public class BezorgerBeheer extends JFrame{

    private JLabel titel;
    private JLabel naam;
    private JLabel aantalBezorgingen;
    private JList bezorgerlijst;
    private Bezorgers bezorgers = new Bezorgers();

    public BezorgerBeheer() {
        titel = new JLabel("Beheren bezorgers");
        naam = new JLabel("Naam");
        aantalBezorgingen = new JLabel("Aantal bezorgingen");
        bezorgerlijst = new JList(bezorgers.getBezorgers());

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

    public static void main(String[] args) {

        BezorgerBeheer test = new BezorgerBeheer();

    }

}
