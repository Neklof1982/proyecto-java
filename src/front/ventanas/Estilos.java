package front.ventanas;

import front.renders.BordeRedondeado;

import javax.swing.*;
import javax.swing.border.MatteBorder;
import java.awt.*;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.awt.geom.RoundRectangle2D;

public class Estilos {

    static void editor(Editor ed) {

        // ESTILOS VENTANA
        ed.setTitle("Editor");
        ed.setBounds(100, 100, 700, 391);
        ed.setMinimumSize(new Dimension(800, 500));
        ed.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int x = (screenSize.width - ed.getWidth()) / 2;
        int y = (screenSize.height - ed.getHeight()) / 2;
        ed.setLocation(x, y);

        // TOOLBAR
        ed.toolBar.setBorder(null);
        ed.toolBar.setBackground(new Color(0, 128, 192));
        ed.toolBar.setFloatable(false);
        ed.getContentPane().add(ed.toolBar, BorderLayout.NORTH);

        // SEPARADOR 1
        ed.separador1.setPreferredSize(new Dimension(40, 40));
        ed.separador1.setMinimumSize(new Dimension(40, 40));
        ed.separador1.setMaximumSize(new Dimension(40, 0));
        ed.separador1.setFocusable(false);
        ed.toolBar.add(ed.separador1);

        // BOTON GUARDAR
        ed.guardar_JButton.setBorder(null);
        ed.guardar_JButton.setBackground(new Color(0, 128, 192));
        ed.guardar_JButton.setPreferredSize(new Dimension(50, 50));
        ed.guardar_JButton.setMinimumSize(new Dimension(50, 50));
        ed.guardar_JButton.setMaximumSize(new Dimension(50, 50));
        ed.guardar_JButton.setFocusable(false);
        ImageIcon icon = new ImageIcon(ed.getClass().getResource("/front/iconos/guardar.png"));
        ed.guardar_JButton.setIcon(icon);
        ed.toolBar.add(ed.guardar_JButton);

        // BOTON RENOMBRAR
        ed.renombrar_JButton.setBorder(null);
        ed.renombrar_JButton.setBackground(new Color(0, 128, 192));
        ed.renombrar_JButton.setPreferredSize(new Dimension(50, 50));
        ed.renombrar_JButton.setMinimumSize(new Dimension(50, 50));
        ed.renombrar_JButton.setMaximumSize(new Dimension(50, 50));
        ed.renombrar_JButton.setFocusable(false);
        icon = new ImageIcon(ed.getClass().getResource("/front/iconos/renombrar.png"));
        ed.renombrar_JButton.setIcon(icon);
        ed.toolBar.add(ed.renombrar_JButton);

        // BOTON ELIMINAR
        ed.eliminar_JButton.setBorder(null);
        ed.eliminar_JButton.setBackground(new Color(0, 128, 192));
        ed.eliminar_JButton.setPreferredSize(new Dimension(50, 50));
        ed.eliminar_JButton.setMinimumSize(new Dimension(50, 50));
        ed.eliminar_JButton.setMaximumSize(new Dimension(50, 50));
        ed.eliminar_JButton.setFocusable(false);
        icon = new ImageIcon(ed.getClass().getResource("/front/iconos/eliminar.png"));
        ed.eliminar_JButton.setIcon(icon);
        ed.toolBar.add(ed.eliminar_JButton);

        // BOTON NUEVA NOTA
        ed.nuevaNota_JButton.setBorder(null);
        ed.nuevaNota_JButton.setBackground(new Color(0, 128, 192));
        ed.nuevaNota_JButton.setPreferredSize(new Dimension(50, 50));
        ed.nuevaNota_JButton.setMinimumSize(new Dimension(50, 50));
        ed.nuevaNota_JButton.setMaximumSize(new Dimension(50, 50));
        ed.nuevaNota_JButton.setFocusable(false);
        icon = new ImageIcon(ed.getClass().getResource("/front/iconos/nueva.png"));
        ed.nuevaNota_JButton.setIcon(icon);
        ed.toolBar.add(ed.nuevaNota_JButton);

        // BOTON GUARDAR COMO
        ed.guardarComo_JButton.setBorder(null);
        ed.guardarComo_JButton.setBackground(new Color(0, 128, 192));
        ed.guardarComo_JButton.setPreferredSize(new Dimension(50, 50));
        ed.guardarComo_JButton.setMinimumSize(new Dimension(50, 50));
        ed.guardarComo_JButton.setMaximumSize(new Dimension(50, 50));
        ed.guardarComo_JButton.setFocusable(false);
        icon = new ImageIcon(ed.getClass().getResource("/front/iconos/guardarComo.png"));
        ed.guardarComo_JButton.setIcon(icon);
        ed.toolBar.add(ed.guardarComo_JButton);

        // BOTON ABRIR DESDE
        ed.abrirDesde_JButton.setBorder(null);
        ed.abrirDesde_JButton.setBackground(new Color(0, 128, 192));
        ed.abrirDesde_JButton.setPreferredSize(new Dimension(50, 50));
        ed.abrirDesde_JButton.setMinimumSize(new Dimension(50, 50));
        ed.abrirDesde_JButton.setMaximumSize(new Dimension(50, 50));
        ed.abrirDesde_JButton.setFocusable(false);
        icon = new ImageIcon(ed.getClass().getResource("/front/iconos/abrirDesde.png"));
        ed.abrirDesde_JButton.setIcon(icon);
        ed.toolBar.add(ed.abrirDesde_JButton);

        // SEPARADOR 2
        ed.separador2.setMaximumSize(new Dimension(9999999, 0));
        ed.toolBar.add(ed.separador2);

        // BOTON REEMPLAZAR
        ed.reemplazar_JButton.setBorder(null);
        ed.reemplazar_JButton.setBackground(new Color(0, 128, 192));
        ed.reemplazar_JButton.setPreferredSize(new Dimension(50, 50));
        ed.reemplazar_JButton.setMinimumSize(new Dimension(50, 50));
        ed.reemplazar_JButton.setMaximumSize(new Dimension(50, 50));
        ed.reemplazar_JButton.setFocusable(false);
        icon = new ImageIcon(ed.getClass().getResource("/front/iconos/reemplazar.png"));
        ed.reemplazar_JButton.setIcon(icon);
        ed.toolBar.add(ed.reemplazar_JButton);

        // BOTON AUMENTAR FUENTE
        ed.aumantarFuente_JButton.setBorder(null);
        ed.aumantarFuente_JButton.setBackground(new Color(0, 128, 192));
        ed.aumantarFuente_JButton.setPreferredSize(new Dimension(50, 50));
        ed.aumantarFuente_JButton.setMinimumSize(new Dimension(50, 50));
        ed.aumantarFuente_JButton.setMaximumSize(new Dimension(50, 50));
        ed.aumantarFuente_JButton.setFocusable(false);
        icon = new ImageIcon(ed.getClass().getResource("/front/iconos/aumentarFuente.png"));
        ed.aumantarFuente_JButton.setIcon(icon);
        ed.toolBar.add(ed.aumantarFuente_JButton);

        // BOTON DISMINUIR FUENTE
        ed.disminuirFuente_JButton.setBorder(null);
        ed.disminuirFuente_JButton.setBackground(new Color(0, 128, 192));
        ed.disminuirFuente_JButton.setPreferredSize(new Dimension(50, 50));
        ed.disminuirFuente_JButton.setMinimumSize(new Dimension(50, 50));
        ed.disminuirFuente_JButton.setMaximumSize(new Dimension(50, 50));
        ed.disminuirFuente_JButton.setFocusable(false);
        icon = new ImageIcon(ed.getClass().getResource("/front/iconos/disminuirFuente.png"));
        ed.disminuirFuente_JButton.setIcon(icon);
        ed.toolBar.add(ed.disminuirFuente_JButton);

        // BOTON CERRAR SESIÓN
        ed.cerrarSesion_JButton.setBorder(null);
        ed.cerrarSesion_JButton.setBackground(new Color(0, 128, 192));
        ed.cerrarSesion_JButton.setPreferredSize(new Dimension(50, 50));
        ed.cerrarSesion_JButton.setMinimumSize(new Dimension(50, 50));
        ed.cerrarSesion_JButton.setMaximumSize(new Dimension(50, 50));
        ed.cerrarSesion_JButton.setFocusable(false);
        icon = new ImageIcon(ed.getClass().getResource("/front/iconos/usuario.png"));
        ed.cerrarSesion_JButton.setIcon(icon);
        ed.toolBar.add(ed.cerrarSesion_JButton);

        // SEPARADOR 3
        ed.separador3.setPreferredSize(new Dimension(40, 40));
        ed.separador3.setMinimumSize(new Dimension(40, 40));
        ed.separador3.setMaximumSize(new Dimension(40, 0));
        ed.separador3.setFocusable(false);
        ed.toolBar.add(ed.separador3);

        // PANEL DIVIDIDO
        ed.splitPane.setDividerSize(0);
        ed.splitPane.setBorder(null);
        ed.getContentPane().add(ed.splitPane, BorderLayout.CENTER);

        // LISTA DE NOTAS
        ed.listaNotas_JList.setPreferredSize(new Dimension(150, 0));
        ed.listaNotas_JList.setBackground(new Color(0, 128, 192));
        ed.listaNotas_JList.setBorder(null);
        ed.listaNotas_JList.setMinimumSize(new Dimension(100, 0));
        ed.listaNotas_JList.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        ed.listaNotas_JList.setFont(new Font("Tahoma", Font.BOLD, 20));
        ed.splitPane.setLeftComponent(ed.listaNotas_JList);

        // EDITOR DE NOTAS
        ed.textArea.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        ed.splitPane.setRightComponent(ed.textArea);

        // PANEL DE INFORMACIÓN
        ed.infoPane.setBorder(new MatteBorder(1, 0, 0, 0, (Color) new Color(0, 0, 0)));
        ed.infoPane.setPreferredSize(new Dimension(10, 20));
        ed.infoPane.setMinimumSize(new Dimension(10, 20));
        ed.infoPane.setLayout(new GridLayout(0, 1, 0, 0));
        ed.getContentPane().add(ed.infoPane, BorderLayout.SOUTH);

        // INFORMACIÓN DE LA NOTA
        ed.infoLabel.setAlignment(Label.RIGHT);
        ed.infoLabel.setPreferredSize(new Dimension(500, 20));
        ed.infoLabel.setText("Lineas: 0, Palabras: 0, Caracteres: 0");
        ed.infoPane.add(ed.infoLabel);

        // ATAJOS DE TECLADO
        ed.textArea.getInputMap(JComponent.WHEN_FOCUSED).put(KeyStroke.getKeyStroke(KeyEvent.VK_S, InputEvent.CTRL_DOWN_MASK), "guardar");

        // Hacer visible
        ed.setVisible(true);
    }

