import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

public class BezorgerBeheer extends JFrame {

    private JList<String> bezorglijstInactief;
    private JList<String> bezorglijstActief;
    private DefaultListModel modelInactief;
    private DefaultListModel modelActief;
    private BezorgerLijst bezorgerLijst = new BezorgerLijst();
    private JButton activiteit;

    public BezorgerBeheer() throws SQLException {
        //Set JFrame properties
        setTitle("Bezorgers beheren");
        setSize(1600, 900);
        setResizable(false);
        setMinimumSize(new Dimension(1600, 900));

        //Initialize the JPanels
        JPanel mainPanel = new JPanel(new GridLayout(2, 3));

        JPanel leftPanel = new JPanel();
        JLabel leftLabel = new JLabel("Inactief");

        JPanel centerPanel1 = new JPanel();
        JButton centerBtn1 = new JButton("<->");
        centerBtn1.setPreferredSize(new Dimension(50, 40));
        JLabel centerLabel1 = new JLabel("");

        JPanel centerPanel = new JPanel();
        JLabel centerLabel = new JLabel("Actief");

        JPanel rightPanel = new JPanel();
        JLabel rightLabel = new JLabel("");

        activiteit = new JButton("Activiteit bezorgers");
        activiteit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (e.getSource() == activiteit) {
                    if (!bezorglijstInactief.isSelectionEmpty()) {
                        System.out.println("I");
                        bezorglijstInactief.getSelectedIndex();
                        String value = bezorglijstInactief.getSelectedValue();
                        String iets = value.replaceAll("\\D+","");
                        System.out.println(Integer.parseInt(iets));
                    }

                    if (!bezorglijstActief.isSelectionEmpty()) {
                        System.out.println("A");
                        bezorglijstActief.getSelectedIndex();
                        System.out.println(bezorglijstActief.getSelectedValue());
                        String value = bezorglijstInactief.getSelectedValue();
                        int iets = Integer.parseInt(value.replaceAll("\\D+",""));
                        System.out.println(iets);
                    }
                }
            }
        });

        SQLFuncties f = new SQLFuncties();
        f.getDataRows();

        // Initialize the JLists and fill them with employees
        bezorglijstInactief = new JList<>(new DefaultListModel<>());
        bezorglijstActief = new JList<>(new DefaultListModel<>());

        modelInactief = (DefaultListModel) bezorglijstInactief.getModel();
        modelActief = (DefaultListModel) bezorglijstActief.getModel();

        for (int i = 0; i < bezorgerLijst.getBezorgers().size(); i++) {
            if (!bezorgerLijst.getBezorgers().get(i).getActief()) {
                modelInactief.addElement(bezorgerLijst.getBezorgers().get(i).toString());
            }
        }

        for (int i = 0; i < bezorgerLijst.getBezorgers().size(); i++) {
            if (bezorgerLijst.getBezorgers().get(i).getActief()) {
                modelActief.addElement(bezorgerLijst.getBezorgers().get(i).toString());
            }
        }

        JScrollPane scrollableListInactive = new JScrollPane(bezorglijstInactief);
        JScrollPane scrollableListActive = new JScrollPane(bezorglijstActief);

        // Set some final JFrame properties
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new FlowLayout());
        setLocationRelativeTo(null);
        setVisible(true);

        // Add components to the JFrame
        scrollableListInactive.setPreferredSize(new Dimension(250, 125));
        leftPanel.add(scrollableListInactive);

        centerPanel1.add(centerBtn1);

        scrollableListActive.setPreferredSize(new Dimension(250, 125));
        centerPanel.add(scrollableListActive);

        rightPanel.add(activiteit);

        mainPanel.add(leftLabel);
        mainPanel.add(centerLabel1);
        mainPanel.add(centerLabel);
        mainPanel.add(rightLabel);

        mainPanel.add(leftPanel);
        mainPanel.add(centerPanel1);
        mainPanel.add(centerPanel);
        mainPanel.add(rightPanel);

        add(mainPanel);

        // MouseListeners for the lists
        bezorglijstInactief.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 2 && !bezorglijstInactief.isSelectionEmpty()) {
                    String selectedItem = bezorglijstInactief.getSelectedValue();
                    for (int i = 0; i < bezorgerLijst.getBezorgers().size(); i++) {
                        if (selectedItem.substring(1, 5).equals(bezorgerLijst.getBezorgers().get(i).toString().substring(1, 5))) {
                            bezorgerLijst.getBezorgers().get(i).setActief(true);
                            SQLFuncties f = new SQLFuncties();

                            int activiteitZetten = bezorgerLijst.getBezorgers().get(i).getWerknemerID();
                            try {
                                f.updateActief(1, activiteitZetten);
                            } catch (SQLException ex) {
                                ex.printStackTrace();
                            }

                            System.out.println(bezorgerLijst.getBezorgers().get(i).getActief());
                        }
                    }
                    // Add selectedItem to the active list
                    modelInactief.removeElement(selectedItem);
                    modelActief.addElement(selectedItem);
                }
            }

            @Override
            public void mousePressed(MouseEvent e) {
            }

            @Override
            public void mouseReleased(MouseEvent e) {
            }

            @Override
            public void mouseEntered(MouseEvent e) {
            }

            @Override
            public void mouseExited(MouseEvent e) {
            }
        });

        bezorglijstActief.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 2 && !bezorglijstActief.isSelectionEmpty()) {
                    String selectedItem = bezorglijstActief.getSelectedValue();
                    for (int i = 0; i < bezorgerLijst.getBezorgers().size(); i++) {
                        if (selectedItem.substring(1, 5).equals(bezorgerLijst.getBezorgers().get(i).toString().substring(1, 5))) {
                            bezorgerLijst.getBezorgers().get(i).setActief(false);

                            SQLFuncties f = new SQLFuncties();

                            int activiteitZetten = bezorgerLijst.getBezorgers().get(i).getWerknemerID();
                            try {
                                f.updateActief(0, activiteitZetten);
                            } catch (SQLException ex) {
                                ex.printStackTrace();
                            }

                            System.out.println(bezorgerLijst.getBezorgers().get(i).getActief());
                        }
                    }
                    // Add selectedItem to the inactive list
                    modelInactief.addElement(selectedItem);
                    modelActief.removeElement(selectedItem);
                }
            }

            @Override
            public void mousePressed(MouseEvent e) {
            }

            @Override
            public void mouseReleased(MouseEvent e) {
            }

            @Override
            public void mouseEntered(MouseEvent e) {
            }

            @Override
            public void mouseExited(MouseEvent e) {
            }
        });

    }

    public static void main(String[] args) throws SQLException {

        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            e.printStackTrace();
        }

        BezorgerBeheer s = new BezorgerBeheer();

    }

}

