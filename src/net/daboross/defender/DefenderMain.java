package net.daboross.defender;

import net.daboross.defender.graphics.DefenderGraphicsHelper;
import java.awt.Canvas;
import java.awt.Point;
import javax.swing.JFrame;
import org.lwjgl.LWJGLException;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;
import org.lwjgl.opengl.GL11;
import static net.daboross.defender.graphics.DefenderGraphicsHelper.drawHexagon;
import net.daboross.defender.graphics.GameGraphics;

/**
 *
 * @author daboross
 */
public class DefenderMain {

    private final Canvas parent_canvas;
    private final DefenderThread defThread;
    private final JFrame frame;
    private final GameGraphics gameGraphics;

    /**
     *
     * @param parent_canvas
     */
    public DefenderMain(final JFrame frame) {
        if (frame == null) {
            throw new IllegalArgumentException();
        }
        parent_canvas = new Canvas();
        frame.add(parent_canvas);
        defThread = new DefenderThread(this);
        this.frame = frame;
        this.gameGraphics = new GameGraphics(getScreenX(), getScreenY());
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
    int x;

    protected void runLoop() {
        x++;
        gameGraphics.addScroll(x, x);
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
