package net.daboross.defender;

import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import net.daboross.defender.graphics.DColor;
import org.lwjgl.LWJGLException;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;
import org.lwjgl.opengl.GL11;
import net.daboross.defender.graphics.GameGraphics;
import net.daboross.defender.graphics.hexagonobjects.StaticHexagonObject;
import org.lwjgl.opengl.AWTGLCanvas;

/**
 *
 * @author daboross
 */
public class DefenderMain {

    private final AWTGLCanvas parentCanvas;
    private final DefenderThread defThread;
    public final GameGraphics gameGraphics;
    private final JFrame frame;
    private final ScrollListener scrollListener;
    private double currentMovementX;
    private double currentMovementY;
    private List<Updatable> updatables = new ArrayList<Updatable>();

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
        addHexs();
        DefenderCharacter defenderCharacter = new DefenderCharacter(this);
        addUpdatable(defenderCharacter);
        gameGraphics.addObject(defenderCharacter);
    }

    private void addHexs() {
        int i = 5;
        for (int x = -i; x <= i; x++) {
            for (int y = -i; y <= i; y++) {
                gameGraphics.addObject(new StaticHexagonObject(new Location(x, y, currentMovementX, currentMovementY), new DColor(1 - x / (float) i, y / (float) i, 0.5f)));
            }
        }
//        gameGraphics.addObject(new StaticHexagonObject(new Location(0, 0, currentMovementX, currentMovementY), new DColor(1, 0, 0)));
//        gameGraphics.addObject(new StaticHexagonObject(new Location(-1, 0, currentMovementX, currentMovementY), new DColor(0, 1, 0)));
//        gameGraphics.addObject(new StaticHexagonObject(new Location(1, 0, currentMovementX, currentMovementY), new DColor(0, 1, 0)));
//        gameGraphics.addObject(new StaticHexagonObject(new Location(-1, -1, currentMovementX, currentMovementY), new DColor(0, 1, 0)));
//        gameGraphics.addObject(new StaticHexagonObject(new Location(1, -1, currentMovementX, currentMovementY), new DColor(0, 1, 0)));
    }

    protected void runLoop() {
        scrollListener.update();
        this.updateUpdatables();
        gameGraphics.addScroll(currentMovementX, currentMovementY);
        gameGraphics.updateScreen();
    }

    private void updateUpdatables() {
        for (Updatable updatable : updatables) {
            updatable.update(this);
        }
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

    public void addUpdatable(Updatable u) {
        updatables.add(u);
    }
}
