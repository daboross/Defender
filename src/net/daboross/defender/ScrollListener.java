package net.daboross.defender;

import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;

/**
 *
 * @author daboross
 */
public class ScrollListener implements MouseMotionListener {

    private static final int edgeDistance = 20;
    private static final int move = 1;
    private final DefenderMain main;

    public ScrollListener(final DefenderMain main) {
        this.main = main;
    }

    public void mouseDragged(final MouseEvent e) {
        System.out.println("drag");
//        mouseMoved(e);
    }

    public void mouseMoved(final MouseEvent e) {
        System.out.println("move");
//        xCheck(e.getX());
//        yCheck(e.getY());
    }

    private void xCheck(final int mouseX) {
        if (mouseX < edgeDistance) {
            main.setCurrentMovementX(-move);
        } else if (mouseX > main.getScreenWidth() - edgeDistance) {
            main.setCurrentMovementX(move);
        }
    }

    private void yCheck(final int mouseY) {
        if (mouseY < edgeDistance) {
            main.setCurrentMovementY(move);
        } else if (mouseY > main.getScreenHeight() - edgeDistance) {
            main.setCurrentMovementX(-move);
        }
    }
}
