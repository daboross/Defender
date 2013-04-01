package net.daboross.defender;

import java.awt.Canvas;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import javax.swing.JPanel;
import org.lwjgl.LWJGLException;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;
import org.lwjgl.opengl.GL11;
import net.daboross.defender.graphics.GameGraphics;
import org.lwjgl.opengl.AWTGLCanvas;

/**
 *
 * @author daboross
 */
public class DefenderMain {

    private final AWTGLCanvas parentCanvas;
    private final DefenderThread defThread;
    private final GameGraphics gameGraphics;
    private final JFrame frame;
    private int currentMovementX;
    private int currentMovementY;

    /**
     *
     * @param parent_canvas
     */
    public DefenderMain(final JFrame frame) {
        if (frame == null) {
            throw new IllegalArgumentException();
        }
        AWTGLCanvas parentCanvasTemp = null;
        try {
            parentCanvasTemp = new AWTGLCanvas();
        } catch (LWJGLException lwjgle) {
            Logger.getLogger(DefenderMain.class.getName()).log(Level.SEVERE, "LWJGLException while creating canvas", lwjgle);
            System.exit(1);
        }
        parentCanvas = parentCanvasTemp;
        frame.getContentPane().add(parentCanvas);
        defThread = new DefenderThread(this);
        this.gameGraphics = new GameGraphics(frame.getWidth(), frame.getHeight());
        this.frame = frame;
    }

    public void start() {
        defThread.start();
    }

    protected void beforeRun() {
        initDisplay();
        parentCanvas.addMouseMotionListener(new MouseMotionListener() {
            public void mouseDragged(MouseEvent e) {
                System.out.println("dragged");
            }

            public void mouseMoved(MouseEvent e) {
                System.out.println("moved");
            }
        });
    }

    protected void runLoop() {
        gameGraphics.addScroll(currentMovementX, currentMovementY);
        gameGraphics.updateScreen();
    }

    protected void afterRun() {
        Display.destroy();
    }

    protected boolean isDone() {
        return Display.isCloseRequested();
    }

    private void initDisplay() {
        try {
            Display.setParent(parentCanvas);
            Display.setDisplayMode(new DisplayMode(640, 480));
            Display.create();
            GL11.glMatrixMode(GL11.GL_PROJECTION);
            GL11.glLoadIdentity();
            GL11.glOrtho(0, 640, 0, 480, 10, -10);
            GL11.glMatrixMode(GL11.GL_MODELVIEW);
        } catch (LWJGLException lwjgle) {
            Logger.getLogger(DefenderMain.class.getName()).log(Level.SEVERE, "LWJGLException when initializing display", lwjgle);
            System.exit(1);
        }
    }

    public void setCurrentMovement(final int x, final int y) {
        this.currentMovementX = x;
        this.currentMovementY = y;
    }

    public void setCurrentMovementX(final int x) {
        this.currentMovementX = x;
    }

    public void setCurrentMovementY(final int y) {
        this.currentMovementY = y;
    }

    public int getScreenWidth() {
        return frame.getWidth();
    }

    public int getScreenHeight() {
        return frame.getHeight();
    }
}
