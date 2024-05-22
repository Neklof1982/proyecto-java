package front.dialogos;

import front.renders.BordeRedondeado;

import javax.swing.*;
import javax.swing.border.MatteBorder;
import java.awt.*;
import java.awt.event.*;
import java.awt.geom.RoundRectangle2D;

public class DialogoInicio extends JDialog {

    // #### ATRIBUTOS #################

    // VARIABLES
    private int accion = 3;
    private String nombreUsuario;
    private String contrasena;

    // INTERFAZ
    private JTextField txtBienBenido;
    private JButton cerrar_JButton;
    private JTextField nombreUsuario_JLabel;
    private JTextField nombreUsuario_JtextField;
    private JTextField contrasena_JLabel;
    private JPasswordField contrasena_JTextField;
    private JButton registrarse_JButton;
    private JButton entrar_JButton;
    private Point initialClick;

    public DialogoInicio() {

        // ESTILOS VENTANA
        setTitle("Inicio");
        setBounds(100, 100, 400, 390);
        setModalityType(ModalityType.APPLICATION_MODAL);
        getRootPane().setDefaultButton(entrar_JButton);
        setUndecorated(true);
        setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        setShape(new RoundRectangle2D.Double(0, 0, getWidth(), getHeight(), 20, 20));

        // PANE
        JPanel inicioPane = new JPanel();
        inicioPane.setBackground(new Color(255, 255, 255));
        inicioPane.setLayout(null);
        inicioPane.setBorder(new BordeRedondeado(Color.BLACK, 30, 2));
        getContentPane().add(inicioPane, BorderLayout.CENTER);

        // BOTÓN DE CIERRE
        cerrar_JButton = new JButton("X");
        cerrar_JButton.setFont(new Font("Tahoma", Font.BOLD, 20));
        cerrar_JButton.setBackground(Color.WHITE);
        cerrar_JButton.setBorder(null);
        cerrar_JButton.setBounds(350, 11, 40, 40);
        cerrar_JButton.setFocusable(false);
        cerrar_JButton.addActionListener(e -> {
            accionCerrar();
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
        inicioPane.add(cerrar_JButton);

        // ETIQUETA BIENVENIDO
        txtBienBenido = new JTextField("Bienvenido");
        txtBienBenido.setFont(new Font("Tahoma", Font.BOLD, 25));
        txtBienBenido.setFocusable(false);
        txtBienBenido.setBorder(null);
        txtBienBenido.setBounds(50, 54, 300, 40);
        inicioPane.add(txtBienBenido);

        // ETIQUETA NOMBRE DE USUARIO
        nombreUsuario_JLabel = new JTextField("Nombre de usuario");
        nombreUsuario_JLabel.setFont(new Font("Tahoma", Font.PLAIN, 15));
        nombreUsuario_JLabel.setBorder(null);
        nombreUsuario_JLabel.setBounds(50, 119, 300, 20);
        nombreUsuario_JLabel.setFocusable(false);
        inicioPane.add(nombreUsuario_JLabel);

        // NOMBRE DE USUARIO
        nombreUsuario_JtextField = new JTextField();
        nombreUsuario_JtextField.setFont(new Font("Tahoma", Font.PLAIN, 15));
        nombreUsuario_JtextField.setBorder(new MatteBorder(0, 0, 1, 0, Color.BLACK));
        nombreUsuario_JtextField.setBounds(50, 150, 300, 30);
        nombreUsuario_JtextField.requestFocusInWindow();
        inicioPane.add(nombreUsuario_JtextField);

        // ETIQUETA CONTRASEÑA
        contrasena_JLabel = new JTextField("Contraseña");
        contrasena_JLabel.setFont(new Font("Tahoma", Font.PLAIN, 15));
        contrasena_JLabel.setBorder(null);
        contrasena_JLabel.setBounds(50, 201, 300, 20);
        contrasena_JLabel.setFocusable(false);
        inicioPane.add(contrasena_JLabel);

        // CONTRASEÑA
        contrasena_JTextField = new JPasswordField();
        contrasena_JTextField.setFont(new Font("Tahoma", Font.PLAIN, 15));
        contrasena_JTextField.setBorder(new MatteBorder(0, 0, 1, 0, Color.BLACK));
        contrasena_JTextField.setBounds(50, 232, 300, 30);
        contrasena_JTextField.requestFocusInWindow();
        inicioPane.add(contrasena_JTextField);

        // BOTÓN REGISTRARSE
        registrarse_JButton = new JButton("Registrarse");
        registrarse_JButton.setFont(new Font("Tahoma", Font.PLAIN, 15));
        registrarse_JButton.setBounds(50, 300, 140, 40);
        registrarse_JButton.setBorder(new BordeRedondeado(Color.BLACK, 20, 1));
        registrarse_JButton.setFocusable(false);
        registrarse_JButton.setBackground(new Color(0xffffff));
        registrarse_JButton.addActionListener(e -> registrarseAccion());
        inicioPane.add(registrarse_JButton);

        // BOTÓN ENTRAR
        entrar_JButton = new JButton("Entrar");
        entrar_JButton.setFont(new Font("Tahoma", Font.PLAIN, 15));
        entrar_JButton.setBounds(210, 300, 140, 40);
        entrar_JButton.setBorder(new BordeRedondeado(Color.BLACK, 20, 1));
        entrar_JButton.setFocusable(false);
        entrar_JButton.setBackground(new Color(0xffffff));
        entrar_JButton.addActionListener(e -> accionEntrar());
        inicioPane.add(entrar_JButton);

        // ATAJOS DE TECLADO
        InputMap inputMap = inicioPane.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW);
        ActionMap actionMap = inicioPane.getActionMap();

        inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_ENTER, 0), "entrarAccion");
        inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), "cerrarAccion");

        actionMap.put("entrarAccion", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                accionEntrar();
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

    // Establezco la variable de la opción de registro
    private void registrarseAccion() {
        accion = 1;
        dispose();
    }

    // Recojo los valores de los inputs
    private void accionEntrar() {
        nombreUsuario = nombreUsuario_JtextField.getText().trim();
        contrasena = String.valueOf(contrasena_JTextField.getPassword()).trim();

        // Se comprueba si la contraseña y el usuario estan vacios
        if (comprobarNombre() && comprobarContrasena()) {
            accion = 2;
            dispose();
        }
    }

    private boolean comprobarNombre() {
        if (!nombreUsuario.isEmpty()) {
            return true;
        }
        JOptionPane.showMessageDialog(null, "El nombre de usuario no puede estar vacio");
        return false;
    }

    private boolean comprobarContrasena() {
        if (!contrasena.isEmpty()) {
            return true;
        }
        JOptionPane.showMessageDialog(null, "La contraseña no puede estar vacia");
        return false;
    }

    // Establezco la variable de la opción de cancelado
    private void accionCerrar() {
        accion = 3;
        dispose();
    }

    public String getNombreUsuario() {
        return nombreUsuario;
    }

    public String getContrasena() {
        return String.valueOf(contrasena);
    }

    public int getAccion() {
        return accion;
    }
}
