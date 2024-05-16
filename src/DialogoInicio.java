import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.MatteBorder;

public class DialogoInicio extends JDialog {

  private static final long serialVersionUID = 1L;
  private JTextField nombreUsuario_JtextField;
  private JTextField contrasena_JTextField;
  private JTextField nombreUsuario_JLabel;
  private JTextField contrasena_JLabel;
  private JTextField txtBienBenido;
  private JButton registrarse_JButton;
  private JButton entrar_JButton;

  public DialogoInicio() {
    setUndecorated(true);
    setBounds(100, 100, 400, 400);
    JPanel InicioPane = new JPanel();
    InicioPane.setBackground(new Color(255, 255, 255));
    getContentPane().add(InicioPane, BorderLayout.CENTER);
    InicioPane.setLayout(null);

    // BOTÓN DE CIERRE
    JButton cerrar_JButton = new JButton("X");
    cerrar_JButton.setFont(new Font("Tahoma", Font.BOLD, 20));
    cerrar_JButton.setBackground(Color.WHITE);
    cerrar_JButton.setBorder(null);
    cerrar_JButton.setBounds(350, 11, 40, 40);
    cerrar_JButton.setFocusable(false);
    cerrar_JButton.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        dispose();
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

    InicioPane.add(cerrar_JButton);

    // ETIQUETA BIENVENIDO
    txtBienBenido = new JTextField("Bienvenido");
    txtBienBenido.setFont(new Font("Tahoma", Font.BOLD, 25));
    txtBienBenido.setFocusable(false);
    txtBienBenido.setBorder(null);
    txtBienBenido.setBounds(50, 54, 300, 40);
    InicioPane.add(txtBienBenido);

    // ETIQUETA NOMBRE DE USUARIO
    nombreUsuario_JLabel = new JTextField("Nombre de usuario");
    nombreUsuario_JLabel.setFont(new Font("Tahoma", Font.PLAIN, 15));
    nombreUsuario_JLabel.setBorder(null);
    nombreUsuario_JLabel.setBounds(50, 119, 300, 20);
    nombreUsuario_JLabel.setFocusable(false);
    InicioPane.add(nombreUsuario_JLabel);

    // VALOR NOMBRE DE USUARIO
    nombreUsuario_JtextField = new JTextField();
    nombreUsuario_JtextField.setFont(new Font("Tahoma", Font.PLAIN, 15));
    nombreUsuario_JtextField.setBorder(new MatteBorder(0, 0, 1, 0, Color.BLACK));
    nombreUsuario_JtextField.setBounds(50, 150, 300, 30);
    nombreUsuario_JtextField.requestFocusInWindow();
    InicioPane.add(nombreUsuario_JtextField);

    // ETIQUETA CONTRASEÑA
    contrasena_JLabel = new JTextField("Contraseña");
    contrasena_JLabel.setFont(new Font("Tahoma", Font.PLAIN, 15));
    contrasena_JLabel.setBorder(null);
    contrasena_JLabel.setBounds(50, 219, 300, 20);
    contrasena_JLabel.setFocusable(false);
    InicioPane.add(contrasena_JLabel);

    // VALOR CONTRASEÑA
    contrasena_JTextField = new JPasswordField();
    contrasena_JTextField.setFont(new Font("Tahoma", Font.PLAIN, 15));
    contrasena_JTextField.setBorder(new MatteBorder(0, 0, 1, 0, Color.BLACK));
    contrasena_JTextField.setBounds(50, 250, 300, 30);
    contrasena_JTextField.requestFocusInWindow();
    InicioPane.add(contrasena_JTextField);

    // BOTÓN REGISTRARSE
    registrarse_JButton = new JButton("Registrarse");
    registrarse_JButton.setFont(new Font("Tahoma", Font.PLAIN, 15));
    registrarse_JButton.setBounds(50, 315, 140, 40);
    registrarse_JButton.setBorder(new RoundedBorder(10));
    registrarse_JButton.setFocusable(false);
    registrarse_JButton.setBackground(new Color(0xffffff));
    InicioPane.add(registrarse_JButton);

    // BOTÓN ENTRAR
    entrar_JButton = new JButton("Entrar");
    entrar_JButton.setFont(new Font("Tahoma", Font.PLAIN, 15));
    entrar_JButton.setBounds(210, 315, 140, 40);
    entrar_JButton.setBorder(new RoundedBorder(10));
    entrar_JButton.setFocusable(false);
    entrar_JButton.setBackground(new Color(0xffffff));
    InicioPane.add(entrar_JButton);

    // CENTRAR VENTANA
    Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    int x = (screenSize.width - getWidth()) / 2;
    int y = (screenSize.height - getHeight()) / 2;
    setLocation(x, y);
  }

  public static void main(String[] args) {
    try {
      DialogoInicio dialog = new DialogoInicio();
      dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
      dialog.setVisible(true);
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
}
