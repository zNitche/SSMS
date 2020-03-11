import ssms.DBConnection;
import ssms.DBStatement;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

public class Content extends JFrame {
    private JPanel panel1;
    private JButton logout;
    private JLabel loggedAs;
    private JList customers_id;
    private JList customers_name;
    private JList customers_phone;
    private JList customers_email;
    private JList customers_status;

    public Content() throws SQLException {
        add(panel1);

        setTitle("SSMS - Content");

        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setUndecorated(true);
        loggedAs.setText("Logged as " + DBConnection.getUSER());

        customers_id.add(DBStatement.getID());
        customers_id.setVisible(true);
        customers_name.add(DBStatement.getName());
        customers_name.setVisible(true);
        customers_phone.add(DBStatement.getPhone());
        customers_phone.setVisible(true);
        customers_email.add(DBStatement.getEmail());
        customers_email.setVisible(true);
        customers_status.add(DBStatement.getStatus());
        customers_status.setVisible(true);

        //DBStatement.getCustomer();

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
    }
}
