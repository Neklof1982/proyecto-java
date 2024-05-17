package front.renders;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;

import javax.swing.border.AbstractBorder;

public class BordeRedondeado extends AbstractBorder {

  private final Color color;
  private final int radio;
  private final int grosor;

  public BordeRedondeado(Color color, int radio, int grosor) {
    this.color = color;
    this.radio = radio;
    this.grosor = grosor;
  }

  @Override
  public void paintBorder(Component c, Graphics g, int x, int y, int ancho, int alto) {
    Graphics2D g2 = (Graphics2D) g;
    g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
    g2.setColor(color);
    g2.setStroke(new BasicStroke(grosor));
    g2.drawRoundRect(x, y, ancho - 1, alto - 1, radio - 10, radio - 10); // Radio para las esquinas
  }
}