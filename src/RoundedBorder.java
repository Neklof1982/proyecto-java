import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;

import javax.swing.border.AbstractBorder;

public class RoundedBorder extends AbstractBorder {
  private final int radius;

  public RoundedBorder(int radius) {
    this.radius = radius;
  }

  @Override
  public void paintBorder(Component c, Graphics g, int x, int y, int width, int height) {
    Graphics2D g2d = (Graphics2D) g;
    g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
    g2d.setColor(Color.GRAY); // Puedes cambiar el color según tus preferencias
    g2d.drawRoundRect(x, y, width - 1, height - 1, radius, radius);
  }
}
