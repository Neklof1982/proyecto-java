package front;

import javax.swing.*;

public class Editor extends JDialog {
    private JPanel contentPane;
    private JButton push;
    private JButton button3;
    private JButton button4;
    private JTextArea textArea1;
    private JList list1;
    private JButton pull;
    private JButton buttonOK;

    public Editor() {
        setContentPane(contentPane);
        setModal(true);
    }

    public static void main(String[] args) {
        Editor dialog = new Editor();
        dialog.pack();
        dialog.setVisible(true);
        System.exit(0);


    }
}
