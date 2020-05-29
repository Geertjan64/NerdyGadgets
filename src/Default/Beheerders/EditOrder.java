package Default.Beheerders;

import SQL.DatabaseConnector;

import java.awt.BorderLayout;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import SQL.SQLFuncties;
import java.awt.event.*;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.TableModel;

public class EditOrder{

    private DatabaseConnector db = new DatabaseConnector();
    private Connection dbc = db.getConnection();
    private SQLFuncties sql = new SQLFuncties();

    public EditOrder() throws SQLException {


        Statement stmt;
        String query;
        ResultSet rs;


        // Temporary data
        Object[][] rowData = {{"Row1-Column1", "Row1-Column2", "Row1-Column3", "Row1-Column4", "Row1-Column5"}};
        // Array for columnNames
        Object[] columnNames = {"Order_ID", "Voornaam", "Achternaam", "Productnaam", "Volledige voorraad"};

        // Creating table
        DefaultTableModel mTableModel = new DefaultTableModel(rowData, columnNames);
        JTable table = new JTable(mTableModel);

        // Query from customers and cities
        query = "SELECT O.Order_ID, customer.First_Name, customer.Last_name, product.Product_Name, Quantity FROM orders join orderline as O on O.Order_ID = orders.Order_ID join product on O.ID_Product = product.ID_Product join customer on orders.Customer_ID = customer.Customer_ID";
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
            rows = new Object[]{rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5)};
            mTableModel.addRow(rows);
        }



        table.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    int row = table.getSelectedRow();
                    int column = table.getSelectedColumn();
                    String resul = table.getValueAt(row, column).toString();
                    String id = table.getValueAt(row, 0).toString();
                }
            }
        });

        table.getModel().addTableModelListener(new TableModelListener() {
            @Override
            public void tableChanged(TableModelEvent e) {
                int row = e.getFirstRow();
                int column = e.getColumn();
                if (column == 0) {
                    TableModel model = (TableModel) e.getSource();
                    Object data = model.getValueAt(row, column);
                    int id = row + 1;
                    try {
                        sql.veranderenOrdergegevens(id, column, data);
                    } catch (SQLException ex) {
                        ex.printStackTrace();
                    }
                } else {
                    JOptionPane.showMessageDialog(frame, "Deze kolom mag niet worden aangepast.");
                }
            }
        });
    }
}


