package Default;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class BezorgerActiviteit extends JFrame {

    public BezorgerActiviteit(int werknemer_ID) throws SQLException {

        setTitle("Bezorgeractiviteit");
        setSize(600, 500);
        setResizable(true);
        setMinimumSize(new Dimension(600, 500));

        // Temporary data
        Object[][] rowData = {{"Row1-Column1", "Row1-Column2", "Row1-Column3", "Row1-Column4", "Row1-Column5", "Row1-Column6"}};
        // Array for columnNames
        Object[] columnNames = {"City", "Zip_Code", "Country", "Street_Name", "House_Number", "Addition"};

        // Creating table
        DefaultTableModel mTableModel = new DefaultTableModel(rowData, columnNames);
        JTable table = new JTable(mTableModel);

        DatabaseReader bezorgerGegevens = new DatabaseReader();
        Connection dbc = bezorgerGegevens.getConnection();

        Statement st = dbc.createStatement();
        ResultSet rs = st.executeQuery("SELECT City, Zip_Code, Country, Street_Name, House_Number, Addition from address " +
                "WHERE Address_ID = " +
                "((SELECT Address_1 FROM customer WHERE Customer_ID IN " +
                "(SELECT Customer_ID FROM orders WHERE Deliverer_ID = " + werknemer_ID + "))) ");

        // remove the temp row previously created
        mTableModel.removeRow(0);

        Object[] rows;
        // For each row
        while (rs.next()) {
            // adding values to temporary rows
            rows = new Object[]{rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6)};
            mTableModel.addRow(rows);
        }

        JScrollPane scrollPane = new JScrollPane(table);
        add(scrollPane);

        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new FlowLayout());
        setLocationRelativeTo(null);
        setVisible(true);
    }
}
