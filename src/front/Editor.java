package front;

import back.Nota;
import back.Usuario;
import front.dialogue.RemplaceDialogue;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashSet;

public class Editor extends JDialog {

    // #### ATRIBUTOS #################

    // Valores
    private HashSet notas;

    // Interfaz
    private JPanel contentPane;
    private JTextArea noteEditor_JTextArea;
    private JList noteList_JList;
    private JButton pullNotes_Jbutton;
    private JButton pushNotes_Jbutton;
    private JButton newNote_Jbutton;
    private JButton deleteNote_Jbutton;
    private JButton replaceAll_Jbutton;
    private JButton openFromFile_Jbutton;
    private JButton saveAsTxt_Jbutton;
    private JButton increaseFontSize_Jbutton;
    private JButton decreaseFontSize_Jbutton;
    private JButton rename_Jbutton;

    // #### MAIN ######################

    public static void main(String[] args) {
        Usuario usuario = new Usuario("12345", "Álvaro");
        LinkedHashSet<Nota> notas = new LinkedHashSet<Nota>();

        for (int i = 0; i < 100; i++) {
            notas.add(new Nota(i, "Mi nota " + i, "Esta es la nota " + i));
        }

        usuario.setListaNotas(notas);

        Editor editor = new Editor(usuario);
        editor.setVisible(true);
        System.exit(0);
    }

    // #### CONSTRUCTOR ###############

    public Editor(Usuario usuario) {
        // Establecer notas
        this.notas = usuario.getListaNotas();

        // Actualizo las notas y
        editorStyle();
        updateNoteList();
        actions();
    }

    // #### METODOS ###################

    // Modificaciones estilo del editor
    private void editorStyle() {
        setContentPane(contentPane);
        setTitle("Editor");
        setModal(true);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setSize(1000, 700);
        setMinimumSize(new Dimension(700, 400));
        noteList_JList.setBorder(new EmptyBorder(10,10, 10, 10));
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int x = (screenSize.width - getWidth()) / 2;
        int y = (screenSize.height - getHeight()) / 2;
        setLocation(x, y);
    }

    // Establecer acciones: botones, textArea, lista
    private void actions() {

        // Traer notas al front
        pullNotes_Jbutton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onPull();
            }
        });

        // Llevar notas al servidor
        pushNotes_Jbutton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onPush();
            }
        });

        // crear notas
        newNote_Jbutton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                newNote();
            }
        });

        // Eliminar nota
        deleteNote_Jbutton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                deleteNote();
            }
        });

        // Descargar nota en el equipo
        saveAsTxt_Jbutton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                saveAsTxt();
            }
        });

        // Reemplazar letras / palabras
        replaceAll_Jbutton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                replaceAll();
            }
        });

        // Aumentar tamño fuente
        increaseFontSize_Jbutton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                increaseFontSize();
            }
        });

        // Disminuir tamaño de fuente
        decreaseFontSize_Jbutton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                decreaseFontSize();
            }
        });

        // Cambio de nota al selecionar dicha en la lista
        noteList_JList.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                if (!e.getValueIsAdjusting()) {
                    changeNote();
                }
            }
        });
    }

    // Aumentar tamaño fuente
    private void increaseFontSize() {
        Font fuenteActual = noteEditor_JTextArea.getFont();
        Font nuevaFuente = fuenteActual.deriveFont(Font.PLAIN, fuenteActual.getSize() + 2);
        noteEditor_JTextArea.setFont(nuevaFuente);
    }

    // Disminuir tamaño fuente
    private void decreaseFontSize() {
        Font fuenteActual = noteEditor_JTextArea.getFont();
        Font nuevaFuente = fuenteActual.deriveFont(Font.PLAIN, fuenteActual.getSize() - 2);
        noteEditor_JTextArea.setFont(nuevaFuente);
    }

    // Guardar archivo como txt
    private void saveAsTxt() {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setDialogTitle("Seleccionar ubicación para guardar");
        fileChooser.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);

        int userSelection = fileChooser.showSaveDialog(this);

        if (userSelection == JFileChooser.APPROVE_OPTION) {
            File fileToSave = fileChooser.getSelectedFile();

            if (!fileToSave.getName().endsWith(".txt")) {
                fileToSave = new File(fileToSave.getAbsolutePath() + ".txt");
            }

            try {
                BufferedWriter writer = new BufferedWriter(new FileWriter(fileToSave));
                writer.write(noteEditor_JTextArea.getText());
                JOptionPane.showMessageDialog(this, "Archivo guardado con éxito.", "Éxito", JOptionPane.INFORMATION_MESSAGE);
            } catch (IOException ex) {
                JOptionPane.showMessageDialog(this, "Error al guardar el archivo.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private void changeNote() {
        Nota selectedNote = (Nota) noteList_JList.getSelectedValue();
        noteEditor_JTextArea.setText(selectedNote.getNota());
    }

   private void replaceAll() {
        RemplaceDialogue remplaceDialogue = new RemplaceDialogue();
        remplaceDialogue.setVisible(true);
        String[] values = remplaceDialogue.getValues();
        noteEditor_JTextArea.setText(noteEditor_JTextArea.getText().replaceAll(values[0], values[1]));
    }

    private void updateNoteList() {
        DefaultListModel<Nota> modeloNotas = new DefaultListModel<Nota>();
        modeloNotas.removeAllElements();

        System.out.println(notas);

        Iterator<Nota> itNotas = notas.iterator();

        while (itNotas.hasNext()) {
            Nota nextNota = itNotas.next();
            modeloNotas.addElement(nextNota);
            System.out.println(nextNota);
        }

        noteList_JList.setModel(modeloNotas);

        noteList_JList.setCellRenderer(new noteListRender());
    }

    private void deleteNote() {
        if (JOptionPane.showConfirmDialog(this, "¿Estas seguro de eliminar esta nota?", "Eliminar Nota", JOptionPane.YES_NO_CANCEL_OPTION) == 0) {
            System.out.println("Hola");
        }
    }

    private void newNote() {

    }

    private void onPull() {

    }

    private void onPush() {

    }

    private static class noteListRender extends JLabel implements ListCellRenderer<Nota> {
        @Override
        public Component getListCellRendererComponent(JList<? extends Nota> list, Nota value, int index, boolean isSelected, boolean cellHasFocus) {

            setText(value.getNombre());

            if (isSelected) {
                setBackground(list.getSelectionBackground());
                setForeground(list.getSelectionForeground());
            } else {
                setBackground(list.getBackground());
                setForeground(list.getForeground());
            }
            setOpaque(true);
            return this;
        }
    }
}
