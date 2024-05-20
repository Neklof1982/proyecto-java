package front.dialogos;

import front.renders.BordeRedondeado;

import java.awt.*;
import java.awt.event.*;
import java.awt.geom.RoundRectangle2D;

import javax.swing.*;
import javax.swing.border.MatteBorder;

public class DialogoRegistro extends JDialog {

    // #### ATRIBUTOS #################

    // VARIABLES
    String nombre;
    String apellido;
    char[] contrasena;
    int accion;

    // INTERFAZ
    private JTextField nombreUsuario_JtextField;
    private JPasswordField contrasena_JPassword2;
    private JTextField nombreUsuario_JLabel;
    private JTextField repiteContrasena_JLabel;
    private JTextField txtBienBenido;
    private JButton registrarse_JButton;
    private JTextField contrasena_JLabel;
    private JPasswordField contrasena_JPassword1;
    private JTextField apellido_JtextField;
    private JTextField apellido_JLabel;
    private Point initialClick;

    public DialogoRegistro() {

        // ESTILOS VENTANA
        setTitle("Registro");
        setBounds(100, 100, 400, 560);
        setModalityType(ModalityType.APPLICATION_MODAL);
        getRootPane().setDefaultButton(registrarse_JButton);
        setUndecorated(true);
        setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        setShape(new RoundRectangle2D.Double(0, 0, getWidth(), getHeight(), 20, 20));

        // PANE
        JPanel registroPane = new JPanel();
        registroPane.setBackground(new Color(255, 255, 255));
        registroPane.setLayout(null);
        registroPane.setBorder(new BordeRedondeado(Color.BLACK, 30, 2));
        getContentPane().add(registroPane, BorderLayout.CENTER);

        // BOTÓN CERRAR
        JButton cerrar_JButton = new JButton("X");
        cerrar_JButton.setFont(new Font("Tahoma", Font.BOLD, 20));
        cerrar_JButton.setBackground(Color.WHITE);
        cerrar_JButton.setBorder(null);
        cerrar_JButton.setBounds(350, 11, 40, 40);
        cerrar_JButton.setFocusable(false);
        cerrar_JButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                accionCerrar();
            }
        });
        cerrar_JButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                cerrar_JButton.setBackground(new Color(0xe81123));
            }

            @Override
            public void mouseExited(MouseEvent e) {
                cerrar_JButton.setBackground(Color.WHITE);
            }
        });

        registroPane.add(cerrar_JButton);

        // ETIQUETA BIENVENIDO
        txtBienBenido = new JTextField("Crear cuenta");
        txtBienBenido.setFont(new Font("Tahoma", Font.BOLD, 25));
        txtBienBenido.setFocusable(false);
        txtBienBenido.setBorder(null);
        txtBienBenido.setBounds(50, 54, 300, 40);
        registroPane.add(txtBienBenido);

        // ETIQUETA NOMBRE DE USUARIO
        nombreUsuario_JLabel = new JTextField("Nombre de usuario");
        nombreUsuario_JLabel.setFont(new Font("Tahoma", Font.PLAIN, 15));
        nombreUsuario_JLabel.setBorder(null);
        nombreUsuario_JLabel.setBounds(50, 119, 300, 20);
        nombreUsuario_JLabel.setFocusable(false);
        registroPane.add(nombreUsuario_JLabel);

        // NOMBRE DE USUARIO
        nombreUsuario_JtextField = new JTextField();
        nombreUsuario_JtextField.setFont(new Font("Tahoma", Font.PLAIN, 15));
        nombreUsuario_JtextField.setBorder(new MatteBorder(0, 0, 1, 0, Color.BLACK));
        nombreUsuario_JtextField.setBounds(50, 150, 300, 30);
        nombreUsuario_JtextField.requestFocusInWindow();
        registroPane.add(nombreUsuario_JtextField);

        // ETIQUETA APELLIDO
        apellido_JLabel = new JTextField("Apellido");
        apellido_JLabel.setFont(new Font("Tahoma", Font.PLAIN, 15));
        apellido_JLabel.setFocusable(false);
        apellido_JLabel.setBorder(null);
        apellido_JLabel.setBounds(50, 201, 300, 20);
        registroPane.add(apellido_JLabel);

        // APELLIDO
        apellido_JtextField = new JTextField();
        apellido_JtextField.setFont(new Font("Tahoma", Font.PLAIN, 15));
        apellido_JtextField.setBorder(new MatteBorder(0, 0, 1, 0, Color.BLACK));
        apellido_JtextField.setBounds(50, 232, 300, 30);
        registroPane.add(apellido_JtextField);

        // ETIQUETA CONTRASEÑA 1
        contrasena_JLabel = new JTextField("Contraseña");
        contrasena_JLabel.setFont(new Font("Tahoma", Font.PLAIN, 15));
        contrasena_JLabel.setFocusable(false);
        contrasena_JLabel.setBorder(null);
        contrasena_JLabel.setBounds(50, 283, 300, 20);
        registroPane.add(contrasena_JLabel);

        // CONTRASEÑA 1
        contrasena_JPassword1 = new JPasswordField();
        contrasena_JPassword1.setFont(new Font("Tahoma", Font.PLAIN, 15));
        contrasena_JPassword1.setBorder(new MatteBorder(0, 0, 1, 0, Color.BLACK));
        contrasena_JPassword1.setBounds(50, 314, 300, 30);
        registroPane.add(contrasena_JPassword1);

        // ETIQUETA CONTRASEÑA 2
        repiteContrasena_JLabel = new JTextField("Repite contraseña");
        repiteContrasena_JLabel.setFont(new Font("Tahoma", Font.PLAIN, 15));
        repiteContrasena_JLabel.setBorder(null);
        repiteContrasena_JLabel.setBounds(50, 365, 300, 20);
        repiteContrasena_JLabel.setFocusable(false);
        registroPane.add(repiteContrasena_JLabel);

        // CONTRASEÑA 2
        contrasena_JPassword2 = new JPasswordField();
        contrasena_JPassword2.setFont(new Font("Tahoma", Font.PLAIN, 15));
        contrasena_JPassword2.setBorder(new MatteBorder(0, 0, 1, 0, Color.BLACK));
        contrasena_JPassword2.setBounds(50, 396, 300, 30);
        contrasena_JPassword2.requestFocusInWindow();
        registroPane.add(contrasena_JPassword2);

        // BOTÓN REGISTRARSE
        registrarse_JButton = new JButton("Registrarse");
        registrarse_JButton.setFont(new Font("Tahoma", Font.PLAIN, 15));
        registrarse_JButton.setBounds(50, 470, 300, 40);
        registrarse_JButton.setBorder(new BordeRedondeado(Color.BLACK, 20, 1));
        registrarse_JButton.setFocusable(false);
        registrarse_JButton.setBackground(new Color(0xffffff));
        registrarse_JButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                accionRegistrar();
            }
        });
        registroPane.add(registrarse_JButton);

        // ATAJOS DE TECLADO
        InputMap inputMap = registroPane.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW);
        ActionMap actionMap = registroPane.getActionMap();

        inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_ENTER, 0), "accionRegistrar");
        inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), "cerrarAccion");

        actionMap.put("accionRegistrar", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                accionRegistrar();
            }
        });

        actionMap.put("cerrarAccion", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                accionCerrar();
            }
        });

        // VENTANA ARRASTRABLE
        addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                initialClick = e.getPoint();
                getComponentAt(initialClick);
            }
        });

        addMouseMotionListener(new MouseAdapter() {
            @Override
            public void mouseDragged(MouseEvent e) {
                // Obtener la posición actual de la ventana
                int thisX = getLocation().x;
                int thisY = getLocation().y;

                // Determinar cuánto se ha movido el ratón
                int xMoved = e.getX() - initialClick.x;
                int yMoved = e.getY() - initialClick.y;

                // Mover la ventana a la nueva posición
                int X = thisX + xMoved;
                int Y = thisY + yMoved;
                setLocation(X, Y);
            }
        });

        // CENTRAR VENTANA
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int x = (screenSize.width - getWidth()) / 2;
        int y = (screenSize.height - getHeight()) / 2;
        setLocation(x, y);
        setVisible(true);
    }

    // #### METODOS ###################

    private void accionCerrar() {
        accion = 1;
        dispose();
    }

    private void accionRegistrar() {
        if (comprobarNombre() && comprobarApellido() && comprobarContrasena()) {
            nombre = nombreUsuario_JtextField.getText();
            apellido = apellido_JtextField.getText();
            contrasena = contrasena_JPassword1.getPassword();
            accion = 2;
            dispose();
        }
    }

    private boolean comprobarContrasena() {
        if (String.valueOf(String.valueOf(contrasena_JPassword1.getPassword())).equals(String.valueOf(contrasena_JPassword2.getPassword()))) {
            return true;
        }
        JOptionPane.showMessageDialog(this, "Las contraseñas no coinciden");
        return false;
    }

    private boolean comprobarNombre() {
        if (!(nombreUsuario_JLabel.getText().equals("")) || !(nombreUsuario_JLabel.getText().equals(" ")) || !(nombreUsuario_JtextField.getText() == null)) {
            return true;
        }
        JOptionPane.showMessageDialog(this, "Nombre no permitido");
        return false;
    }

    private boolean comprobarApellido() {
        if (!(apellido_JtextField.getText().equals("")) || !(apellido_JtextField.getText().equals(" ")) || !(apellido_JtextField.getText() == null)) {
            return true;
        }
        JOptionPane.showMessageDialog(this, "Apellido no permitido");
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
