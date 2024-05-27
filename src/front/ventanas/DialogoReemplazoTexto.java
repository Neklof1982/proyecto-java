package front.ventanas;

import javax.swing.*;
import java.awt.*;

public class DialogoReemplazoTexto extends JDialog {

    // VARIABLES
    int accion;
    String nuevo;
    String antiguo;

    // INTERFAZ
    JTextField txtBienBenido = new JTextField("Reemplazar");
    JButton cerrar_JButton = new JButton("X");
    JLabel antiguoTexto_JLabel = new JLabel("Texto antiguo");
    JTextField antiguoTexto_JTextField = new JTextField();
    JLabel nuevoTexto_JLabel = new JLabel("Texto nuevo");
    JTextField nuevoTexto_JTextField = new JTextField();
    JButton reemplazar_JButton = new JButton("Reemplazar");
    JPanel reemplazoPane = new JPanel();
    Point initialClick;

    public DialogoReemplazoTexto() {
        // Estilos del formulario
        Estilos.dialogoReemplazoTexto(this);
        // Acciones de la interfaz
        Acciones.dialogoReemplazoTexto(this);
        // Mostrar
        setVisible(true);
    }

    void accionReemplazar() {

        if (comprobarAntiguoTexto()) {
            accion = 2;
            nuevo = nuevoTexto_JTextField.getText();
            antiguo = antiguoTexto_JTextField.getText();
            dispose();
        } else {
            JOptionPane.showMessageDialog(null, "El texto antiguo no puede estar vacio");
        }
    }

    boolean comprobarAntiguoTexto() {
        if (!antiguoTexto_JTextField.getText().isEmpty()) {
            return true;
        }
        return false;
    }

    void accionCerrar() {
        accion = 1;
        dispose();
    }

    public int getAccion() {
        return accion;
    }

    public String getNuevo() {
        return nuevo;
    }

    public String getAntiguo() {
        return antiguo;
    }
}