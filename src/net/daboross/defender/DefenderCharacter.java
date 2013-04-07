package net.daboross.defender;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.logging.ConsoleHandler;
import java.util.logging.Formatter;
import java.util.logging.Handler;
import java.util.logging.Level;
import java.util.logging.LogRecord;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;
import net.daboross.defender.graphics.FinePoint;
import net.daboross.defender.graphics.GraphicsObject;
import net.daboross.defender.graphics.HexagonStatics;
import org.lwjgl.input.Keyboard;
import org.lwjgl.opengl.GL11;
import static org.lwjgl.input.Keyboard.isKeyDown;
import org.omg.Dynamic.Parameter;

/**
 *
 * @author daboross
 */
public class DefenderCharacter implements GraphicsObject, Updatable {

    private static final Logger L = Logger.getLogger(DefenderCharacter.class.getSimpleName());

    static {
        Handler handler = new ConsoleHandler();
        handler.setLevel(Level.ALL);
        handler.setFormatter(new SimpleFormatter() {
            @Override
            public String format(LogRecord record) {
                System.out.println("LGO:" + record.getMessage());
                return super.format(record);
            }
        });
        L.addHandler(handler);

    }
    private static final int SPEED = 20;
    private static final int BEFORE_MOVE_WAIT = 3;
    private static final int HEX_MOVE_NONE = 0;
    private static final int HEX_MOVE_UP = 1;
    private static final int HEX_MOVE_RIGHT_UP = 2;
    private static final int HEX_MOVE_RIGHT_DOWN = 3;
    private static final int HEX_MOVE_DOWN = 4;
    private static final int HEX_MOVE_LEFT_UP = 5;
    private static final int HEX_MOVE_LEFT_DOWN = 6;
    private static final int STARTING_HEX_X = 0;
    private static final int STARTING_HEX_Y = 0;
    private int hexX = STARTING_HEX_X;//This should not be changed outside of changeMoveIntoHexMove()
    private int hexY = STARTING_HEX_Y;//This should not be changed outside of changeMoveIntoHexMove()
    private int offsetMoveX = 0;
    private int offsetMoveY = 0;
    private int currentHexMove = HEX_MOVE_NONE;
    private boolean moving = false;//This should not be changed outside of refreshMove()
    private int movePercentage;//This should not be changed outside of refreshMove()
    private int waitTime;//This should not be changed outside of refreshMove()

    private static String getMoveName(int hexMove) {
        switch (hexMove) {
            case HEX_MOVE_UP:
                return "HEX MOVE UP";
            case HEX_MOVE_RIGHT_UP:
                return "HEX MOVE RIGHT UP";
            case HEX_MOVE_RIGHT_DOWN:
                return "HEX MOVE RIGHT DOWN";
            case HEX_MOVE_DOWN:
                return "HEX MOVE DOWN";
            case HEX_MOVE_LEFT_DOWN:
                return "HEX MOVE LEFT DOWN";
            case HEX_MOVE_LEFT_UP:
                return "HEX MOVE LEFT UP";
            default:
                return "Unknown: \"" + hexMove + "\"";
        }
    }

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
            if (movePercentage >= 100) {
                changeMoveIntoHexMove();
                stopMove();
                return;
            }
            changeOffSetMove();
            movePercentage += SPEED;
        } else {
            if (currentHexMove != HEX_MOVE_NONE) {
                if (waitTime >= BEFORE_MOVE_WAIT) {
                    waitTime = 0;
                    moving = true;
                    L.log(Level.INFO, "Hex Move: {0}", getMoveName(currentHexMove));
                } else {
                    waitTime++;
                }
            }
        }
    }

    /**
     * This should NOT be called outside of refreshMove()
     */
    private void changeOffSetMove() {
        switch (currentHexMove) {
            case HEX_MOVE_UP:
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
    }

    /**
     * This should NOT be called outside of refreshMove()
     */
    private void changeMoveIntoHexMove() {
        switch (currentHexMove) {
            case HEX_MOVE_NONE:
                break;
            case HEX_MOVE_UP:
                hexY++;
                break;
            case HEX_MOVE_RIGHT_UP:
                if (hexX % 2 == 0) {
                    hexY++;
                }
                hexX++;
                break;
            case HEX_MOVE_RIGHT_DOWN:
                if (hexX % 2 != 0) {
                    hexY--;
                }
                hexX++;
                break;
            case HEX_MOVE_DOWN:
                hexY--;
                break;
            case HEX_MOVE_LEFT_DOWN:
                if (hexX % 2 != 0) {
                    hexY--;
                }
                hexX--;
                break;
            case HEX_MOVE_LEFT_UP:
                if (hexX % 2 == 0) {
                    hexY++;
                }
                hexX--;
                break;
        }
    }

    /**
     * This should not be changed outside of refreshMove()
     */
    private void stopMove() {
        moving = false;
        movePercentage = 0;
        currentHexMove = HEX_MOVE_NONE;
    }
}