    static void dialogoInicio(DialogoInicio di) {

        // ESTILOS VENTANA
        di.setTitle("Inicio");
        di.setBounds(100, 100, 400, 390);
        di.setModalityType(Dialog.ModalityType.APPLICATION_MODAL);
        di.getRootPane().setDefaultButton(di.entrar_JButton);
        di.setUndecorated(true);
        di.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        di.setShape(new RoundRectangle2D.Double(0, 0, di.getWidth(), di.getHeight(), 20, 20));
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int x = (screenSize.width - di.getWidth()) / 2;
        int y = (screenSize.height - di.getHeight()) / 2;
        di.setLocation(x, y);
        di.inicioPane.setBackground(new Color(255, 255, 255));
        di.inicioPane.setLayout(null);
        di.inicioPane.setBorder(new BordeRedondeado(Color.BLACK, 30, 2));
        di.getContentPane().add(di.inicioPane, BorderLayout.CENTER);

        // BOTÓN DE CIERRE
        di.cerrar_JButton = new JButton("X");
        di.cerrar_JButton.setFont(new Font("Tahoma", Font.BOLD, 20));
        di.cerrar_JButton.setBackground(Color.WHITE);
        di.cerrar_JButton.setBorder(null);
        di.cerrar_JButton.setBounds(350, 11, 40, 40);
        di.cerrar_JButton.setFocusable(false);
        di.inicioPane.add(di.cerrar_JButton);

        // ETIQUETA BIENVENIDO
        di.txtBienBenido = new JTextField("Bienvenido");
        di.txtBienBenido.setFont(new Font("Tahoma", Font.BOLD, 25));
        di.txtBienBenido.setFocusable(false);
        di.txtBienBenido.setBorder(null);
        di.txtBienBenido.setBounds(50, 54, 300, 40);
        di.inicioPane.add(di.txtBienBenido);

        // ETIQUETA NOMBRE DE USUARIO
        di.nombreUsuario_JLabel = new JTextField("Nombre de usuario");
        di.nombreUsuario_JLabel.setFont(new Font("Tahoma", Font.PLAIN, 15));
        di.nombreUsuario_JLabel.setBorder(null);
        di.nombreUsuario_JLabel.setBounds(50, 119, 300, 20);
        di.nombreUsuario_JLabel.setFocusable(false);
        di.inicioPane.add(di.nombreUsuario_JLabel);

        // NOMBRE DE USUARIO
        di.nombreUsuario_JtextField = new JTextField();
        di.nombreUsuario_JtextField.setFont(new Font("Tahoma", Font.PLAIN, 15));
        di.nombreUsuario_JtextField.setBorder(new MatteBorder(0, 0, 1, 0, Color.BLACK));
        di.nombreUsuario_JtextField.setBounds(50, 150, 300, 30);
        di.nombreUsuario_JtextField.requestFocusInWindow();
        di.inicioPane.add(di.nombreUsuario_JtextField);

        // ETIQUETA CONTRASEÑA
        di.contrasena_JLabel = new JTextField("Contraseña");
        di.contrasena_JLabel.setFont(new Font("Tahoma", Font.PLAIN, 15));
        di.contrasena_JLabel.setBorder(null);
        di.contrasena_JLabel.setBounds(50, 201, 300, 20);
        di.contrasena_JLabel.setFocusable(false);
        di.inicioPane.add(di.contrasena_JLabel);

        // CONTRASEÑA
        di.contrasena_JTextField = new JPasswordField();
        di.contrasena_JTextField.setFont(new Font("Tahoma", Font.PLAIN, 15));
        di.contrasena_JTextField.setBorder(new MatteBorder(0, 0, 1, 0, Color.BLACK));
        di.contrasena_JTextField.setBounds(50, 232, 300, 30);
        di.contrasena_JTextField.requestFocusInWindow();
        di.inicioPane.add(di.contrasena_JTextField);

        // BOTÓN REGISTRARSE
        di.registrarse_JButton = new JButton("Registrarse");
        di.registrarse_JButton.setFont(new Font("Tahoma", Font.PLAIN, 15));
        di.registrarse_JButton.setBounds(50, 300, 140, 40);
        di.registrarse_JButton.setBorder(new BordeRedondeado(Color.BLACK, 20, 1));
        di.registrarse_JButton.setFocusable(false);
        di.registrarse_JButton.setBackground(new Color(0xffffff));
        di.inicioPane.add(di.registrarse_JButton);

        // BOTÓN ENTRAR
        di.entrar_JButton = new JButton("Entrar");
        di.entrar_JButton.setFont(new Font("Tahoma", Font.PLAIN, 15));
        di.entrar_JButton.setBounds(210, 300, 140, 40);
        di.entrar_JButton.setBorder(new BordeRedondeado(Color.BLACK, 20, 1));
        di.entrar_JButton.setFocusable(false);
        di.entrar_JButton.setBackground(new Color(0xffffff));
        di.inicioPane.add(di.entrar_JButton);
    }

