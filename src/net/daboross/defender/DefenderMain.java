package net.daboross.defender;

import java.awt.Canvas;
import java.awt.Component;
import java.awt.Container;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.lwjgl.LWJGLException;
import org.lwjgl.opengl.Display;

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

    protected void beforeRun() {
        initDisplay();
    }

    protected void runLoop() {
        Display.sync(60);
        Display.update();
    }

    protected void afterRun() {
        Display.destroy();
    }

    private void initDisplay() {
        try {
            Display.setParent(parent_canvas);
            Display.create();
        } catch (LWJGLException e) {
        }
    }
}
