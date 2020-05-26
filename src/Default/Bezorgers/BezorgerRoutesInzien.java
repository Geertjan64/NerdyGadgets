package Default.Bezorgers;

import SQL.DatabaseReader;
import SQL.SQLFuncties;


import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Arrays;

public class BezorgerRoutesInzien extends JFrame {
    private SQLFuncties sql = new SQLFuncties();
    private JPanel buttonPanel;
    private JList<String> list;
    private String[] temp;
    private int id;

    public BezorgerRoutesInzien(int id) throws SQLException {
        this.id = id;
        list = new JList<>(sql.stappenRoute(id));
        setTitle("Inzien Route - "+id);
        setSize(1200, 800);
        setResizable(false);
        setMinimumSize(new Dimension(1200, 800));

        list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        list.setFixedCellHeight(50);
        list.setBorder(new EmptyBorder(10, 10, 10, 10));

        JScrollPane scrollPane1 = new JScrollPane(list);
        scrollPane1.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        add(scrollPane1);

        validate();
        repaint();

        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new FlowLayout());
        setLocationRelativeTo(null);
        setVisible(true);
    }
}
