package net.daboross.defender;

import java.awt.Canvas;
import java.awt.Container;
import java.awt.Point;
import javax.swing.JFrame;
import org.lwjgl.LWJGLException;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;
import org.lwjgl.opengl.GL11;
import static net.daboross.defender.DefenderGraphicsHelper.drawHexagon;

/**
 *
 * @author daboross
 */
public class DefenderMain {

    private final Canvas parent_canvas;
    private final DefenderThread defThread;
    private final JFrame frame;

    /**
     *
     * @param parent_canvas
     */
    public DefenderMain(JFrame frame) {
        if (frame == null) {
            throw new IllegalArgumentException();
        }
        parent_canvas = new Canvas();
        frame.add(parent_canvas);
        defThread = new DefenderThread(this);
        this.frame = frame;
    }

    public void start() {
        defThread.start();
    }

    protected void beforeRun() {
        initDisplay();
    }

    private int getScreenX() {
        return parent_canvas.getWidth();
    }

    private int getScreenY() {
        return parent_canvas.getHeight();
    }

    protected void runLoop() {
        GL11.glClear(GL11.GL_COLOR_BUFFER_BIT | GL11.GL_DEPTH_BUFFER_BIT);
        GL11.glColor3f(0.5f, 0.5f, 1.0f);
        int distance = 30;
        int screenX = getScreenX() + distance;
        int screenY = getScreenY() + distance;
        for (int x = 0; x <= screenX; x += distance) {
            for (int y = ((x % 4 == 0) ? (distance / 2) : 0); y <= screenY; y += distance) {
                drawHexagon(new Point(x, y), distance / 2);
            }
        }
        drawHexagon(new Point(), 3);
        Display.update();
    }

    protected void afterRun() {
        Display.destroy();
    }

    protected boolean isDone() {
        return Display.isCloseRequested();
    }

    private void initDisplay() {
        try {
            Display.setParent(parent_canvas);
            Display.setDisplayMode(new DisplayMode(640, 480));
            Display.create();
            GL11.glMatrixMode(GL11.GL_PROJECTION);
            GL11.glLoadIdentity();
            GL11.glOrtho(0, 640, 0, 480, 10, -10);
            GL11.glMatrixMode(GL11.GL_MODELVIEW);
        } catch (LWJGLException e) {
            System.exit(1);
        }
    }
}
