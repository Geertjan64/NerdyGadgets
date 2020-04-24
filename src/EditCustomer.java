import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

class EditCustomer
{
    // Define database variables
    private static Connection con = null;
    private static final String URL = "jdbc:mysql://localhost:3306/wideworldimporters";
    private static final String driver = "com.mysql.jdbc.Driver";
    private static final String user = "root";
    private static final String pass = "";
    private Object JTable;


    /**
     *
     * @throws SQLException
     */
    public EditCustomer() throws SQLException
    {

        Statement stmt;
        String query;
        ResultSet rs;

        // Temporary data
        Object[][] rowData = {{"Row1-Column1", "Row1-Column2", "Row1-Column3", "Row1-Column4"}};
        // Array for columnNames
        Object[] columnNames = {"Klant naam", " Adres", "Postcode", "Stad", ""};

        // Creating table
        DefaultTableModel mTableModel = new DefaultTableModel(rowData, columnNames);
        JTable table = new JTable(mTableModel);

        // try & connect DB
        try {
            Class.forName(driver).newInstance();
            con = DriverManager.getConnection(URL, user, pass);
        } catch (Exception e) {
            System.err.println("Exception: " + e.getMessage());
        }

        // Query from customers and cities
        query = "SELECT CustomerName, DeliveryAddressLine2, DeliveryPostalCode, CityName FROM Customers join cities on DeliveryCityID = CityID";
        stmt = con.createStatement();
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
            rows = new Object[]{rs.getString(1), rs.getString(2), rs.getString(3),rs.getString(4)};
            mTableModel.addRow(rows);
        }
    }
    }

