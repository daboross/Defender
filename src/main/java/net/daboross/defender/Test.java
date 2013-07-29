package net.daboross.defender;

import java.awt.Canvas;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import org.lwjgl.LWJGLException;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;
import org.lwjgl.opengl.GL11;

/**
 *
 * @author daboross
 */
public class Test {

    public static void main(String[] args) throws Exception {
        JFrame jFrame = new JFrame("test");
        jFrame.setResizable(false);
        jFrame.setPreferredSize(new Dimension(640, 480));
        jFrame.pack();
        Dimension d = Toolkit.getDefaultToolkit().getScreenSize();
        jFrame.setLocation((d.width - 640) / 2, (d.height - 480) / 2);
        jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Canvas canvas = new Canvas();
        jFrame.add(canvas);
        canvas.addMouseMotionListener(new MouseMotionListener() {
            public void mouseMoved(MouseEvent e) {
                System.out.println(e);
            }

            public void mouseDragged(MouseEvent e) {
                System.out.println(e);
            }
        });
        jFrame.setVisible(true);
        try {
            Display.setParent(canvas);
            Display.setDisplayMode(new DisplayMode(640, 480));
            Display.create();
            GL11.glMatrixMode(GL11.GL_PROJECTION);
            GL11.glLoadIdentity();
            GL11.glOrtho(0, 640, 0, 480, 10, -10);
            GL11.glMatrixMode(GL11.GL_MODELVIEW);
        } catch (LWJGLException lwjgle) {
            Logger.getLogger(Test.class.getName()).log(Level.SEVERE, "LWJGLException when initializing display", lwjgle);
            System.exit(1);
        }
    }
}