    static void dialogoRegistro(DialogoRegistro dr) {

        // ESTILOS VENTANA
        dr.setTitle("Registro");
        dr.setBounds(100, 100, 400, 560);
        dr.setModalityType(Dialog.ModalityType.APPLICATION_MODAL);
        dr.getRootPane().setDefaultButton(dr.registrarse_JButton);
        dr.setUndecorated(true);
        dr.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        dr.setShape(new RoundRectangle2D.Double(0, 0, dr.getWidth(), dr.getHeight(), 20, 20));
        dr.registroPane.setBackground(new Color(255, 255, 255));
        dr.registroPane.setLayout(null);
        dr.registroPane.setBorder(new BordeRedondeado(Color.BLACK, 30, 2));
        dr.getContentPane().add(dr.registroPane, BorderLayout.CENTER);
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int x = (screenSize.width - dr.getWidth()) / 2;
        int y = (screenSize.height - dr.getHeight()) / 2;
        dr.setLocation(x, y);

        // BOTÓN CERRAR
        dr.cerrar_JButton.setFont(new Font("Tahoma", Font.BOLD, 20));
        dr.cerrar_JButton.setBackground(Color.WHITE);
        dr.cerrar_JButton.setBorder(null);
        dr.cerrar_JButton.setBounds(350, 11, 40, 40);
        dr.cerrar_JButton.setFocusable(false);
        dr.registroPane.add(dr.cerrar_JButton);

        // ETIQUETA BIENVENIDO
        dr.txtBienBenido = new JTextField("Crear cuenta");
        dr.txtBienBenido.setFont(new Font("Tahoma", Font.BOLD, 25));
        dr.txtBienBenido.setFocusable(false);
        dr.txtBienBenido.setBorder(null);
        dr.txtBienBenido.setBounds(50, 54, 300, 40);
        dr.registroPane.add(dr.txtBienBenido);

        // ETIQUETA NOMBRE DE USUARIO
        dr.nombreUsuario_JLabel = new JTextField("Nombre de usuario");
        dr.nombreUsuario_JLabel.setFont(new Font("Tahoma", Font.PLAIN, 15));
        dr.nombreUsuario_JLabel.setBorder(null);
        dr.nombreUsuario_JLabel.setBounds(50, 119, 300, 20);
        dr.nombreUsuario_JLabel.setFocusable(false);
        dr.registroPane.add(dr.nombreUsuario_JLabel);

        // NOMBRE DE USUARIO
        dr.nombreUsuario_JtextField = new JTextField();
        dr.nombreUsuario_JtextField.setFont(new Font("Tahoma", Font.PLAIN, 15));
        dr.nombreUsuario_JtextField.setBorder(new MatteBorder(0, 0, 1, 0, Color.BLACK));
        dr.nombreUsuario_JtextField.setBounds(50, 150, 300, 30);
        dr.nombreUsuario_JtextField.requestFocusInWindow();
        dr.registroPane.add(dr.nombreUsuario_JtextField);

        // ETIQUETA APELLIDO
        dr.apellido_JLabel = new JTextField("Apellido");
        dr.apellido_JLabel.setFont(new Font("Tahoma", Font.PLAIN, 15));
        dr.apellido_JLabel.setFocusable(false);
        dr.apellido_JLabel.setBorder(null);
        dr.apellido_JLabel.setBounds(50, 201, 300, 20);
        dr.registroPane.add(dr.apellido_JLabel);

        // APELLIDO
        dr.apellido_JtextField = new JTextField();
        dr.apellido_JtextField.setFont(new Font("Tahoma", Font.PLAIN, 15));
        dr.apellido_JtextField.setBorder(new MatteBorder(0, 0, 1, 0, Color.BLACK));
        dr.apellido_JtextField.setBounds(50, 232, 300, 30);
        dr.registroPane.add(dr.apellido_JtextField);

        // ETIQUETA CONTRASEÑA 1
        dr.contrasena_JLabel = new JTextField("Contraseña");
        dr.contrasena_JLabel.setFont(new Font("Tahoma", Font.PLAIN, 15));
        dr.contrasena_JLabel.setFocusable(false);
        dr.contrasena_JLabel.setBorder(null);
        dr.contrasena_JLabel.setBounds(50, 283, 300, 20);
        dr.registroPane.add(dr.contrasena_JLabel);

        // CONTRASEÑA 1
        dr.contrasena_JPassword1 = new JPasswordField();
        dr.contrasena_JPassword1.setFont(new Font("Tahoma", Font.PLAIN, 15));
        dr.contrasena_JPassword1.setBorder(new MatteBorder(0, 0, 1, 0, Color.BLACK));
        dr.contrasena_JPassword1.setBounds(50, 314, 300, 30);
        dr.registroPane.add(dr.contrasena_JPassword1);

        // ETIQUETA CONTRASEÑA 2
        dr.repiteContrasena_JLabel = new JTextField("Repite contraseña");
        dr.repiteContrasena_JLabel.setFont(new Font("Tahoma", Font.PLAIN, 15));
        dr.repiteContrasena_JLabel.setBorder(null);
        dr.repiteContrasena_JLabel.setBounds(50, 365, 300, 20);
        dr.repiteContrasena_JLabel.setFocusable(false);
        dr.registroPane.add(dr.repiteContrasena_JLabel);

        // CONTRASEÑA 2
        dr.contrasena_JPassword2 = new JPasswordField();
        dr.contrasena_JPassword2.setFont(new Font("Tahoma", Font.PLAIN, 15));
        dr.contrasena_JPassword2.setBorder(new MatteBorder(0, 0, 1, 0, Color.BLACK));
        dr.contrasena_JPassword2.setBounds(50, 396, 300, 30);
        dr.contrasena_JPassword2.requestFocusInWindow();
        dr.registroPane.add(dr.contrasena_JPassword2);

        // BOTÓN REGISTRARSE
        dr.registrarse_JButton = new JButton("Registrarse");
        dr.registrarse_JButton.setFont(new Font("Tahoma", Font.PLAIN, 15));
        dr.registrarse_JButton.setBounds(50, 470, 300, 40);
        dr.registrarse_JButton.setBorder(new BordeRedondeado(Color.BLACK, 20, 1));
        dr.registrarse_JButton.setFocusable(false);
        dr.registrarse_JButton.setBackground(new Color(0xffffff));
        dr.registroPane.add(dr.registrarse_JButton);
    }

