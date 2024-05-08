package front;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Login extends JDialog {

    private JPanel contentPane;

    private JButton LoginButton;
    private JButton cancelButton;
    private JPasswordField passwordField;
    private JTextField userField;

    public Login() {
        setContentPane(contentPane);
        setModal(true);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setSize(1000, 700);

        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int x = (screenSize.width - getWidth()) / 2;
        int y = (screenSize.height - getHeight()) / 2;
        setLocation(x, y);

        LoginButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                loginAcction();
            }
        });

        cancelButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

            }
        });
    }

    private void loginAcction() {

    }
}
