import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * @author dylan
 */

public class KeuzeMenu extends JFrame {

    public KeuzeMenu() {

        /** JFrame **/
        super("Routebepaling - Keuzemenu");
        setSize(600, 400);
        setResizable(false);
        setMinimumSize(new Dimension(600, 400));
        JPanel panel = new JPanel(new BorderLayout());
        JPanel layout = new JPanel(new GridBagLayout());

        /** Buttons **/
        JButton btn1 = new JButton("Huidige routes");
        JButton btn2 = new JButton("Route inplannen");
        JButton btn3 = new JButton("Route starten");
        JButton btn4 = new JButton("Gemaakte ritten");
        JButton btn5 = new JButton("Beheren route");
        JButton btn6 = new JButton("Beheren adressen");
        JButton btn7 = new JButton("Beheren bezorgers");

        /** Set prefered button size **/
        btn1.setPreferredSize(new Dimension(100, 50));

        /** Creates grid (4 rows, 1 column, horizontal gap 20, vertical gap 5 **/
        JPanel btnPanel = new JPanel(new GridLayout(4, 1, 20, 5));

        /** Adds created buttons to btnPanel (Grid) **/
        btnPanel.add(btn1);
        btnPanel.add(btn2);
        btnPanel.add(btn3);
        btnPanel.add(btn4);
        btnPanel.add(btn5);
        btnPanel.add(btn6);
        btnPanel.add(btn7);

        /** Adds the JPanel with gridlayout to layout JPanel **/
        layout.add(btnPanel);

        /** Adds layouts Jpanel, to panel JPanel, centering them **/
        panel.add(layout, BorderLayout.CENTER);

        /** Adds JPanel panel to the JFrame **/
        add(panel);

        /** Centering screen (setLocationRelativeTo null) **/
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new FlowLayout());
        setLocationRelativeTo(null);
        setVisible(true);

        /** Action listeners **/
        btn1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("HELLO");
            }
        });

        btn2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("Hello2");
            }
        });

        btn7.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new BezorgerBeheer();
            }
        });

    }


    public static void main(String[] args) {
        new KeuzeMenu();
    }

}
