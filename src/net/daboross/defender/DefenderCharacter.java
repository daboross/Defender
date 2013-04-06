package net.daboross.defender;

import java.util.logging.Level;
import java.util.logging.Logger;
import net.daboross.defender.graphics.FinePoint;
import net.daboross.defender.graphics.GraphicsObject;
import net.daboross.defender.graphics.HexagonStatics;
import org.lwjgl.input.Keyboard;
import org.lwjgl.opengl.GL11;
import static org.lwjgl.input.Keyboard.isKeyDown;

/**
 *
 * @author daboross
 */
public class DefenderCharacter implements GraphicsObject, Updatable {

    private static final Logger L = Logger.getLogger(DefenderCharacter.class.getSimpleName());
    private static final int HEX_MOVE_NONE = 0;
    private static final int HEX_MOVE_UP = 1;
    private static final int HEX_MOVE_RIGHT_UP = 2;
    private static final int HEX_MOVE_RIGHT_DOWN = 3;
    private static final int HEX_MOVE_DOWN = 4;
    private static final int HEX_MOVE_LEFT_UP = 5;
    private static final int HEX_MOVE_LEFT_DOWN = 6;
    private static final int STARTING_HEX_X = 0;
    private static final int STARTING_HEX_Y = 0;
    private int hexX = STARTING_HEX_X;
    private int hexY = STARTING_HEX_Y;
    private int currentHexMove = HEX_MOVE_NONE;
    private boolean moving = false;

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
        if (!moving) {
            currentHexMove = HEX_MOVE_NONE;
            if (isKeyDown(Keyboard.KEY_W)) {
                setMove(HEX_MOVE_UP);
            }
            if (isKeyDown(Keyboard.KEY_S)) {
                setMove(HEX_MOVE_DOWN);
            }
            if (isKeyDown(Keyboard.KEY_A)) {
                setMove(HEX_MOVE_LEFT_DOWN);
            }
            if (isKeyDown(Keyboard.KEY_Q)) {
                setMove(HEX_MOVE_LEFT_UP);
            }
            if (isKeyDown(Keyboard.KEY_D)) {
                setMove(HEX_MOVE_RIGHT_DOWN);
            }
            if (isKeyDown(Keyboard.KEY_E)) {
                setMove(HEX_MOVE_RIGHT_UP);
            }
        }
        refreshMove();
        if (Keyboard.isKeyDown(Keyboard.KEY_X)) {
            main.gameGraphics.centerOn(this.getLocation());
        }
    }

    public void setMove(int hexMove) {
        switch (hexMove) {
            case HEX_MOVE_NONE:
                currentHexMove = HEX_MOVE_NONE;
                break;

            case HEX_MOVE_UP:
                switch (currentHexMove) {
                    case HEX_MOVE_NONE:
                        currentHexMove = HEX_MOVE_UP;
                        break;
                    case HEX_MOVE_UP:
                        break;
                    case HEX_MOVE_RIGHT_UP:
                        break;
                    case HEX_MOVE_RIGHT_DOWN:
                        break;
                    case HEX_MOVE_DOWN:
                        currentHexMove = HEX_MOVE_NONE;
                        break;
                    case HEX_MOVE_LEFT_DOWN:
                        break;
                    case HEX_MOVE_LEFT_UP:
                        break;
                }
                break;

            case HEX_MOVE_RIGHT_UP:
                switch (currentHexMove) {
                    case HEX_MOVE_NONE:
                        currentHexMove = HEX_MOVE_RIGHT_UP;
                        break;
                    case HEX_MOVE_UP:
                        currentHexMove = HEX_MOVE_RIGHT_UP;
                        break;
                    case HEX_MOVE_RIGHT_UP:
                        break;
                    case HEX_MOVE_RIGHT_DOWN:
                        currentHexMove = HEX_MOVE_RIGHT_UP;
                        break;
                    case HEX_MOVE_DOWN:
                        currentHexMove = HEX_MOVE_RIGHT_DOWN;
                        break;
                    case HEX_MOVE_LEFT_DOWN:
                        currentHexMove = HEX_MOVE_NONE;
                        break;
                    case HEX_MOVE_LEFT_UP:
                        currentHexMove = HEX_MOVE_UP;
                        break;
                }
                break;

            case HEX_MOVE_RIGHT_DOWN:
                switch (currentHexMove) {
                    case HEX_MOVE_NONE:
                        currentHexMove = HEX_MOVE_RIGHT_DOWN;
                        break;
                    case HEX_MOVE_DOWN:
                        currentHexMove = HEX_MOVE_RIGHT_DOWN;
                        break;
                    case HEX_MOVE_LEFT_DOWN:
                        currentHexMove = HEX_MOVE_DOWN;
                        break;
                    case HEX_MOVE_LEFT_UP:
                        currentHexMove = HEX_MOVE_NONE;
                        break;
                    case HEX_MOVE_UP:
                        currentHexMove = HEX_MOVE_RIGHT_UP;
                        break;
                    case HEX_MOVE_RIGHT_UP:
                        break;
                }
                break;

            case HEX_MOVE_DOWN:
                switch (currentHexMove) {
                    case HEX_MOVE_NONE:
                        currentHexMove = HEX_MOVE_DOWN;
                        break;
                    case HEX_MOVE_UP:
                        currentHexMove = HEX_MOVE_NONE;
                        break;
                    case HEX_MOVE_RIGHT_UP:
                        break;
                    case HEX_MOVE_RIGHT_DOWN:
                        break;
                    case HEX_MOVE_DOWN:
                        break;
                    case HEX_MOVE_LEFT_DOWN:
                        break;
                    case HEX_MOVE_LEFT_UP:
                        break;
                }
                break;

            case HEX_MOVE_LEFT_DOWN:
                switch (currentHexMove) {
                    case HEX_MOVE_NONE:
                        currentHexMove = HEX_MOVE_LEFT_DOWN;
                        break;
                    case HEX_MOVE_UP:
                        currentHexMove = HEX_MOVE_LEFT_UP;
                        break;
                    case HEX_MOVE_RIGHT_UP:
                        currentHexMove = HEX_MOVE_NONE;
                        break;
                    case HEX_MOVE_RIGHT_DOWN:
                        currentHexMove = HEX_MOVE_DOWN;
                        break;
                    case HEX_MOVE_DOWN:
                        currentHexMove = HEX_MOVE_LEFT_DOWN;
                        break;
                    case HEX_MOVE_LEFT_DOWN:
                        break;
                    case HEX_MOVE_LEFT_UP:
                        break;
                }
                break;

            case HEX_MOVE_LEFT_UP:
                switch (currentHexMove) {
                    case HEX_MOVE_NONE:
                        currentHexMove = HEX_MOVE_LEFT_UP;
                        break;
                    case HEX_MOVE_UP:
                        currentHexMove = HEX_MOVE_LEFT_UP;
                        break;
                    case HEX_MOVE_RIGHT_UP:
                        currentHexMove = HEX_MOVE_UP;
                        break;
                    case HEX_MOVE_RIGHT_DOWN:
                        currentHexMove = HEX_MOVE_NONE;
                        break;
                    case HEX_MOVE_DOWN:
                        currentHexMove = HEX_MOVE_LEFT_DOWN;
                        break;
                    case HEX_MOVE_LEFT_DOWN:
                        currentHexMove = HEX_MOVE_LEFT_UP;
                        break;
                    case HEX_MOVE_LEFT_UP:
                        break;
                }
                break;

            default:
                L.log(Level.INFO, "hex move called with unknown constant:{0}", hexMove);
                break;
        }
    }

    private void refreshMove() {
        if (moving) {
            if (currentHexMove == HEX_MOVE_NONE) {
                moving = false;
                return;
            }
            moving = false;
        } else {
            if (currentHexMove != HEX_MOVE_NONE) {
            }
        }
    }
}
