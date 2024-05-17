package front.dialogos;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class DialogoInicio extends JDialog {

    private int acction = 3;
    private String userName;
    private char[] password;

    private JPanel loginPane;
    private JButton login_JButton;
    private JButton cancel_JlButton;
    private JPasswordField password_JTextField;
    private JTextField userName_JTextField;
    private JButton SignUp_JButton;

    public DialogoInicio() {
        acctions();
        loginStyle();
    }

    private void loginStyle() {
        setContentPane(loginPane);
        setTitle("Login");
        setModal(true);
        getRootPane().setDefaultButton(login_JButton);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setSize(300, 300);
        setMinimumSize(new Dimension(300, 300));
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int x = (screenSize.width - getWidth()) / 2;
        int y = (screenSize.height - getHeight()) / 2;
        setLocation(x, y);
        setVisible(true);
    }

    // Acciones de botones, inputs ...
    private void acctions() {
        login_JButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                loginAcction();
            }
        });

        cancel_JlButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                cancelAcction();
            }
        });

        SignUp_JButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                singUpAcction();
            }
        });
    }

    private void cancelAcction() {
        acction = 3;
        dispose();
    }

    // Establezco la variable de la opción de registro
    private void singUpAcction() {
        acction = 1;
        dispose();
    }

    // Recojo los valores de los inputs
    private void loginAcction() {
        userName = userName_JTextField.getText();
        password = password_JTextField.getPassword();
        acction = 2;
        dispose();
    }

    public String getUserName() {
        return userName;
    }

    public String getPassword() {
        return String.valueOf(password);
    }

    public int getAcction() {
        return acction;
    }
}
