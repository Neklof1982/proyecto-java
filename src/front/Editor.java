package front;

import back.Nota;
import back.Usuario;
import back.UsuarioDAO;
import com.google.gson.Gson;
import com.google.gson.stream.JsonReader;
import front.dialogue.DialogoInicio;
import front.dialogue.DialogoReemplazoTexto;
import front.dialogue.DialogoRegistro;
import front.renders.NoteListRender;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.TreeSet;

public class Editor extends JFrame {

    // #### ATRIBUTOS ##################

    // Valores

    Usuario usuario;
    TreeSet<Nota> notas = new TreeSet<Nota>();
    Nota selectedNote;

    // Objetos interfaz

    private JList noteList_JList;
    private JTextArea noteEditor_JTextArea;
    private JPanel editorPane;
    private JButton saveNotes_Jbutton;
    private JButton newNote_Jbutton;
    private JButton deleteNote_Jbutton;
    private JButton renameNote_Jbutton;
    private JButton replaceAll_Jbutton;
    private JButton openFromFile_Jbutton;
    private JButton saveAsTxt_Jbutton;
    private JButton increaseFontSize_Jbutton;
    private JButton decreaseFontSize_Jbutton;

    // #### CONSTRUCTOR ###############

    public Editor() {
        editorStyle();
        loadUser();

        updateNoteList();
        selectFirstNote();
        acctions();
    }

    private String fechaActual() {
        Date now = new Date();
        SimpleDateFormat  dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return dateFormat.format(now);
    }

    private void selectFirstNote() {

        // Si no tiene notas le creo una
        if (notas.size() == 0) {
            notas.add(new Nota(1,"Tu primera nota","Esta es tu primera nota", fechaActual(),20));
            updateNoteList();
        }

        // Seleciono la primera nota
        selectedNote = notas.first();
        noteEditor_JTextArea.setText(selectedNote.getContenido());
        noteList_JList.setSelectedValue(selectedNote,true);

        Font fuenteActual = noteEditor_JTextArea.getFont();
        Font nuevaFuente = fuenteActual.deriveFont(Font.PLAIN, selectedNote.getTamanoTexto());
        noteEditor_JTextArea.setFont(nuevaFuente);
    }

    // ##### METODOS ##################

