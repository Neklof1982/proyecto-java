import front.Editor;
import front.dialogos.DialogoInicio;
import front.dialogos.DialogoRegistro;

import java.awt.*;

public class Main {
    public static void main(String[] args) {

        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    Editor editor = new Editor();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }
}
