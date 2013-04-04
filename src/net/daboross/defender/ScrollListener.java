package net.daboross.defender;

import org.lwjgl.input.Mouse;

/**
 *
 * @author daboross
 */
public class ScrollListener {

    private static final int MOUSE_BUTTON = 0;
    private final DefenderMain main;
    private boolean pressedLast;
    private int mouseXLast;
    private int mouseYLast;

    public ScrollListener(final DefenderMain main) {
        this.main = main;
    }

    public void update() {
        if (Mouse.isButtonDown(MOUSE_BUTTON)) {
            if (pressedLast) {
                main.gameGraphics.addScroll(Mouse.getX() - mouseXLast, Mouse.getY() - mouseYLast);
                mouseXLast = Mouse.getX();
                mouseYLast = Mouse.getY();
            } else {
                mouseXLast = Mouse.getX();
                mouseYLast = Mouse.getY();
                pressedLast = true;
            }
        } else {
            if (pressedLast) {
                main.gameGraphics.addScroll(Mouse.getX() - mouseXLast, Mouse.getY() - mouseYLast);
                pressedLast = false;
            }
        }
    }
}