    private void loadUser()   {

        File datosUsuariosR = new File("datosUsuario.json");

        // Leo los datos guardados en el equipo (si estan)
        if (datosUsuariosR.exists()) {
            try {
                Gson gson = new Gson();

                FileReader lector = new FileReader("datosUsuario.json");
                JsonReader lectorJson = new JsonReader(lector);

                usuario = gson.fromJson(lectorJson, Usuario.class);

                usuario = UsuarioDAO.obtenerUsuario(usuario.getNombre(), usuario.getContraseña());

                notas = usuario.getListaNotas();

                lectorJson.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            // Si no hay datos de usuario en el equipo se accede a la base de datos
            // Se abre la ventana de login
            DialogoInicio login = new DialogoInicio();

            switch (login.getAcction()) {
                case 1: { // SingUp
                    // Se abre la ventana de registro
                    DialogoRegistro singUp = new DialogoRegistro();

                    // Se recogen los datos de registro
                    String nombre = singUp.getUserName();
                    String apellido = singUp.getSurname();
                    String contrasena = singUp.getPassword();

                    // Se solicita el regsitro a la base de datos
                    if (UsuarioDAO.crearUsuario(nombre, apellido, contrasena)) {
                        JOptionPane.showMessageDialog(this, "Usuario Registrado");
                    } else {
                        JOptionPane.showMessageDialog(this, "Error al registrar el usuario");
                    }

                    // En cualquier caso se le manda a la ventana de login para que intente crearse otra cuenta o se
                    // inicie con la cuenta ya creada
                    loadUser();
                }
                case 2: { // Login
                    // Si se acepta el login...
                    usuario = UsuarioDAO.obtenerUsuario(login.getUserName(), login.getPassword());
                    if (usuario == null) {
                        // Si el usuaio no se encuentra en la base de datos se muestra un mensaje
                        JOptionPane.showMessageDialog(this, "Usuario no encontrado");
                        dispose();
                    } else {
                        // Si se encuentra se guarda la lista de notas
                        notas = usuario.getListaNotas();
                        // Se guarda el usuario en el equipo

                        try {
                            Gson gson = new Gson();
                            BufferedWriter datos = new BufferedWriter(new FileWriter("datosUsuario.json"));

                            String json = gson.toJson(usuario);
                            datos.write(json);

                            datos.close();
                        } catch (IOException e) {
                            System.err.println("error");
                        }
                    }
                    break;
                }
                case 3: { // Cancel
                    // si se cancela el login...
                    dispose();
                    break;
                }
                default: {
                    dispose();
                }
            }
        }
    }

    private void newNote() {
        int lastId = notas.last().getId();
        notas.add(new Nota(lastId +  1, JOptionPane.showInputDialog("Nombre de la nota"), " ", fechaActual(), 20));

        selectedNote = notas.last();
        noteEditor_JTextArea.setText(selectedNote.getContenido());

        updateNoteList();
    }

    private void changeNote() {
        selectedNote = (Nota) noteList_JList.getSelectedValue();
        noteEditor_JTextArea.setText(selectedNote.getContenido());

        Font fuenteActual = noteEditor_JTextArea.getFont();
        Font nuevaFuente = fuenteActual.deriveFont(Font.PLAIN, selectedNote.getTamanoTexto());
        noteEditor_JTextArea.setFont(nuevaFuente);
    }

    private void updateNoteList() {
        DefaultListModel<Nota> modeloNotas = new DefaultListModel<Nota>();

        Iterator<Nota> itNotas = notas.iterator();

        while (itNotas.hasNext()) {
            Nota nextNota = itNotas.next();
            modeloNotas.addElement(nextNota);
        }

        noteList_JList.setModel(modeloNotas);

        noteList_JList.setCellRenderer(new NoteListRender());
    }

    // Aumentar tamaño fuente
    private void increaseFontSize() {
        Font fuenteActual = noteEditor_JTextArea.getFont();
        Font nuevaFuente = fuenteActual.deriveFont(Font.PLAIN, fuenteActual.getSize() + 2);
        noteEditor_JTextArea.setFont(nuevaFuente);
        selectedNote.setTamanoTexto(fuenteActual.getSize());
    }

    // Disminuir tamaño fuente
    private void decreaseFontSize() {
        Font fuenteActual = noteEditor_JTextArea.getFont();
        Font nuevaFuente = fuenteActual.deriveFont(Font.PLAIN, fuenteActual.getSize() - 2);
        noteEditor_JTextArea.setFont(nuevaFuente);
        selectedNote.setTamanoTexto(fuenteActual.getSize());
    }

    private void replaceAll() {
        DialogoReemplazoTexto remplaceDialogue = new DialogoReemplazoTexto();
        remplaceDialogue.setVisible(true);
        noteEditor_JTextArea.setText(noteEditor_JTextArea.getText().replaceAll(remplaceDialogue.getOldText(), remplaceDialogue.getNewText()));
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

    // Guardo la nota editada y la mando al servidor
    private void saveNotes() {
        selectedNote.setContenido(noteEditor_JTextArea.getText());
        UsuarioDAO.actualizarNotas(usuario);
    }

    // Elimino la nota y la mando al servidor
    private void deleteNote() {
        if (JOptionPane.showConfirmDialog(this, "¿Estas seguro de eliminar esta nota?", "Eliminar Nota", JOptionPane.YES_NO_CANCEL_OPTION) == 0) {
            notas.remove(selectedNote);
            selectFirstNote();
            UsuarioDAO.actualizarNotas(usuario);
            updateNoteList();
        }
    }

    private void editorStyle() {
        // Establecer panel
        setContentPane(editorPane);

        // Nombre de la ventana
        setTitle("Editor");

        // Centrado y tamaño de la ventana
        setSize(500,500);
        setMinimumSize(new Dimension(300, 300));
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int x = (screenSize.width - getWidth()) / 2;
        int y = (screenSize.height - getHeight()) / 2;
        setLocation(x, y);

        // Margenes de la lista y textArea
        noteList_JList.setBorder(new EmptyBorder(10,10, 10, 10));
        noteEditor_JTextArea.setBorder(BorderFactory.createCompoundBorder(noteEditor_JTextArea.getBorder(), BorderFactory.createEmptyBorder(10, 10, 10, 10)));

        // Comportamiento de la ventana
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

    // Establecer acciones: botones, textArea, lista
    private void acctions() {
        // crear notas
        newNote_Jbutton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                newNote();
            }
        });

        saveNotes_Jbutton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                saveNotes();
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

        // Boton eliminar nota selecionada
        deleteNote_Jbutton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                deleteNote();
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
}