    static void dialogoReemplazoTexto(DialogoReemplazoTexto drt) {

        // ESTILOS VENTANA
        drt.setTitle("Reemplazar");
        drt.setBounds(100, 100, 400, 390);
        drt.setModalityType(Dialog.ModalityType.APPLICATION_MODAL);
        drt.getRootPane().setDefaultButton(drt.reemplazar_JButton);
        drt.setUndecorated(true);
        drt.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        drt.setShape(new RoundRectangle2D.Double(0, 0, drt.getWidth(), drt.getHeight(), 20, 20));
        drt.reemplazoPane.setBackground(new Color(255, 255, 255));
        drt.reemplazoPane.setLayout(null);
        drt.reemplazoPane.setBorder(new BordeRedondeado(Color.BLACK, 30, 2));
        drt.getContentPane().add(drt.reemplazoPane, BorderLayout.CENTER);
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int x = (screenSize.width - drt.getWidth()) / 2;
        int y = (screenSize.height - drt.getHeight()) / 2;
        drt.setLocation(x, y);

        // BOTÓN DE CIERRE
        drt.cerrar_JButton.setFont(new Font("Tahoma", Font.BOLD, 20));
        drt.cerrar_JButton.setBackground(Color.WHITE);
        drt.cerrar_JButton.setBorder(null);
        drt.cerrar_JButton.setBounds(350, 11, 40, 40);
        drt.cerrar_JButton.setFocusable(false);
        drt.reemplazoPane.add(drt.cerrar_JButton);

        // ETIQUETA BIENVENIDO
        drt.txtBienBenido.setFont(new Font("Tahoma", Font.BOLD, 25));
        drt.txtBienBenido.setFocusable(false);
        drt.txtBienBenido.setBorder(null);
        drt.txtBienBenido.setBounds(50, 54, 300, 40);
        drt.reemplazoPane.add(drt.txtBienBenido);

        // ETIQUETA NUEVO TEXTO
        drt.antiguoTexto_JLabel.setFont(new Font("Tahoma", Font.PLAIN, 15));
        drt.antiguoTexto_JLabel.setBorder(null);
        drt.antiguoTexto_JLabel.setBounds(50, 119, 300, 20);
        drt.antiguoTexto_JLabel.setFocusable(false);
        drt.reemplazoPane.add(drt.antiguoTexto_JLabel);

        // ANTIGUO TEXTO
        drt.antiguoTexto_JTextField.setFont(new Font("Tahoma", Font.PLAIN, 15));
        drt.antiguoTexto_JTextField.setBorder(new MatteBorder(0, 0, 1, 0, Color.BLACK));
        drt.antiguoTexto_JTextField.setBounds(50, 150, 300, 30);
        drt.antiguoTexto_JTextField.requestFocusInWindow();
        drt.reemplazoPane.add(drt.antiguoTexto_JTextField);

        // ETIQUETA NUEVO TEXTO
        drt.nuevoTexto_JLabel.setFont(new Font("Tahoma", Font.PLAIN, 15));
        drt.nuevoTexto_JLabel.setBorder(null);
        drt.nuevoTexto_JLabel.setBounds(50, 201, 300, 20);
        drt.nuevoTexto_JLabel.setFocusable(false);
        drt.reemplazoPane.add(drt.nuevoTexto_JLabel);

        // NUEVO TEXTO
        drt.nuevoTexto_JTextField.setFont(new Font("Tahoma", Font.PLAIN, 15));
        drt.nuevoTexto_JTextField.setBorder(new MatteBorder(0, 0, 1, 0, Color.BLACK));
        drt.nuevoTexto_JTextField.setBounds(50, 232, 300, 30);
        drt.nuevoTexto_JTextField.requestFocusInWindow();
        drt.reemplazoPane.add(drt.nuevoTexto_JTextField);

        // BOTÓN REEMPLAZAR
        drt.reemplazar_JButton.setFont(new Font("Tahoma", Font.PLAIN, 15));
        drt.reemplazar_JButton.setBounds(50, 300, 300, 40);
        drt.reemplazar_JButton.setBorder(new BordeRedondeado(Color.BLACK, 20, 1));
        drt.reemplazar_JButton.setFocusable(false);
        drt.reemplazar_JButton.setBackground(new Color(0xffffff));
        drt.reemplazoPane.add(drt.reemplazar_JButton);
    }
}
