package net.daboross.defender;

import java.awt.Dimension;
import java.awt.Toolkit;
import javax.swing.JFrame;

/**
 *
 * @author daboross
 */
public class DefenderApplicationMain {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        JFrame jFrame = new JFrame("Defender");
        jFrame.setSize(640, 480);
        Dimension d = Toolkit.getDefaultToolkit().getScreenSize();
        jFrame.setLocation((d.width - 640) / 2, (d.height - 480) / 2);
        jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        DefenderMain main = new DefenderMain(jFrame);
        jFrame.setVisible(true);
        main.start();
    }
}
