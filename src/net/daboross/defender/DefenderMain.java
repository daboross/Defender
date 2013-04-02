package net.daboross.defender;

import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
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
    private final ScrollListener scrollListener;
    private double currentMovementX;
    private double currentMovementY;

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
        this.parentCanvas = parentCanvasTemp;
        frame.getContentPane().add(parentCanvas);
        this.defThread = new DefenderThread(this);
        this.gameGraphics = new GameGraphics();
        this.frame = frame;
        this.scrollListener = new ScrollListener(this);
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
        scrollListener.update();
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

    public void setCurrentMovement(final double x, final double y) {
        this.currentMovementX = x;
        this.currentMovementY = y;
    }

    public void setCurrentMovementX(final double x) {
        this.currentMovementX = x;
    }

    public void setCurrentMovementY(final double y) {
        this.currentMovementY = y;
    }

    public int getScreenWidth() {
        return frame.getWidth();
    }

    public int getScreenHeight() {
        return frame.getHeight();
    }
}
