package front.ventanas;

import back.Nota;
import back.Usuario;
import com.google.gson.Gson;
import com.google.gson.stream.JsonReader;
import conector.Conector;
import front.renders.RenderizadorLista;

import java.awt.*;
import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.TreeSet;

import javax.swing.*;

public class Editor extends JFrame {

    // #### ATRIBUTOS ###################

    // VARIABLES
    private Usuario usuario;
    private TreeSet<Nota> notas = new TreeSet<Nota>();
    private Nota selectedNote;

    // INTERFAZ
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
    JSplitPane splitPane = new JSplitPane();
    JList listaNotas_JList = new JList();
    JTextArea textArea = new JTextArea();
    JPanel infoPane = new JPanel();
    Label infoLabel = new Label(" ");
    JButton cerrarSesion_JButton = new JButton("");

    // #### CONSTRUCTOR #################

    public Editor() {
        // Estilos del editor
        Estilos.editor(this);
        // Acciones de la interfaz
        Acciones.editor(this);

        // Se carga los datos del usuario si el fichero de conexión y la base de datos son accesibles
        File ficheroConexion = new File("./conexion.txt");
        if (ficheroConexion.exists()) {
            if (Conector.comprobarConexion()) {
                if (cargarDatos()) {
                    actualizarListaNotas();
                    seleccionarPrimeraNota();
                }
            } else {
                JOptionPane.showMessageDialog(this, "No hay conexion con la base de datos");
                this.dispose();
            }
        } else {
            JOptionPane.showMessageDialog(this, "Falta \"conexion.txt\"");
            this.dispose();
        }
    }

    // BOTON CERRAR SESIÓN
    void cerrarSesion() {
        if (JOptionPane.showConfirmDialog(this, "¿Quiere cerrar sesión?") == 0) {

            File datosUsuario = new File("datosUsuario.json");
            if (datosUsuario.exists()) {
                datosUsuario.delete();
            }

            File datosEntrada = new File("entrada.json");
            if (datosEntrada.exists()) {
                datosEntrada.delete();
                dispose();
            }
        }
    }

    // Guardar las notas en local
    void guardarNota() {
        selectedNote.setContenido(textArea.getText());
    }

    // Guardar las notas en el servidor
    void guardarNotas() {
        selectedNote.setContenido(textArea.getText());
        Conector.actualizarNotasCon(usuario);
    }

    // Cambiar nombre a la nota selecionada
    void renombrarNota() {
        String nuevoNombre = JOptionPane.showInputDialog("Nuevo Nombre");
        if (nuevoNombre == null) {
            JOptionPane.showMessageDialog(this, "Nombre no permitido");
        } else {
            selectedNote.setTitulo(nuevoNombre);
        }
    }

    // Cargar los datos de los usurios
    boolean cargarDatos() {

        File datosUsuariosR = new File("datosUsuario.json");

        // Leo los datos guardados en el equipo (si estan)
        if (datosUsuariosR.exists()) {
            cargarJson();
        } else {
            // Si no hay datos de usuario en el equipo se accede a la base de datos
            // Se abre la ventana de login
            DialogoInicio login = new DialogoInicio();

            switch (login.getAccion()) {
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

        if (usuario != null) {
            return true;
        }
        return false;
    }

    // Ventana de login
    void entrar(DialogoInicio login) {
        usuario = Conector.obtenerUsuarioCon(login.getNombreUsuario(), login.getContrasena());
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
                setTitle("Notas: " + usuario.getNombre() + " " + usuario.getApellido());
            } catch (IOException e) {
                System.err.println("error");
            }
        }
    }

    // Ventana de Regisrtro
    void registro() {
        // Se abre la ventana de registro
        DialogoRegistro registro = new DialogoRegistro();
        if (registro.getAccion() == 2) {
            // Se recogen los datos de registro
            String nombre = registro.getNombre();
            String apellido = registro.getApellido();
            String contrasena = registro.getContrasena();
            // Se solicita el regsitro a la base de datos
            if (Conector.crearUsuarioCon(nombre, apellido, contrasena)) {
                JOptionPane.showMessageDialog(this, "Usuario Registrado");
            } else {
                JOptionPane.showMessageDialog(this, "Error al registrar el usuario");
            }
        }

        // En cualquier caso se le manda a la ventana de login para que intente crearse otra cuenta o se
        // inicie con la cuenta ya creada
        cargarDatos();
    }

