package net.daboross.defender;

import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.Display;

/**
 *
 * @author daboross
 */
public class ScrollListener {

    private static final int edgeDistance = 50;
    private static final int move = 10;
    private final DefenderMain main;

    public ScrollListener(final DefenderMain main) {
        this.main = main;
    }

    public void update() {
        xCheck(Mouse.getX());
        yCheck(Mouse.getY());
    }

    private void xCheck(final int mouseX) {
        int leftBound = edgeDistance;
        int rightBound = Display.getWidth() - edgeDistance;
        if (mouseX < leftBound) {
            main.setCurrentMovementX((leftBound - mouseX) / 5);
        } else if (mouseX > rightBound) {
            main.setCurrentMovementX((rightBound - mouseX) / 5);
        } else {
            main.setCurrentMovementX(0);
        }
    }

    private void yCheck(final int mouseY) {
        int topBound = edgeDistance;
        int bottomBound = Display.getHeight() - edgeDistance;
        if (mouseY < topBound) {
            main.setCurrentMovementY((topBound - mouseY) / 5);
        } else if (mouseY > bottomBound) {
            main.setCurrentMovementY((bottomBound - mouseY) / 5);
        } else {
            main.setCurrentMovementY(0);
        }
    }
}
