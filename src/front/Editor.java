package front;

import back.Nota;
import back.Usuario;
import back.UsuarioDAO;
import com.google.gson.Gson;
import com.google.gson.stream.JsonReader;
import conector.Conector;
import front.dialogos.DialogoInicio;
import front.dialogos.DialogoReemplazoTexto;
import front.dialogos.DialogoRegistro;
import front.renders.RenderizadorLista;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.TreeSet;

import javax.swing.*;
import javax.swing.border.MatteBorder;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

public class Editor extends JFrame {

    // #### ATRIBUTOS ###################

    // Valores

    Usuario usuario;
    TreeSet<Nota> notas = new TreeSet<>();
    Nota selectedNote;

    // TOLLBAR
    JToolBar toolBar = new JToolBar();
    Component separador1 = Box.createHorizontalGlue();
    JButton guardar_JButton = new JButton("");
    JButton renombrar_JButton = new JButton("");
    JButton eliminar_JButton = new JButton("");
    JButton nuevaNota_JButton = new JButton("");
    JButton guardarComo_JButton = new JButton("");
    JButton abrirDesde_JButton = new JButton("");
    Component separador2 = Box.createHorizontalGlue();
    JButton reemplazar_JButton = new JButton("");
    JButton aumantarFuente_JButton = new JButton("");
    JButton disminuirFuente_JButton = new JButton("");
    Component separador3 = Box.createHorizontalGlue();

    // PANEL LISTA / EDITOR
    JSplitPane splitPane = new JSplitPane();
    JList listaNotas_JList = new JList();
    JTextArea textArea = new JTextArea();

    // PANEL INFORMACIÓN
    JPanel infoPane = new JPanel();
    Label infoLabel = new Label("New label  ");

    // #### CONSTRUCTOR #################

