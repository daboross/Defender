package net.daboross.defender;

import java.awt.Canvas;
import java.awt.Container;
import java.awt.Point;
import org.lwjgl.LWJGLException;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;
import org.lwjgl.opengl.GL11;

/**
 *
 * @author daboross
 */
public class DefenderMain {

    private final Canvas parent_canvas;
    private final DefenderThread defThread;

    /**
     *
     * @param parent_canvas
     */
    public DefenderMain(Container c) {
        if (c == null) {
            throw new IllegalArgumentException();
        }
        parent_canvas = new Canvas();
        c.add(parent_canvas);
        defThread = new DefenderThread(this);
    }

    public void start() {
        defThread.start();
    }

    protected void beforeRun() {
        initDisplay();
    }

    protected void runLoop() {
        GL11.glClear(GL11.GL_COLOR_BUFFER_BIT | GL11.GL_DEPTH_BUFFER_BIT);
        GL11.glColor3f(0.5f, 0.5f, 1.0f);
        GL11.glBegin(GL11.GL_QUADS);
        Point center = new Point(320, 240);
        double angle = 0;
        for (int i = 0; i < 12; i++) {
            GL11.glVertex2d(center.x + Math.sin(angle) * 200, center.y + Math.cos(angle) * 200);
            angle += Math.PI / 3;
        }
        GL11.glEnd();
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
            GL11.glOrtho(0, 640, 0, 480, 1, -1);
            GL11.glMatrixMode(GL11.GL_MODELVIEW);
        } catch (LWJGLException e) {
            System.exit(1);
        }
    }
}
