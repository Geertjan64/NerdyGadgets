package Default;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * @author dylan
 */

public class KeuzeMenu extends JFrame {

    private JMenuBar menubar;
    private JMenu menu;
    private JMenuItem logout;

    public KeuzeMenu() {
        /** JFrame **/
        super("Routebepaling - Keuzemenu | ");
        setSize(600, 400);
        setResizable(false);
        setMinimumSize(new Dimension(600, 400));
        JPanel panel = new JPanel(new BorderLayout());
        JPanel layout = new JPanel(new GridBagLayout());

        /** Creates grid (4 rows, 1 column, horizontal gap 20, vertical gap 5 **/
        JPanel btnPanel = new JPanel(new GridLayout(4, 1, 20, 5));

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

        /** Set to true to test Default.KeuzeMenu **/
        setVisible(false);

        /** add the menu button to the panel **/
        menubar();
    }
    /** Made by Pascal **/
    private void menubar(){
        menubar = new JMenuBar();
        menu = new JMenu("Menu");

        logout = new JMenuItem("Uitloggen");
        logout.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                /** exits the program **/
                dispose();
                new Login();
            }
        });
        /** makes the layout for the menubar **/
        menu.add(logout);
        menubar.add(menu);
        setJMenuBar(menubar);
    }
}