    // Cargar datos en el equipo
    void cargarJson() {
        try {
            Gson gson = new Gson();
            FileReader lector = new FileReader("datosUsuario.json");
            JsonReader lectorJson = new JsonReader(lector);
            usuario = gson.fromJson(lectorJson, Usuario.class);
            usuario = Conector.obtenerUsuarioCon(usuario.getNombre(), usuario.getContraseña());
            notas = usuario.getListaNotas();
            lectorJson.close();
            setTitle("Notas: " + usuario.getNombre() + " " + usuario.getApellido());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Aumentar tamaño fuente
    void aumantarFuente() {
        Font fuenteActual = textArea.getFont();
        if (fuenteActual.getSize() <= 500) {
            Font nuevaFuente = fuenteActual.deriveFont(Font.PLAIN, fuenteActual.getSize() + 2);
            textArea.setFont(nuevaFuente);
            selectedNote.setTamanoTexto(fuenteActual.getSize());
        }
    }

    // Disminuir tamaño fuente
    void disminurFuente() {
        Font fuenteActual = textArea.getFont();
        if (fuenteActual.getSize() >= 20) {
            Font nuevaFuente = fuenteActual.deriveFont(Font.PLAIN, fuenteActual.getSize() - 2);
            textArea.setFont(nuevaFuente);
            selectedNote.setTamanoTexto(fuenteActual.getSize());
        }
    }

    // Reemplazar todas las coincidencias del texto
    void reemplazar() {
        DialogoReemplazoTexto dialogoReemplazo = new DialogoReemplazoTexto();

        if (dialogoReemplazo.getAccion() == 2) {
            textArea.setText(textArea.getText().replaceAll(dialogoReemplazo.getAntiguo(), dialogoReemplazo.getNuevo()));
        }
    }

    // Guardar archivo como txt
    void guardarComo() {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setDialogTitle("Seleccionar ubicación para guardar");
        fileChooser.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
        int userSelection = fileChooser.showSaveDialog(this);

        if (userSelection == JFileChooser.APPROVE_OPTION) {
            File fileToSave = fileChooser.getSelectedFile();

            if (!fileToSave.getName().endsWith(".txt")) {
                fileToSave = new File(fileToSave.getAbsolutePath() + ".txt");
            }

            try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileToSave))) {
                writer.write(textArea.getText());
                JOptionPane.showMessageDialog(this, "Archivo guardado con éxito.", "Éxito", JOptionPane.INFORMATION_MESSAGE);
            } catch (IOException ex) {
                JOptionPane.showMessageDialog(this, "Error al guardar el archivo.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    // Abrir desde
    void abrirDesde() {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setDialogTitle("Seleccionar archivo para abrir");
        fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);

        int userSelection = fileChooser.showOpenDialog(this);

        if (userSelection == JFileChooser.APPROVE_OPTION) {
            File fileToOpen = fileChooser.getSelectedFile();

            try (BufferedReader reader = new BufferedReader(new FileReader(fileToOpen))) {
                StringBuilder content = new StringBuilder();
                String line;
                while ((line = reader.readLine()) != null) {
                    content.append(line).append("\n");
                }
                // Creo una nota
                nuevaNota();
                // Le asigno el contenido
                notas.last().setContenido(content.toString());
                // Actualizo la lista de notas
                actualizarListaNotas();
                selectedNote = notas.last();
                textArea.setText(selectedNote.getContenido());
            } catch (IOException ex) {
                JOptionPane.showMessageDialog(this, "Error al abrir el archivo.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    // Elimino la nota y la mando al servidor
    void deleteNote() {
        if (JOptionPane.showConfirmDialog(this, "¿Estas seguro de eliminar esta nota?", "Eliminar Nota", JOptionPane.YES_NO_CANCEL_OPTION) == 0) {
            notas.remove(selectedNote);
            seleccionarPrimeraNota();
            Conector.actualizarNotasCon(usuario);
            actualizarListaNotas();
        }
    }

    // Crear nota
    void nuevaNota() {
        int lastId = notas.last().getId();
        notas.add(new Nota(lastId + 1, JOptionPane.showInputDialog("Nombre de la nota"), "", fechaActual(), 20));

        selectedNote = notas.last();
        textArea.setText(selectedNote.getContenido());

        actualizarListaNotas();
    }

    // Cambiar nota
    void cambiarNota() {
        selectedNote = (Nota) listaNotas_JList.getSelectedValue();
        textArea.setText(selectedNote.getContenido());

        Font fuenteActual = textArea.getFont();
        Font nuevaFuente = fuenteActual.deriveFont(Font.PLAIN, selectedNote.getTamanoTexto());
        textArea.setFont(nuevaFuente);
    }

    // Actualizar lista notas
    void actualizarListaNotas() {
        DefaultListModel<Nota> modeloNotas = new DefaultListModel<Nota>();

        Iterator<Nota> itNotas = notas.iterator();

        while (itNotas.hasNext()) {
            Nota nextNota = itNotas.next();
            modeloNotas.addElement(nextNota);
        }

        listaNotas_JList.setModel(modeloNotas);

        listaNotas_JList.setCellRenderer(new RenderizadorLista());
    }

    // Seleccionar primera nota de la lista
    void seleccionarPrimeraNota() {

        // Si no tiene notas le creo una
        if (notas.size() == 0) {
            notas.add(new Nota(1, "Tu primera nota", "Esta es la primera nota de " + usuario.getNombre(), fechaActual(), 20));
            actualizarListaNotas();
        }

        // Seleciono la primera nota
        selectedNote = notas.first();
        textArea.setText(selectedNote.getContenido());
        listaNotas_JList.setSelectedValue(selectedNote, true);

        Font fuenteActual = textArea.getFont();
        Font nuevaFuente = fuenteActual.deriveFont(Font.PLAIN, selectedNote.getTamanoTexto());
        textArea.setFont(nuevaFuente);
    }

    // Calcular fecha actual
    String fechaActual() {
        Date now = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return dateFormat.format(now);
    }

    // Actualizar información de la nota
    void actualizarInformacion() {
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
