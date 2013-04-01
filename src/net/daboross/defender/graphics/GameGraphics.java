package net.daboross.defender.graphics;

import java.util.ArrayList;
import net.daboross.defender.Location;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.GL11;

/**
 *
 * @author daboross
 */
public class GameGraphics {

    private final int screenX;
    private final int screenY;
    private int scrollX;
    private int scrollY;
    private final ArrayList<GraphicsObject> objectList;

    public GameGraphics(final int screenX, final int screenY) {
        this.screenX = screenX;
        this.screenY = screenY;
        objectList = new ArrayList<GraphicsObject>();
        DefenderGraphicsHelper.setHexagonDistance(100);
        DefenderGraphicsHelper.setScreenSizeX(screenX);
        DefenderGraphicsHelper.setScreenSizeY(screenY);
    }

    public void addScroll(int x, int y) {
        scrollX += x;
        scrollY += y;
    }

    public void addObject(GraphicsObject go) {
        objectList.add(go);
    }

    private void paintGrid() {
        GL11.glBegin(GL11.GL_QUADS);
        GL11.glVertex2i(0, 0);
        GL11.glVertex2i(screenX, 0);
        GL11.glVertex2i(screenX, screenY);
        GL11.glVertex2i(0, screenY);
        GL11.glEnd();
        GL11.glColor3f(0, 0, 0);
        DefenderGraphicsHelper.hexagonGrid(scrollX, scrollY);
    }

    private void paintObjects() {
        for (GraphicsObject o : objectList) {
            Location l = o.getLocation();

        }
    }

    public void updateScreen() {
        GL11.glClear(GL11.GL_COLOR_BUFFER_BIT | GL11.GL_DEPTH_BUFFER_BIT);
        GL11.glColor3f(0.1f, 0.1f, 0.1f);
        paintGrid();
        paintObjects();
        Display.update();
    }
}
