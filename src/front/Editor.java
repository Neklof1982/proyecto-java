package front;

import back.Nota;
import back.Usuario;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.TreeSet;

public class Editor extends JDialog {

    Usuario usu = new Usuario("Álvaro", "e23423");
    TreeSet<Nota> notas = new TreeSet<Nota>();


    private JPanel contentPane;

    private JButton pullNotes;
    private JButton pushNotes;

    private JButton newNote;
    private JButton deleteNote;

    private JTextArea textArea1;
    private JList list1;

    public Editor() {

        usu.setListaNotas(notas);
        
        setContentPane(contentPane);
        setModal(true);

        pullNotes.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onPull();
            }
        });

        pushNotes.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onPush();
            }
        });

        newNote.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                newNote();
            }
        });

        deleteNote.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                deleteNote();
            }
        });
    }

    public static void main(String[] args) {
        Editor editor = new Editor();
        editor.pack();
        editor.setVisible(true);
        System.exit(0);
    }

    private void deleteNote() {
        if (JOptionPane.showConfirmDialog(this, "¿Óscar es tonto?", "Eliminar Nota", JOptionPane.YES_NO_CANCEL_OPTION) == 0) {
            System.out.println("Hola");
        }
    }

    private void newNote() {

    }

    private void onPull() {

    }

    private void onPush() {

    }
}
