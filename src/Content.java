import ssms.DBConnection;
import ssms.DBStatement;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

public class Content extends JFrame {
    private JPanel panel1;
    private JButton logout;
    private JLabel loggedAs;
    private JButton addCustomerButton;
    private JButton removeCustomerButton;
    private JButton detailsButton;
    private JButton refreashButton;
    private JTable customersTable;
    private JScrollPane scrollPan;


    public Content() throws SQLException {

        add(panel1);

        setTitle("SSMS - Content");

        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setUndecorated(true);
        loggedAs.setText("Logged as " + DBConnection.getUSER());

        //customersTable = new JTable() {
        //    public boolean editCellAt(int row, int column, java.util.EventObject e) {
        //        return false;
        //    }
        //};

        //customersTable.setCellSelectionEnabled(false);
        //customersTable.setEnabled(false);

        customersTable.setDefaultEditor(Object.class, null);

        scrollPan.setViewportView(customersTable);

        DefaultTableModel model = (DefaultTableModel) customersTable.getModel();

        model.addColumn("Customer ID");
        model.addColumn("Customer Name");
        model.addColumn("Customer Phone");
        model.addColumn("Customer Email");
        model.addColumn("Customer Status");

        DBStatement.putIntable(model);

        logout.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                try {
                    DBConnection.closeConnection();
                    dispose();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        });

        refreashButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                try {
                    DBStatement.refreashTable(model);
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        });
    }
}
