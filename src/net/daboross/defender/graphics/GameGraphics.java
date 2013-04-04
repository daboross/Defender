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

    /**
     * This is the horizontal scroll amount.
     */
    private double scrollX;
    /**
     * This is the vertical scroll amount.
     */
    private double scrollY;
    /**
     * This is the horizontal scroll amount used in the hexagon grid. It is the
     * same as the scrollX%200.
     */
    private double gridScrollX;
    /**
     * This is the horizontal scroll amount used in the hexagon grid. It is the
     * same as the scrollY%200.
     */
    private double gridScrollY;
    private final ArrayList<GraphicsObject> objectList;

    public GameGraphics() {
        objectList = new ArrayList<GraphicsObject>();
    }

    public void addScroll(final double x, final double y) {
        scrollX += x;
        scrollY += y;
        gridScrollX = scrollX % (HexagonStatics.xDistance * 2);
        gridScrollY = scrollY % (HexagonStatics.yDistance * 2);
    }

    public void addObject(GraphicsObject go) {
        objectList.add(go);
    }

    private void paintGrid() {
        GL11.glColor3f(0.5f, 0.5f, 0.5f);
        GameGraphics.clearScreen();
        GL11.glColor3f(0, 0, 0);
        hexGrid();
    }

    private void paintObjects() {
        for (GraphicsObject o : objectList) {
            Location l = o.getLocation();
            FinePoint hexCenter = hexLocation(l.hexX, l.hexY);
            FinePoint finalLocation = new FinePoint(hexCenter, l.driftX, l.driftY);
            o.paintOnScreen(finalLocation);
        }
    }

    public void updateScreen() {
        GL11.glClear(GL11.GL_COLOR_BUFFER_BIT | GL11.GL_DEPTH_BUFFER_BIT);
        paintGrid();
        paintObjects();
        Display.update();
    }

    private static void clearScreen() {
        GL11.glBegin(GL11.GL_QUADS);
        GL11.glVertex2i(0, 0);
        GL11.glVertex2i(Display.getWidth(), 0);
        GL11.glVertex2i(Display.getWidth(), Display.getHeight());
        GL11.glVertex2i(0, Display.getHeight());
        GL11.glEnd();
    }

    private void hexGrid() {
        double screenX = Display.getWidth() + HexagonStatics.xDistance;
        double screenY = Display.getHeight() + HexagonStatics.yDistance;
        for (double x = gridScrollX - HexagonStatics.xDistance * 2; x <= screenX; x += HexagonStatics.xDistance) {
            for (double y = (gridScrollY - HexagonStatics.yDistance * 2) + ((((x - gridScrollX) % (HexagonStatics.xDistance * 2) == 0) ? HexagonStatics.yDistance / 2 : 0)); y <= screenY; y += HexagonStatics.yDistance) {
                drawHexagon(x, y);
            }
        }
    }

    private FinePoint hexLocation(int hexX, int hexY) {
        double x = scrollX + HexagonStatics.xDistance * hexX;
        double y = scrollY + (hexX % 2 == 0 ? HexagonStatics.yDistance / 2 : 0) + hexY * HexagonStatics.yDistance;
        return new FinePoint(x, y);
    }

    private static void drawHexagon(double x, double y) {
        for (int i = 0; i < 6; i++) {
            GL11.glBegin(GL11.GL_TRIANGLES);
            GL11.glVertex2d(x, y);
            GL11.glVertex2d(x + Math.cos(i * HexagonStatics.hexagonMultiplier) * HexagonStatics.hexagonSize, y + Math.sin(i * HexagonStatics.hexagonMultiplier) * HexagonStatics.hexagonSize);
            GL11.glVertex2d(x + Math.cos((i + 1) * HexagonStatics.hexagonMultiplier) * HexagonStatics.hexagonSize, y + Math.sin((i + 1) * HexagonStatics.hexagonMultiplier) * HexagonStatics.hexagonSize);
            GL11.glEnd();
        }
    }
}
