package net.daboross.defender.graphics;

import org.lwjgl.opengl.GL11;

/**
 *
 * @author daboross
 */
public class HexagonStatics {

    public static final double hexagonMultiplier = Math.PI / 3;
    public static final double hexagonDistance = 100;
    public static final double xDistance = (int) (Math.sin(hexagonMultiplier) * hexagonDistance);
    //Don't know why I need a cast to integer, but it is really weird if I don't have it.
    public static final double yDistance = hexagonDistance;
    public static final double hexagonSize = hexagonDistance / 1.8;

    public static void drawHexagon(double x, double y, double size) {
        for (int i = 0; i < 6; i++) {
            GL11.glBegin(GL11.GL_TRIANGLES);
            GL11.glVertex2d(x, y);
            GL11.glVertex2d(x + Math.cos(i * HexagonStatics.hexagonMultiplier) * size, y + Math.sin(i * HexagonStatics.hexagonMultiplier) * size);
            GL11.glVertex2d(x + Math.cos((i + 1) * HexagonStatics.hexagonMultiplier) * size, y + Math.sin((i + 1) * HexagonStatics.hexagonMultiplier) * size);
            GL11.glEnd();
        }
    }
}
