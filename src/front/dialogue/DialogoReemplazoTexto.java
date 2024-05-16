package front.dialogue;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class DialogoReemplazoTexto extends JDialog {

    private String oldText = "";
    private String newText = "";

    private JPanel replacePane;
    private JButton buttonOK_JButton;
    private JButton buttonCancel_JButton;
    private JTextField originalText_JTextField;
    private JTextField newText_JTextField;

    public DialogoReemplazoTexto() {
        setContentPane(replacePane);
        setModal(true);
        getRootPane().setDefaultButton(buttonOK_JButton);
        setSize(200, 200);

        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int x = (screenSize.width - getWidth()) / 2;
        int y = (screenSize.height - getHeight()) / 2;
        setLocation(x, y);

        actions();
    }

    private void actions() {
        buttonOK_JButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onOK();
            }
        });

        buttonCancel_JButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onCancel();
            }
        });

        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                onCancel();
            }
        });

        replacePane.registerKeyboardAction(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onCancel();
            }
        }, KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);
    }

    public String[] getValues() {
        return new String[]{originalText_JTextField.getText(), newText_JTextField.getText()};
    };

    private void onOK() {
        newText = newText_JTextField.getText();
        oldText = originalText_JTextField.getText();
        dispose();
    }

    private void onCancel() {
        dispose();
    }

    public String getOldText() {
        return oldText;
    }

    public String getNewText() {
        return newText;
    }
}
