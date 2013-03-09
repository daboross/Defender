package net.daboross.defender;

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
        jFrame.setVisible(true);
        jFrame.setSize(640, 480);
        jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        DefenderMain main = new DefenderMain(jFrame);
        main.start();
    }
}
