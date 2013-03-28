package net.daboross.defender;

import java.awt.Point;
import org.lwjgl.opengl.GL11;

/**
 *
 * @author daboross
 */
public class DefenderGraphicsHelper {

    private static final double hexagonMultiplier = Math.PI / 3;

    public static void drawHexagon(Point location, double size) {
        int x = location.x;
        int y = location.y;
        for (int i = 0; i < 6; i++) {
            GL11.glBegin(GL11.GL_TRIANGLES);
            GL11.glVertex2i(x, y);
            GL11.glVertex2d(x + Math.cos(i * hexagonMultiplier) * size, y + Math.sin(i * hexagonMultiplier) * size);
            GL11.glVertex2d(x + Math.cos((i + 1) * hexagonMultiplier) * size, y + Math.sin((i + 1) * hexagonMultiplier) * size);
            GL11.glEnd();
        }
    }
}