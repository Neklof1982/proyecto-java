package front.dialogos;

import front.renders.BordeRedondeado;

import javax.swing.*;
import javax.swing.border.MatteBorder;
import java.awt.*;
import java.awt.event.*;
import java.awt.geom.RoundRectangle2D;

public class DialogoReemplazoTexto extends JDialog {

    // VARIABLES
    int accion;
    String nuevo;
    String antiguo;

    // INTERFAZ
    private JTextField txtBienBenido = new JTextField("Reemplazar");
    private JLabel antiguoTexto_JLabel = new JLabel("Texto antiguo");
    private JTextField antiguoTexto_JTextField = new JTextField();
    private JLabel nuevoTexto_JLabel = new JLabel("Texto nuevo");
    private JTextField nuevoTexto_JTextField = new JTextField();
    private JButton reemplazar_JButton = new JButton("Reemplazar");
    private Point initialClick;

    public DialogoReemplazoTexto() {

        // ESTILOS VENTANA
        setTitle("Reemplazar");
        setBounds(100, 100, 400, 390);
        setModalityType(ModalityType.APPLICATION_MODAL);
        getRootPane().setDefaultButton(reemplazar_JButton);
        setUndecorated(true);
        setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        setShape(new RoundRectangle2D.Double(0, 0, getWidth(), getHeight(), 20, 20));

        // PANE
        JPanel reemplazoPane = new JPanel();
        reemplazoPane.setBackground(new Color(255, 255, 255));
        reemplazoPane.setLayout(null);
        reemplazoPane.setBorder(new BordeRedondeado(Color.BLACK, 30, 2));
        getContentPane().add(reemplazoPane, BorderLayout.CENTER);

        // BOTÓN DE CIERRE
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
        reemplazoPane.add(cerrar_JButton);

        // ETIQUETA BIENVENIDO
        txtBienBenido.setFont(new Font("Tahoma", Font.BOLD, 25));
        txtBienBenido.setFocusable(false);
        txtBienBenido.setBorder(null);
        txtBienBenido.setBounds(50, 54, 300, 40);
        reemplazoPane.add(txtBienBenido);

        // ETIQUETA NUEVO TEXTO
        antiguoTexto_JLabel.setFont(new Font("Tahoma", Font.PLAIN, 15));
        antiguoTexto_JLabel.setBorder(null);
        antiguoTexto_JLabel.setBounds(50, 119, 300, 20);
        antiguoTexto_JLabel.setFocusable(false);
        reemplazoPane.add(antiguoTexto_JLabel);

        // ANTIGUO TEXTO
        antiguoTexto_JTextField.setFont(new Font("Tahoma", Font.PLAIN, 15));
        antiguoTexto_JTextField.setBorder(new MatteBorder(0, 0, 1, 0, Color.BLACK));
        antiguoTexto_JTextField.setBounds(50, 150, 300, 30);
        antiguoTexto_JTextField.requestFocusInWindow();
        reemplazoPane.add(antiguoTexto_JTextField);

        // ETIQUETA NUEVO TEXTO
        nuevoTexto_JLabel.setFont(new Font("Tahoma", Font.PLAIN, 15));
        nuevoTexto_JLabel.setBorder(null);
        nuevoTexto_JLabel.setBounds(50, 201, 300, 20);
        nuevoTexto_JLabel.setFocusable(false);
        reemplazoPane.add(nuevoTexto_JLabel);

        // NUEVO TEXTO
        nuevoTexto_JTextField.setFont(new Font("Tahoma", Font.PLAIN, 15));
        nuevoTexto_JTextField.setBorder(new MatteBorder(0, 0, 1, 0, Color.BLACK));
        nuevoTexto_JTextField.setBounds(50, 232, 300, 30);
        nuevoTexto_JTextField.requestFocusInWindow();
        reemplazoPane.add(nuevoTexto_JTextField);

        // BOTÓN REEMPLAZAR
        reemplazar_JButton.setFont(new Font("Tahoma", Font.PLAIN, 15));
        reemplazar_JButton.setBounds(50, 300, 300, 40);
        reemplazar_JButton.setBorder(new BordeRedondeado(Color.BLACK, 20, 1));
        reemplazar_JButton.setFocusable(false);
        reemplazar_JButton.setBackground(new Color(0xffffff));
        reemplazar_JButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                accionReemplazar();
            }
        });
        reemplazoPane.add(reemplazar_JButton);

        // ATAJOS DE TECLADO
        InputMap inputMap = reemplazoPane.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW);
        ActionMap actionMap = reemplazoPane.getActionMap();

        inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_ENTER, 0), "accionReemplazar");
        inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), "cerrarAccion");

        actionMap.put("accionReemplazar", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                accionReemplazar();
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

    private void accionReemplazar() {

        if (comprobarAntiguoTexto()) {
            accion = 2;
            nuevo = nuevoTexto_JTextField.getText();
            antiguo = antiguoTexto_JTextField.getText();
            dispose();
        } else {
            JOptionPane.showMessageDialog(null, "El texto antiguo no puede estar vacio");
        }
    }

    private boolean comprobarAntiguoTexto() {
        if (!antiguoTexto_JTextField.getText().isEmpty()) {
            return true;
        }
        return false;
    }

    private void accionCerrar() {
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
