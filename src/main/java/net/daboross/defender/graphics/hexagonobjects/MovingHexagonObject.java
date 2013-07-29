package net.daboross.defender.graphics.hexagonobjects;

import net.daboross.defender.Location;
import net.daboross.defender.graphics.DColor;

/**
 *
 * @author daboross
 */
public class MovingHexagonObject extends HexagonObject {

    private final double size;
    private final Location location;
    private final double speed;
    private final DColor dColor;

    public MovingHexagonObject(double size, Location location, double speed, DColor dColor) {
        this.size = size;
        this.location = location;
        this.speed = speed / 5;
        this.dColor = dColor;
    }

    @Override
    protected double getSize() {
        return size;
    }
    private double x = 15;
    private double y = 15;
    private boolean b;
    private boolean a;
    private boolean c;
    private int change;

    public Location getLocation() {
        change++;
        if (change > 200) {
            if (c) {
                a = !a;
            } else {
                b = !b;
            }
            c = !c;
            change = 0;
        }
        x += b ? speed : -speed;
        y += a ? speed : -speed;
        return new Location(location, 5, 5, x, y);
    }

    @Override
    protected DColor getDColor() {
        return dColor;
    }
}
