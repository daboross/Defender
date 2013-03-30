package net.daboross.defender.graphics;

import org.lwjgl.opengl.GL11;

/**
 *
 * @author daboross
 */
public class DefenderGraphicsHelper {

    private static int screenSizeX;
    private static int screenSizeY;
    private static int hexagonDistance;

    public static void setHexagonDistance(int hexagonDistance) {
        DefenderGraphicsHelper.hexagonDistance = hexagonDistance;
    }

    public static void setScreenSizeX(int screenSizeX) {
        DefenderGraphicsHelper.screenSizeX = screenSizeX;
    }

    public static void setScreenSizeY(int screenSizeY) {
        DefenderGraphicsHelper.screenSizeY = screenSizeY;
    }
    private static final double hexagonMultiplier = Math.PI / 3;

    public static void main(String[] args) {
        System.out.println(Math.sin(hexagonMultiplier));
    }

    public static void getHexLocation(int hexX, int hexY, int offSetX, int offSetY) {
    }

    public static void hexagonGrid(int offSetX, int offSetY) {
        int offSetXFinal = offSetX;
        int offSetYFinal = offSetY;
        int xDistance = (int) (Math.sin(hexagonMultiplier) * hexagonDistance);
        int yDistance = hexagonDistance;
        while (offSetXFinal > xDistance * 2) {
            offSetXFinal -= xDistance * 2;
        }
        while (offSetYFinal > yDistance * 2) {
            offSetYFinal -= yDistance * 2;
        }
        int screenX = screenSizeX + xDistance;
        int screenY = screenSizeY + xDistance;
        for (int x = offSetXFinal - xDistance * 2; x <= screenX; x += xDistance) {
            for (int y = (offSetYFinal - yDistance * 2) + ((((x - offSetXFinal) % (xDistance * 2) == 0) ? yDistance / 2 : 0)); y <= screenY; y += yDistance) {
                drawHexagon(x, y, hexagonDistance / 1.8);
            }
        }
    }

    public static void drawHexagon(int x, int y, double size) {
        for (int i = 0; i < 6; i++) {
            GL11.glBegin(GL11.GL_TRIANGLES);
            GL11.glVertex2i(x, y);
            GL11.glVertex2d(x + Math.cos(i * hexagonMultiplier) * size, y + Math.sin(i * hexagonMultiplier) * size);
            GL11.glVertex2d(x + Math.cos((i + 1) * hexagonMultiplier) * size, y + Math.sin((i + 1) * hexagonMultiplier) * size);
            GL11.glEnd();
        }
    }
}