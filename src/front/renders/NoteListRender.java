package front.renders;

import back.Nota;

import javax.swing.*;
import java.awt.*;

public class NoteListRender extends JLabel implements ListCellRenderer<Nota> {
    @Override
    public Component getListCellRendererComponent(JList<? extends Nota> list, Nota value, int index, boolean isSelected, boolean cellHasFocus) {

        setText(value.getTitulo());

        if (isSelected) {
            setBackground(list.getSelectionBackground());
            setForeground(list.getSelectionForeground());
        } else {
            setBackground(list.getBackground());
            setForeground(list.getForeground());
        }
        setOpaque(true);
        return this;
    }
}
