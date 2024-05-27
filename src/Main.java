import front.ventanas.Editor;

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