    public Editor() {

        // ESTILOS VENTANA
        setTitle("Editor");
        setBounds(100, 100, 700, 391);
        setMinimumSize(new Dimension(800, 500));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // TOOLBAR
        toolBar.setBorder(null);
        toolBar.setBackground(new Color(0, 128, 192));
        toolBar.setFloatable(false);
        getContentPane().add(toolBar, BorderLayout.NORTH);

        // Separador 1
        separador1.setPreferredSize(new Dimension(40, 40));
        separador1.setMinimumSize(new Dimension(40, 40));
        separador1.setMaximumSize(new Dimension(40, 0));
        separador1.setFocusable(false);
        toolBar.add(separador1);

        // Boton guardar
        guardar_JButton.setBorder(null);
        guardar_JButton.setBackground(new Color(0, 128, 192));
        guardar_JButton.setPreferredSize(new Dimension(50, 50));
        guardar_JButton.setMinimumSize(new Dimension(50, 50));
        guardar_JButton.setMaximumSize(new Dimension(50, 50));
        guardar_JButton.setFocusable(false);
        ImageIcon icon = new ImageIcon(getClass().getResource("/front/iconos/guardar.png"));
        guardar_JButton.setIcon(icon);
        guardar_JButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                guardarNotas();
            }
        });
        toolBar.add(guardar_JButton);

        // Boton renombrar
        renombrar_JButton.setBorder(null);
        renombrar_JButton.setBackground(new Color(0, 128, 192));
        renombrar_JButton.setPreferredSize(new Dimension(50, 50));
        renombrar_JButton.setMinimumSize(new Dimension(50, 50));
        renombrar_JButton.setMaximumSize(new Dimension(50, 50));
        renombrar_JButton.setFocusable(false);
        icon = new ImageIcon(getClass().getResource("/front/iconos/renombrar.png"));
        renombrar_JButton.setIcon(icon);
        renombrar_JButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                renombrarNota();
            }
        });
        toolBar.add(renombrar_JButton);

        // Boton eliminar
        eliminar_JButton.setBorder(null);
        eliminar_JButton.setBackground(new Color(0, 128, 192));
        eliminar_JButton.setPreferredSize(new Dimension(50, 50));
        eliminar_JButton.setMinimumSize(new Dimension(50, 50));
        eliminar_JButton.setMaximumSize(new Dimension(50, 50));
        eliminar_JButton.setFocusable(false);
        icon = new ImageIcon(getClass().getResource("/front/iconos/eliminar.png"));
        eliminar_JButton.setIcon(icon);
        eliminar_JButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                deleteNote();
            }
        });
        toolBar.add(eliminar_JButton);

        // Boton nueva nota
        nuevaNota_JButton.setBorder(null);
        nuevaNota_JButton.setBackground(new Color(0, 128, 192));
        nuevaNota_JButton.setPreferredSize(new Dimension(50, 50));
        nuevaNota_JButton.setMinimumSize(new Dimension(50, 50));
        nuevaNota_JButton.setMaximumSize(new Dimension(50, 50));
        nuevaNota_JButton.setFocusable(false);
        icon = new ImageIcon(getClass().getResource("/front/iconos/nueva.png"));
        nuevaNota_JButton.setIcon(icon);
        nuevaNota_JButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                nuevaNota();
            }
        });
        toolBar.add(nuevaNota_JButton);

        // Boton guardar como
        guardarComo_JButton.setBorder(null);
        guardarComo_JButton.setBackground(new Color(0, 128, 192));
        guardarComo_JButton.setPreferredSize(new Dimension(50, 50));
        guardarComo_JButton.setMinimumSize(new Dimension(50, 50));
        guardarComo_JButton.setMaximumSize(new Dimension(50, 50));
        guardarComo_JButton.setFocusable(false);
        icon = new ImageIcon(getClass().getResource("/front/iconos/guardarComo.png"));
        guardarComo_JButton.setIcon(icon);
        guardarComo_JButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                guardarComo();
            }
        });
        toolBar.add(guardarComo_JButton);

        // Boton abrir desde
        abrirDesde_JButton.setBorder(null);
        abrirDesde_JButton.setBackground(new Color(0, 128, 192));
        abrirDesde_JButton.setPreferredSize(new Dimension(50, 50));
        abrirDesde_JButton.setMinimumSize(new Dimension(50, 50));
        abrirDesde_JButton.setMaximumSize(new Dimension(50, 50));
        abrirDesde_JButton.setFocusable(false);
        icon = new ImageIcon(getClass().getResource("/front/iconos/abrirDesde.png"));
        abrirDesde_JButton.setIcon(icon);
        toolBar.add(abrirDesde_JButton);

        // Separador 2
        toolBar.add(separador2);

        // Boton reemplazar
        reemplazar_JButton.setBorder(null);
        reemplazar_JButton.setBackground(new Color(0, 128, 192));
        reemplazar_JButton.setPreferredSize(new Dimension(50, 50));
        reemplazar_JButton.setMinimumSize(new Dimension(50, 50));
        reemplazar_JButton.setMaximumSize(new Dimension(50, 50));
        reemplazar_JButton.setFocusable(false);
        icon = new ImageIcon(getClass().getResource("/front/iconos/reemplazar.png"));
        reemplazar_JButton.setIcon(icon);
        toolBar.add(reemplazar_JButton);

        // Boton aumantar fuente
        aumantarFuente_JButton.setBorder(null);
        aumantarFuente_JButton.setBackground(new Color(0, 128, 192));
        aumantarFuente_JButton.setPreferredSize(new Dimension(50, 50));
        aumantarFuente_JButton.setMinimumSize(new Dimension(50, 50));
        aumantarFuente_JButton.setMaximumSize(new Dimension(50, 50));
        aumantarFuente_JButton.setFocusable(false);
        icon = new ImageIcon(getClass().getResource("/front/iconos/aumentarFuente.png"));
        aumantarFuente_JButton.setIcon(icon);
        aumantarFuente_JButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                aumantarFuente();
            }
        });
        toolBar.add(aumantarFuente_JButton);

        // Boton disminuir fuente
        disminuirFuente_JButton.setBorder(null);
        disminuirFuente_JButton.setBackground(new Color(0, 128, 192));
        disminuirFuente_JButton.setPreferredSize(new Dimension(50, 50));
        disminuirFuente_JButton.setMinimumSize(new Dimension(50, 50));
        disminuirFuente_JButton.setMaximumSize(new Dimension(50, 50));
        disminuirFuente_JButton.setFocusable(false);
        icon = new ImageIcon(getClass().getResource("/front/iconos/disminuirFuente.png"));
        disminuirFuente_JButton.setIcon(icon);
        disminuirFuente_JButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                disminurFuente();
            }
        });
        toolBar.add(disminuirFuente_JButton);

        // separador 3
        separador3.setPreferredSize(new Dimension(40, 40));
        separador3.setMinimumSize(new Dimension(40, 40));
        separador3.setMaximumSize(new Dimension(40, 0));
        separador3.setFocusable(false);
        toolBar.add(separador3);

        // Panel dividido
        splitPane.setDividerSize(0);
        getContentPane().add(splitPane, BorderLayout.CENTER);

        // Lista de notas
        listaNotas_JList.setPreferredSize(new Dimension(150, 0));
        listaNotas_JList.setBackground(new Color(0, 128, 192));
        listaNotas_JList.setBorder(null);
        listaNotas_JList.setMinimumSize(new Dimension(100, 0));
        listaNotas_JList.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                changeNote();
            }
        });
        splitPane.setLeftComponent(listaNotas_JList);

        // Editor de nota
        textArea.getDocument().addDocumentListener(new DocumentListener() {
            public void changedUpdate(DocumentEvent evt) {
                actualizarInfo();
                guardarNota();
            }

            @Override
            public void insertUpdate(DocumentEvent e) {
                actualizarInfo();
                guardarNota();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                actualizarInfo();
                guardarNota();
            }
        });
        splitPane.setRightComponent(textArea);

        // Panel de Información de la nota
        infoPane.setBorder(new MatteBorder(1, 0, 0, 0, (Color) new Color(0, 0, 0)));
        infoPane.setPreferredSize(new Dimension(10, 20));
        infoPane.setMinimumSize(new Dimension(10, 20));
        infoPane.setLayout(new GridLayout(0, 1, 0, 0));
        getContentPane().add(infoPane, BorderLayout.SOUTH);

        // Texto info nota
        infoLabel.setAlignment(Label.RIGHT);
        infoLabel.setPreferredSize(new Dimension(500, 20));
        infoLabel.setText("Lineas: 0, Palabras: 0, Caracteres: 0");
        infoPane.add(infoLabel);

        // CENTRAR VENTANA
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int x = (screenSize.width - getWidth()) / 2;
        int y = (screenSize.height - getHeight()) / 2;
        setLocation(x, y);
        setVisible(true);

        // Se carga los datos del usuario
        cargarDatos();
        updateNoteList();
        selectFirstNote();
        setTitle("Notas: " + usuario.getNombre() + " " + usuario.getApellido());
    }

    //
    private void guardarNota() {
        selectedNote.setContenido(textArea.getText());
    }

    private void renombrarNota() {
        System.out.println("ren");
        String nuevoNombre = JOptionPane.showInputDialog("Nuevo Nombre");
        if (nuevoNombre.isEmpty()) {
            JOptionPane.showConfirmDialog(this, "Nombre no permitido");
        } else {
            selectedNote.setTitulo(nuevoNombre);
        }
    }

    private void cargarDatos()   {

        File datosUsuariosR = new File("datosUsuario.json");

        // Leo los datos guardados en el equipo (si estan)
        if (datosUsuariosR.exists()) {
            cargarJson();
        } else {
            // Si no hay datos de usuario en el equipo se accede a la base de datos
            // Se abre la ventana de login
            DialogoInicio login = new DialogoInicio();

            switch (login.getAcction()) {
                case 1: { // SingUp
                    registro();
                    break;
                }
                case 2: { // Login
                    // Si se acepta el login...
                    entrar(login);
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

    private void entrar(DialogoInicio login) {

        usuario = Conector.obtenerUsuarioCon(login.getUserName(), login.getPassword());

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
    }

    private void registro() {
        // Se abre la ventana de registro
        DialogoRegistro singUp = new DialogoRegistro();

        // Se recogen los datos de registro
        String nombre = singUp.getUserName();
        String apellido = singUp.getSurname();
        String contrasena = singUp.getPassword();

        // Se solicita el regsitro a la base de datos
        if (Conector.crearUsuarioCon(nombre, apellido, contrasena)) {
            JOptionPane.showMessageDialog(this, "Usuario Registrado");
        } else {
            JOptionPane.showMessageDialog(this, "Error al registrar el usuario");
        }

        // En cualquier caso se le manda a la ventana de login para que intente crearse otra cuenta o se
        // inicie con la cuenta ya creada
        cargarDatos();
    }

    private void cargarJson() {
        try {
            Gson gson = new Gson();

            FileReader lector = new FileReader("datosUsuario.json");
            JsonReader lectorJson = new JsonReader(lector);

            usuario = gson.fromJson(lectorJson, Usuario.class);

            usuario = Conector.obtenerUsuarioCon(usuario.getNombre(), usuario.getContraseña());

            notas = usuario.getListaNotas();

            lectorJson.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Aumentar tamaño fuente
    private void aumantarFuente() {
        Font fuenteActual = textArea.getFont();
        Font nuevaFuente = fuenteActual.deriveFont(Font.PLAIN, fuenteActual.getSize() + 2);
        textArea.setFont(nuevaFuente);
        selectedNote.setTamanoTexto(fuenteActual.getSize());
    }

    // Disminuir tamaño fuente
    private void disminurFuente() {
        Font fuenteActual = textArea.getFont();
        Font nuevaFuente = fuenteActual.deriveFont(Font.PLAIN, fuenteActual.getSize() - 2);
        textArea.setFont(nuevaFuente);
        selectedNote.setTamanoTexto(fuenteActual.getSize());
    }

    // Reemplazar todas las coincidencias del texto
    private void replaceAll() {
        DialogoReemplazoTexto remplaceDialogue = new DialogoReemplazoTexto();
        remplaceDialogue.setVisible(true);
        textArea.setText(textArea.getText().replaceAll(remplaceDialogue.getOldText(), remplaceDialogue.getNewText()));
    }

    // Guardo la nota editada y la mando al servidor
    private void guardarNotas() {
        selectedNote.setContenido(textArea.getText());
        Conector.actualizarNotasCon(usuario);
    }

    // Guardar archivo como txt
    private void guardarComo() {
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
                writer.write(textArea.getText());
                JOptionPane.showMessageDialog(this, "Archivo guardado con éxito.", "Éxito", JOptionPane.INFORMATION_MESSAGE);
            } catch (IOException ex) {
                JOptionPane.showMessageDialog(this, "Error al guardar el archivo.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    // Elimino la nota y la mando al servidor
    private void deleteNote() {
        if (JOptionPane.showConfirmDialog(this, "¿Estas seguro de eliminar esta nota?", "Eliminar Nota", JOptionPane.YES_NO_CANCEL_OPTION) == 0) {
            notas.remove(selectedNote);
            selectFirstNote();
            Conector.actualizarNotasCon(usuario);
            updateNoteList();
        }
    }

    private void nuevaNota() {
        int lastId = notas.last().getId();
        notas.add(new Nota(lastId +  1, JOptionPane.showInputDialog("Nombre de la nota"), " ", fechaActual(), 20));

        selectedNote = notas.last();
        textArea.setText(selectedNote.getContenido());

        updateNoteList();
    }

    private void changeNote() {
        selectedNote = (Nota) listaNotas_JList.getSelectedValue();
        textArea.setText(selectedNote.getContenido());

        Font fuenteActual = textArea.getFont();
        Font nuevaFuente = fuenteActual.deriveFont(Font.PLAIN, selectedNote.getTamanoTexto());
        textArea.setFont(nuevaFuente);
    }

    private void updateNoteList() {
        DefaultListModel<Nota> modeloNotas = new DefaultListModel<Nota>();

        Iterator<Nota> itNotas = notas.iterator();

        while (itNotas.hasNext()) {
            Nota nextNota = itNotas.next();
            modeloNotas.addElement(nextNota);
        }

        listaNotas_JList.setModel(modeloNotas);

        listaNotas_JList.setCellRenderer(new RenderizadorLista());
    }

    private void selectFirstNote() {

        // Si no tiene notas le creo una
        if (notas.size() == 0) {
            notas.add(new Nota(1,"Tu primera nota","Esta es tu primera nota", fechaActual(),20));
            updateNoteList();
        }

        // Seleciono la primera nota
        selectedNote = notas.first();
        textArea.setText(selectedNote.getContenido());
        listaNotas_JList.setSelectedValue(selectedNote,true);

        Font fuenteActual = textArea.getFont();
        Font nuevaFuente = fuenteActual.deriveFont(Font.PLAIN, selectedNote.getTamanoTexto());
        textArea.setFont(nuevaFuente);
    }

    private String fechaActual() {
        Date now = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return dateFormat.format(now);
    }

    private void actualizarInfo() {
        // Saco el texto de la nota
        String nota = textArea.getText();

        // Saco el numero de lineas de la nota
        int nLineas = nota.split("\r\n|\r|\n").length;

        // Saco todas las palabras de la nota
        String[] palabras = nota.split("\\s+");
        int nPalabras = palabras.length;

        // Eliminio las "palabras" vacias
        for (String palabra : palabras) {
            if (palabra.isEmpty()) {
                nPalabras--;
            }
        }

        // Saco el numero de caracteres de la nota
        int nCaracteres = nota.length();

        // Actualizo la información
        if (nCaracteres > 0) {
            infoLabel.setText("Lineas: " + nLineas + ", Palabras: " + nPalabras + ", Caracteres: " + nCaracteres);
        } else {
            infoLabel.setText("Lineas: 0, Palabras: 0, Caracteres: 0");
        }
    }
}
