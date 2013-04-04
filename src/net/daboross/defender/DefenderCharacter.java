package net.daboross.defender;

import net.daboross.defender.graphics.FinePoint;
import net.daboross.defender.graphics.GameGraphics;
import net.daboross.defender.graphics.GraphicsObject;
import net.daboross.defender.graphics.HexagonStatics;
import org.lwjgl.input.Keyboard;
import org.lwjgl.opengl.GL11;

/**
 *
 * @author daboross
 */
public class DefenderCharacter implements GraphicsObject, Updatable {

    private final DefenderMain main;
    private final GameGraphics gameGraphics;
    private static final long longestTime = 500;
    private int hexX;
    private int hexY;
    private long lastUpdate;
    private long lastWPress;
    private long lastSPress;
    private long lastAPress;
    private long lastQPress;
    private long lastEPress;

    public DefenderCharacter(DefenderMain main) {
        this.main = main;
        this.gameGraphics = main.gameGraphics;
    }

    public Location getLocation() {
        return new Location(hexX, hexY, 0, 0);
    }

    public void paintOnScreen(FinePoint screenLocation) {
        GL11.glColor3f(0, 0, 1);
        HexagonStatics.drawHexagon(screenLocation.x, screenLocation.y, 30);
    }

    public void update(DefenderMain main) {
        long time = System.currentTimeMillis();
        if (time - lastUpdate > longestTime) {
            lastUpdate = time;
            if (Keyboard.isKeyDown(Keyboard.KEY_W)) {
                hexY++;
            }
            if (Keyboard.isKeyDown(Keyboard.KEY_S)) {
                hexY--;
            }
            if (Keyboard.isKeyDown(Keyboard.KEY_A)) {
                if (hexX % 2 == 0) {
                    hexX--;
                } else {
                    hexX--;
                    hexY--;
                }
            }
            if (Keyboard.isKeyDown(Keyboard.KEY_Q)) {
                if (hexX % 2 == 0) {
                    hexX--;
                    hexY++;
                } else {
                    hexX--;
                }
            }
            if (Keyboard.isKeyDown(Keyboard.KEY_D)) {
                if (hexX % 2 == 0) {
                    hexX++;
                } else {
                    hexX++;
                    hexY--;
                }
            }
            if (Keyboard.isKeyDown(Keyboard.KEY_E)) {
                if (hexX % 2 == 0) {
                    hexX++;
                    hexY++;
                } else {
                    hexX++;
                }
            }
        }
    }

    private boolean isWPressed() {
        return false;
    }

    private boolean isSPressed() {
        return false;
    }

    private boolean isAPressed() {
        return false;
    }

    private boolean isDPressed() {
        return false;
    }

    private boolean isQPressed() {
        return false;
    }

    private boolean isEPressed() {
        return false;
    }
}
