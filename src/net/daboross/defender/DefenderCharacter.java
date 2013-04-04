package net.daboross.defender;

import net.daboross.defender.graphics.FinePoint;
import net.daboross.defender.graphics.GraphicsObject;
import net.daboross.defender.graphics.HexagonStatics;
import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.GL11;

/**
 *
 * @author daboross
 */
public class DefenderCharacter implements GraphicsObject, Updatable {

    private static final long longestKeyPress = 500;
    private int hexX;
    private int hexY;
    private long lastWPress;
    private long lastSPress;
    private long lastAPress;
    private long lastDPress;
    private long lastQPress;
    private long lastEPress;
    private boolean lastWPressed;
    private boolean lastSPressed;
    private boolean lastAPressed;
    private boolean lastDPressed;
    private boolean lastQPressed;
    private boolean lastEPressed;

    public DefenderCharacter() {
    }

    public Location getLocation() {
        return new Location(hexX, hexY, 0, 0);
    }

    public void paintOnScreen(FinePoint screenLocation) {
        GL11.glColor3f(0, 0, 1);
        HexagonStatics.drawHexagon(screenLocation.x, screenLocation.y, 30);
    }

    public void update(DefenderMain main) {
        if (isWPressed()) {
            hexY++;
        }
        if (isSPressed()) {
            hexY--;
        }
        if (isAPressed()) {
            if (hexX % 2 == 0) {
                hexX--;
            } else {
                hexX--;
                hexY--;
            }
        }
        if (isQPressed()) {
            if (hexX % 2 == 0) {
                hexX--;
                hexY++;
            } else {
                hexX--;
            }
        }
        if (isDPressed()) {
            if (hexX % 2 == 0) {
                hexX++;
            } else {
                hexX++;
                hexY--;
            }
        }
        if (isEPressed()) {
            if (hexX % 2 == 0) {
                hexX++;
                hexY++;
            } else {
                hexX++;
            }
        }
        Keyboard.next();
    }

    private boolean isWPressed() {
        if (Keyboard.isKeyDown(Keyboard.KEY_W)) {
            if (System.currentTimeMillis() - lastWPress > longestKeyPress || !lastWPressed) {
                lastWPress = System.currentTimeMillis();
                lastWPressed = true;
                return true;
            } else {
                lastWPressed = false;
            }
        }
        return false;
    }

    private boolean isSPressed() {
        if (Keyboard.isKeyDown(Keyboard.KEY_S)) {
            if (System.currentTimeMillis() - lastSPress > longestKeyPress || !lastSPressed) {
                lastSPress = System.currentTimeMillis();
                lastSPressed = true;
                return true;
            } else {
                lastSPressed = false;
            }
        }
        return false;
    }

    private boolean isAPressed() {
        if (Keyboard.isKeyDown(Keyboard.KEY_A)) {
            if (System.currentTimeMillis() - lastAPress > longestKeyPress || !lastAPressed) {
                lastAPress = System.currentTimeMillis();
                lastAPressed = true;
                return true;
            } else {
                lastAPressed = false;
            }
        }
        return false;
    }

    private boolean isDPressed() {
        if (Keyboard.isKeyDown(Keyboard.KEY_D)) {
            if (System.currentTimeMillis() - lastDPress > longestKeyPress || !lastDPressed) {
                lastDPress = System.currentTimeMillis();
                lastDPressed = true;
                return true;
            } else {
                lastDPressed = false;
            }
        }
        return false;
    }

    private boolean isQPressed() {
        if (Keyboard.isKeyDown(Keyboard.KEY_Q)) {
            if (System.currentTimeMillis() - lastQPress > longestKeyPress || !lastQPressed) {
                lastQPress = System.currentTimeMillis();
                lastQPressed = true;
                return true;
            } else {
                lastQPressed = false;
            }
        }
        return false;
    }

    private boolean isEPressed() {
        if (Keyboard.isKeyDown(Keyboard.KEY_E)) {
            if (System.currentTimeMillis() - lastEPress > longestKeyPress || !lastEPressed) {
                lastEPress = System.currentTimeMillis();
                lastEPressed = true;
                return true;
            } else {
                lastEPressed = false;
            }
        }
        return false;
    }
}
