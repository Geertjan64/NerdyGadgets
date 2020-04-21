import javax.swing.*;
import java.awt.*;

public class KeuzeMenu extends JFrame {

    public KeuzeMenu() {

        /** JFrame **/
        super("Routebepaling - Keuzemenu");
        setSize(600, 400);

        /** Buttons **/
        JButton btn1 = new JButton("Huidige routes");
        JButton btn2 = new JButton("Route inplannen");
        JButton btn3 = new JButton("Route starten");
        JButton btn4 = new JButton("Gemaakte ritten");
        JButton btn5 = new JButton("Beheren route");
        JButton btn6 = new JButton("Beheren adressen");
        JButton btn7 = new JButton("Beheren bezorgers");

        for(int i = 0; i < 7; i++) {
            //add(btn + i);
        }

        setLayout(new FlowLayout());
        setVisible(true);
    }


    public static void main(String[] args) {
        new KeuzeMenu();

    }

}
