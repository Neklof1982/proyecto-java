package front.ventanas;

import javax.swing.*;
import java.awt.*;

public class DialogoInicio extends JDialog {

    // #### ATRIBUTOS #################

    // VARIABLES
    private int accion = 3;
    private String nombreUsuario;
    private String contrasena;

    // INTERFAZ
    JPanel inicioPane = new JPanel();
    JTextField txtBienBenido;
    JButton cerrar_JButton;
    JTextField nombreUsuario_JLabel;
    JTextField nombreUsuario_JtextField;
    JTextField contrasena_JLabel;
    JPasswordField contrasena_JTextField;
    JButton registrarse_JButton;
    JButton entrar_JButton;
    Point initialClick;

    // #### CONSTRUCTOR ###############

    public DialogoInicio() {
        // Estilos del formulario
        Estilos.dialogoInicio(this);
        // Acciones de la interfaz
        Acciones.dialogoInicio(this);
        // Mostrar
        setVisible(true);
    }

    // #### METODOS ###################

    // Establezco la variable de la opción de registro
    void registrarseAccion() {
        accion = 1;
        dispose();
    }

    // Recojo los valores de los inputs
    void accionEntrar() {
        nombreUsuario = nombreUsuario_JtextField.getText().trim();
        contrasena = String.valueOf(contrasena_JTextField.getPassword()).trim();

        // Se comprueba si la contraseña y el usuario estan vacios
        if (comprobarNombre() && comprobarContrasena()) {
            accion = 2;
            dispose();
        }
    }

    boolean comprobarNombre() {
        if (!nombreUsuario.isEmpty()) {
            return true;
        }
        JOptionPane.showMessageDialog(null, "El nombre de usuario no puede estar vacio");
        return false;
    }

    boolean comprobarContrasena() {
        if (!contrasena.isEmpty()) {
            return true;
        }
        JOptionPane.showMessageDialog(null, "La contraseña no puede estar vacia");
        return false;
    }

    // Establezco la variable de la opción de cancelado
    void accionCerrar() {
        accion = 3;
        dispose();
    }

    String getNombreUsuario() {
        return nombreUsuario;
    }

    String getContrasena() {
        return String.valueOf(contrasena);
    }

    int getAccion() {
        return accion;
    }
}
