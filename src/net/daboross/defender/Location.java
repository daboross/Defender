package net.daboross.defender;

/**
 *
 * @author daboross
 */
public class Location implements Cloneable {

    public final int hexX;
    public final int hexY;
    public final double driftX;
    public final double driftY;

    public Location() {
        this.hexX = 0;
        this.hexY = 0;
        this.driftX = 0;
        this.driftY = 0;
    }

    public Location(final int hexX, final int hexY, final double driftX, final double driftY) {
        this.hexX = hexX;
        this.hexY = hexY;
        this.driftX = driftX;
        this.driftY = driftY;
    }

    public Location(final Location location, final int hexX, final int hexY, final double driftX, final double driftY) {
        this.hexX = location.hexX + hexX;
        this.hexY = location.hexY + hexY;
        this.driftX = location.driftX + driftX;
        this.driftY = location.driftY + driftY;
    }

    @Override
    protected Object clone() {
        return new Location(hexX, hexY, driftX, driftY);
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof Location)) {
            return false;
        }
        Location l = (Location) obj;
        return l.hexX == hexX && l.hexY == hexY && l.driftX == driftX && l.driftY == driftY;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 53 * hash + this.hexX;
        hash = 53 * hash + this.hexY;
        hash = 53 * hash + (int) (Double.doubleToLongBits(this.driftX) ^ (Double.doubleToLongBits(this.driftX) >>> 32));
        hash = 53 * hash + (int) (Double.doubleToLongBits(this.driftY) ^ (Double.doubleToLongBits(this.driftY) >>> 32));
        return hash;
    }
}
