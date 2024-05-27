package front.ventanas;

import front.renders.BordeRedondeado;

import java.awt.*;
import java.awt.event.*;
import java.awt.geom.RoundRectangle2D;

import javax.swing.*;
import javax.swing.border.MatteBorder;

public class DialogoRegistro extends JDialog {

    // #### ATRIBUTOS #################

    // VARIABLES
    private String nombre;
    private String apellido;
    private String contrasena;
    private int accion;

    // INTERFAZ
    JTextField nombreUsuario_JtextField;
    JPasswordField contrasena_JPassword2;
    JTextField nombreUsuario_JLabel;
    JTextField repiteContrasena_JLabel;
    JTextField txtBienBenido;
    JButton registrarse_JButton;
    JButton cerrar_JButton = new JButton("X");
    JTextField contrasena_JLabel;
    JPasswordField contrasena_JPassword1;
    JTextField apellido_JtextField;
    JTextField apellido_JLabel;
    Point initialClick;
    JPanel registroPane = new JPanel();

    public DialogoRegistro() {
        // Estilos del formulario
        Estilos.dialogoRegistro(this);
        // Acciones de la interfaz
        Acciones.dialogoRegistro(this);
        // Mostrar
        setVisible(true);
    }

    // #### METODOS ###################

    void accionCerrar() {
        accion = 1;
        dispose();
    }

    void accionRegistrar() {
        if (comprobarNombre() && comprobarApellido() && comprobarContrasena()) {
            nombre = nombreUsuario_JtextField.getText();
            apellido = apellido_JtextField.getText();
            contrasena = String.valueOf(contrasena_JPassword1.getPassword());
            accion = 2;
            dispose();
        }
    }

    boolean comprobarContrasena() {
        String contrasena1 = String.valueOf(contrasena_JPassword1.getPassword());
        String contrasena2 = String.valueOf(contrasena_JPassword2.getPassword());

        // Se comprueba que sean iguales
        if (contrasena1.equals(contrasena2)) {
            // Se comprueba que no esten vacias
            if (!contrasena1.isEmpty()) {
                // Se comprueba que no contenga espacios
                if (!contrasena1.contains(" ")) {
                    return true;
                } else {
                    JOptionPane.showMessageDialog(this, "La contraseña no puede tener espacios");
                }
            } else {
                JOptionPane.showMessageDialog(this, "La contraseña no puede estar vacia");
            }
        } else {
            JOptionPane.showMessageDialog(this, "Las contraseñas no coinciden");
        }
        return false;
    }

    boolean comprobarNombre() {

        // Se comprueba que no este vacio
        if (!nombreUsuario_JtextField.getText().isEmpty()) {
            // Se comprueba que no tenga espacios
            if (!nombreUsuario_JtextField.getText().contains(" ")) {
                return true;
            } else {
                JOptionPane.showMessageDialog(this, "El nombre no puede tener espacios");
            }
        } else {
            JOptionPane.showMessageDialog(this, "El nombre no puede estar vacio");
        }
        return false;
    }

    boolean comprobarApellido() {

        // Se comprueba que no este vacio
        if (!apellido_JtextField.getText().isEmpty()) {
            // Se comprueba que no tenga espacios
            if (!apellido_JtextField.getText().contains(" ")) {
                return true;
            } else {
                JOptionPane.showMessageDialog(this, "El apellido no puede tener espacios");
            }
        } else {
            JOptionPane.showMessageDialog(this, "El apellido no puede estar vacio");
        }
        return false;
    }

    // #### GET and SET ###############

    public String getApellido() {
        return apellido;
    }

    public String getNombre() {
        return nombre;
    }

    public String getContrasena() {
        return String.valueOf(contrasena);
    }

    public int getAccion() {
        return accion;
    }
}
