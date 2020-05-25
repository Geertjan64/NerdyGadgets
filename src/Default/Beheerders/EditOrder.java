package Default.Beheerders;

import SQL.DatabaseReader;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

public class EditOrder {

    private DatabaseReader db = new DatabaseReader();
    private Connection dbc = db.getConnection();

    public EditOrder() throws SQLException {

        Statement stmt;
        String query;
        ResultSet rs;

        // Temporary data
        Object[][] rowData = {{"Row1-Column1", "Row1-Column2", "Row1-Column3"}};
        // Array for columnNames
        Object[] columnNames = {"Order ID", "Bezorger naam", "Klant naam"};

        // Creating table
        DefaultTableModel mTableModel = new DefaultTableModel(rowData, columnNames);
        JTable table = new JTable(mTableModel);

        // Query from customers and cities
        query = "SELECT Order_ID, Customer_ID, Deliverer_ID FROM orders;";
        stmt = dbc.createStatement();
        // Execute query and return results
        rs = stmt.executeQuery(query);

        // Creating JFrame
        JFrame frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        JScrollPane scrollPane = new JScrollPane(table);
        frame.add(scrollPane, BorderLayout.CENTER);
        frame.setSize(600, 500);
        frame.setVisible(true);

        // remove the temp row previously created
        mTableModel.removeRow(0);

        Object[] rows;
        // For each row
        while (rs.next()) {
            // adding values to temporary rows
            rows = new Object[]{rs.getString(1), rs.getString(2), rs.getString(3)};
            mTableModel.addRow(rows);
        }
    }
}

