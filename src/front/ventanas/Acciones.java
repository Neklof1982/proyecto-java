package front.ventanas;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.*;
import java.awt.event.*;

import static com.sun.java.accessibility.util.AWTEventMonitor.addMouseListener;
import static com.sun.java.accessibility.util.AWTEventMonitor.addMouseMotionListener;

public class Acciones {

    static void editor(Editor ed) {

        // Toolbar
        ed.guardar_JButton.addActionListener(e -> ed.guardarNotas());
        ed.renombrar_JButton.addActionListener(e -> ed.renombrarNota());
        ed.eliminar_JButton.addActionListener(e -> ed.deleteNote());
        ed.nuevaNota_JButton.addActionListener(e -> ed.nuevaNota());
        ed.guardarComo_JButton.addActionListener(e -> ed.guardarComo());
        ed.abrirDesde_JButton.addActionListener(e -> ed.abrirDesde());
        ed.reemplazar_JButton.addActionListener(e -> ed.reemplazar());
        ed.aumantarFuente_JButton.addActionListener(e -> ed.aumantarFuente());
        ed.disminuirFuente_JButton.addActionListener(e -> ed.disminurFuente());
        ed.cerrarSesion_JButton.addActionListener(e -> ed.cerrarSesion());

        // Lista notas
        ed.listaNotas_JList.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                ed.cambiarNota();
            }
        });

        // TextArea
        ed.textArea.getDocument().addDocumentListener(new DocumentListener() {
            public void changedUpdate(DocumentEvent evt) {
                ed.actualizarInformacion();
                ed.guardarNota();
            }

            @Override
            public void insertUpdate(DocumentEvent e) {
                ed.actualizarInformacion();
                ed.guardarNota();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                ed.actualizarInformacion();
                ed.guardarNota();
            }
        });

        // Guardar (CTRL + S)
        ed.textArea.getActionMap().put("guardar", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ed.guardarNotas();
            }
        });

        // Cabiar tamaño texto (CTRL + WHEEL)
        ed.textArea.addMouseWheelListener((MouseWheelListener) e -> {
            if (e.isControlDown()) {
                if (e.getWheelRotation() < 0) {
                    ed.aumantarFuente();
                } else {
                    ed.disminurFuente();
                }
            }
        });
    }

    static void dialogoInicio(DialogoInicio di) {

        di.cerrar_JButton.addActionListener(e -> {
            di.accionCerrar();
        });

        di.cerrar_JButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                di.cerrar_JButton.setBackground(new Color(0xe81123));
            }

            @Override
            public void mouseExited(MouseEvent e) {
                di.cerrar_JButton.setBackground(Color.WHITE);
            }
        });

        di.registrarse_JButton.addActionListener(e -> di.registrarseAccion());

        di.entrar_JButton.addActionListener(e -> di.accionEntrar());

        // ATAJOS DE TECLADO
        InputMap inputMap = di.inicioPane.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW);
        ActionMap actionMap = di.inicioPane.getActionMap();

        inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_ENTER, 0), "entrarAccion");
        inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), "cerrarAccion");

        actionMap.put("entrarAccion", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                di.accionEntrar();
            }
        });

        actionMap.put("cerrarAccion", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                di.accionCerrar();
            }
        });

        // VENTANA ARRASTRABLE
        di.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                di.initialClick = e.getPoint();
                di.getComponentAt(di.initialClick);
            }
        });

        di.addMouseMotionListener(new MouseAdapter() {
            @Override
            public void mouseDragged(MouseEvent e) {
                // Obtener la posición actual de la ventana
                int thisX = di.getLocation().x;
                int thisY = di.getLocation().y;

                // Determinar cuánto se ha movido el ratón
                int xMoved = e.getX() - di.initialClick.x;
                int yMoved = e.getY() - di.initialClick.y;

                // Mover la ventana a la nueva posición
                int X = thisX + xMoved;
                int Y = thisY + yMoved;
                di.setLocation(X, Y);
            }
        });
    }

    static void dialogoRegistro(DialogoRegistro dr) {
        dr.cerrar_JButton.addActionListener(e -> dr.accionCerrar());

        dr.cerrar_JButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                dr.cerrar_JButton.setBackground(new Color(0xe81123));
            }

            @Override
            public void mouseExited(MouseEvent e) {
                dr.cerrar_JButton.setBackground(Color.WHITE);
            }
        });

        dr.registrarse_JButton.addActionListener(e -> dr.accionRegistrar());

        // ATAJOS DE TECLADO
        InputMap inputMap = dr.registroPane.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW);
        ActionMap actionMap = dr.registroPane.getActionMap();

        inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_ENTER, 0), "accionRegistrar");
        inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), "cerrarAccion");

        actionMap.put("accionRegistrar", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dr.accionRegistrar();
            }
        });

        actionMap.put("cerrarAccion", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dr.accionCerrar();
            }
        });

        // VENTANA ARRASTRABLE
        addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                dr.initialClick = e.getPoint();
                dr.getComponentAt(dr.initialClick);
            }
        });

        addMouseMotionListener(new MouseAdapter() {
            @Override
            public void mouseDragged(MouseEvent e) {
                // Obtener la posición actual de la ventana
                int thisX = dr.getLocation().x;
                int thisY = dr.getLocation().y;

                // Determinar cuánto se ha movido el ratón
                int xMoved = e.getX() - dr.initialClick.x;
                int yMoved = e.getY() - dr.initialClick.y;

                // Mover la ventana a la nueva posición
                int X = thisX + xMoved;
                int Y = thisY + yMoved;
                dr.setLocation(X, Y);
            }
        });
    }

    static void dialogoReemplazoTexto(DialogoReemplazoTexto drt) {

        drt.cerrar_JButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                drt.accionCerrar();
            }
        });
        drt.cerrar_JButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                drt.cerrar_JButton.setBackground(new Color(0xe81123));
            }

            @Override
            public void mouseExited(MouseEvent e) {
                drt.cerrar_JButton.setBackground(Color.WHITE);
            }
        });


        drt.reemplazar_JButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                drt.accionReemplazar();
            }
        });

        // ATAJOS DE TECLADO
        InputMap inputMap = drt.reemplazoPane.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW);
        ActionMap actionMap = drt.reemplazoPane.getActionMap();

        inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_ENTER, 0), "accionReemplazar");
        inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), "cerrarAccion");

        actionMap.put("accionReemplazar", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                drt.accionReemplazar();
            }
        });

        actionMap.put("cerrarAccion", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                drt.accionCerrar();
            }
        });

        // VENTANA ARRASTRABLE
        addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                drt.initialClick = e.getPoint();
                drt.getComponentAt(drt.initialClick);
            }
        });

        addMouseMotionListener(new MouseAdapter() {
            @Override
            public void mouseDragged(MouseEvent e) {
                // Obtener la posición actual de la ventana
                int thisX = drt.getLocation().x;
                int thisY = drt.getLocation().y;

                // Determinar cuánto se ha movido el ratón
                int xMoved = e.getX() - drt.initialClick.x;
                int yMoved = e.getY() - drt.initialClick.y;

                // Mover la ventana a la nueva posición
                int X = thisX + xMoved;
                int Y = thisY + yMoved;
                drt.setLocation(X, Y);
            }
        });
    }
}
