package net.daboross.defender.graphics.hexagonobjects;

import net.daboross.defender.graphics.DColor;
import net.daboross.defender.graphics.FinePoint;
import net.daboross.defender.graphics.GraphicsObject;
import net.daboross.defender.graphics.HexagonStatics;

/**
 *
 * @author daboross
 */
public abstract class HexagonObject implements GraphicsObject {

    protected abstract DColor getDColor();

    protected abstract double getSize();

    public void paintOnScreen(FinePoint point) {
        getDColor().putOnGL();
        HexagonStatics.drawHexagon(point.x, point.y, getSize());
    }
}
