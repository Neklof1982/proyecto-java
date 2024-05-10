package front.dialogue;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SingUpDialogue extends JDialog {

    private String userName;
    private String surname;
    private char[] password;

    private JPanel singUpPane;
    private JButton singUp_JButton;
    private JButton cancel_JButton;
    private JPasswordField password2_JPasswordField;
    private JPasswordField password1_JPasswordField;
    private JTextField userName_JTextField;
    private JTextField surname_JTextField;

    public SingUpDialogue() {
        acctions();
        singUpStyle();
    }

    private void acctions() {
        singUp_JButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                singUpAcction();
            }
        });

        cancel_JButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onCancel();
            }
        });
    }

    private void singUpStyle() {
        getRootPane().setDefaultButton(singUp_JButton);
        setContentPane(singUpPane);
        setTitle("SingUp");
        setModal(true);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setSize(300, 300);
        setMinimumSize(new Dimension(300, 300));
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int x = (screenSize.width - getWidth()) / 2;
        int y = (screenSize.height - getHeight()) / 2;
        setLocation(x, y);
        setVisible(true);
    }

    private void singUpAcction() {

        // Se comprueba si son iguales las contraseñas
        if (String.valueOf(String.valueOf(password1_JPasswordField.getPassword())).equals(String.valueOf(password2_JPasswordField.getPassword()))) {
            // Se comprueba que el nombre de usuario no es == ("" || " " || null)
            if (!(userName_JTextField.getText().equals("") || userName_JTextField.getText().equals(" ") || (userName_JTextField.getText() == null))) {
                if (!(surname_JTextField.getText().equals("") || surname_JTextField.getText().equals("") ||surname_JTextField.getText() == null)) {
                    userName = userName_JTextField.getText();
                    surname = surname_JTextField.getText();
                    password = password1_JPasswordField.getPassword();
                    dispose();
                } else {
                    JOptionPane.showMessageDialog(this, "Apellido no permitido");
                }
            } else {
                JOptionPane.showMessageDialog(this, "Nombre no permitido");
            }
        } else {
            JOptionPane.showMessageDialog(this, "Las contraseñas no coinciden");
        }
    }

    private void onCancel() {
        dispose();
    }

    public String getUserName() {
        return userName;
    }

    public String getSurname() {
        return surname;
    }

    public String getPassword() {
        return String.valueOf(password);
    }
}
