package net.daboross.defender;

import net.daboross.defender.graphics.FinePoint;
import net.daboross.defender.graphics.GraphicsObject;
import net.daboross.defender.graphics.HexagonStatics;
import org.lwjgl.input.Keyboard;
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

    public void paintOnScreen(FinePoint point) {
        GL11.glColor3f(0, 0, 1);
        HexagonStatics.drawHexagon(point.x, point.y, 30);
    }

    public void update(DefenderMain main) {
        int moveX = 0;
        int moveY = 0;
        boolean wsPressed = false;
        if (isWPressed()) {
            moveY++;
            wsPressed = true;
        }
        if (isSPressed()) {
            moveY--;
            wsPressed = true;
        }
        if (isAPressed()) {
            moveX--;
            if (hexX % 2 != 0) {
                if (!wsPressed) {
                    moveY--;
                }
            }
        }
        if (isQPressed()) {
            moveX--;
            if (!wsPressed && hexX % 2 == 0) {
                moveY++;
            }
        }
        if (isDPressed()) {
            moveX++;
            if (!wsPressed && hexX % 2 != 0) {
                moveY--;
            }
        }
        if (isEPressed()) {
            moveX++;
            if (!wsPressed && hexX % 2 == 0) {
                moveY++;
            }
        }
        if (moveX > 1) {
            moveX = 1;
        }
        if (moveX < -1) {
            moveX = -1;
        }
        if (moveY > 1) {
            moveY = 1;
        }
        if (moveY < -1) {
            moveY = -1;
        }
        hexX += moveX;
        hexY += moveY;
        if (Keyboard.isKeyDown(Keyboard.KEY_X)) {
            main.gameGraphics.centerOn(this.getLocation());
        }
    }

    private boolean isWPressed() {
        if (Keyboard.isKeyDown(Keyboard.KEY_W)) {
            if (System.currentTimeMillis() - lastWPress > longestKeyPress || !lastWPressed) {
                lastWPress = System.currentTimeMillis();
                lastWPressed = true;
                return true;
            }
        } else {
            lastWPressed = false;
        }
        return false;
    }

    private boolean isSPressed() {
        if (Keyboard.isKeyDown(Keyboard.KEY_S)) {
            if (System.currentTimeMillis() - lastSPress > longestKeyPress || !lastSPressed) {
                lastSPress = System.currentTimeMillis();
                lastSPressed = true;
                return true;
            }
        } else {
            lastSPressed = false;
        }
        return false;
    }

    private boolean isAPressed() {
        if (Keyboard.isKeyDown(Keyboard.KEY_A)) {
            if (System.currentTimeMillis() - lastAPress > longestKeyPress || !lastAPressed) {
                lastAPress = System.currentTimeMillis();
                lastAPressed = true;
                return true;
            }
        } else {
            lastAPressed = false;
        }
        return false;
    }

    private boolean isDPressed() {
        if (Keyboard.isKeyDown(Keyboard.KEY_D)) {
            if (System.currentTimeMillis() - lastDPress > longestKeyPress || !lastDPressed) {
                lastDPress = System.currentTimeMillis();
                lastDPressed = true;
                return true;
            }
        } else {
            lastDPressed = false;
        }
        return false;
    }

    private boolean isQPressed() {
        if (Keyboard.isKeyDown(Keyboard.KEY_Q)) {
            if (System.currentTimeMillis() - lastQPress > longestKeyPress || !lastQPressed) {
                lastQPress = System.currentTimeMillis();
                lastQPressed = true;
                return true;
            }
        } else {
            lastQPressed = false;
        }
        return false;
    }

    private boolean isEPressed() {
        if (Keyboard.isKeyDown(Keyboard.KEY_E)) {
            if (System.currentTimeMillis() - lastEPress > longestKeyPress || !lastEPressed) {
                lastEPress = System.currentTimeMillis();
                lastEPressed = true;
                return true;
            }
        } else {
            lastEPressed = false;
        }
        return false;
    }
}
