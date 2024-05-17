import front.Editor;

import java.awt.*;

public class Main {
    public static void main(String[] args) {

        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    Editor editor2 = new Editor();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }
}
