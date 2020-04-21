import javax.swing.*;
import java.awt.*;

public class Bezorgers extends JFrame {

    private JLabel titel;
    private JList bezorgerlijst;

    public Bezorgers() {
        titel = new JLabel("Beheren bezorgers");
        bezorgerlijst = new JList();

        setTitle("Bezorgers beheren");
        setSize(1000, 600);
        setLayout(new GridLayout(4, 3));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        add(titel);

        setVisible(true);

    }
}
