package net.daboross.defender.graphics.hexagonobjects;

import net.daboross.defender.Location;
import net.daboross.defender.graphics.DColor;

/**
 *
 * @author daboross
 */
public class StaticHexagonObject extends HexagonObject {

    private final double size;
    private final Location location;
    private final DColor dColor;

    public StaticHexagonObject(Location location, DColor dColor) {
        this.size = 5;
        this.location = location;
        this.dColor = dColor;
    }

    public StaticHexagonObject(double size, Location location, DColor dColor) {
        this.size = size;
        this.location = location;
        this.dColor = dColor;
    }

    @Override
    protected double getSize() {
        return size;
    }

    public Location getLocation() {
        return location;
    }

    @Override
    protected DColor getDColor() {
        return dColor;
    }
}
