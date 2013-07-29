package net.daboross.defender;

import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;

/**
 *
 * @author daboross
 */
public class ScrollListener implements Updatable {

    private static final int MOUSE_BUTTON = 0;
    public static final int CENTER_BUTTON = 1;
    private boolean pressedLast;
    private int mouseXLast;
    private int mouseYLast;

    public ScrollListener() {
    }

    public void update(DefenderMain main) {
        if (Mouse.isButtonDown(MOUSE_BUTTON)) {
            if (pressedLast) {
                if (!Keyboard.isKeyDown(Keyboard.KEY_X)) {
                    main.gameGraphics.addScroll(Mouse.getX() - mouseXLast, Mouse.getY() - mouseYLast);
                }
                mouseXLast = Mouse.getX();
                mouseYLast = Mouse.getY();
            } else {
                mouseXLast = Mouse.getX();
                mouseYLast = Mouse.getY();
                pressedLast = true;
            }
        } else {
            if (pressedLast) {
                if (!Keyboard.isKeyDown(Keyboard.KEY_X)) {
                    main.gameGraphics.addScroll(Mouse.getX() - mouseXLast, Mouse.getY() - mouseYLast);
                }
                pressedLast = false;
            }
        }
    }
}
