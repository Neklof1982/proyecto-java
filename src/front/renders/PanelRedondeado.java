package front.renders;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;

import javax.swing.JPanel;

class PanelRedondeado extends JPanel {
  private final int radius;

  public PanelRedondeado(int radius) {
    super();
    this.radius = radius;
    this.setOpaque(false); // Para que la pintura personalizada funcione
  }

  @Override
  protected void paintComponent(Graphics g) {
    super.paintComponent(g);
    Graphics2D g2 = (Graphics2D) g;
    g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
    g2.setColor(getBackground()); // Color de fondo del panel
    g2.fillRoundRect(0, 0, getWidth(), getHeight(), radius, radius); // Fondo redondeado
  }
}