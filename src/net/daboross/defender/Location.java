package net.daboross.defender;

/**
 *
 * @author daboross
 */
public class Location implements Cloneable {

    private final int hexX;
    private final int hexY;
    private final double driftX;
    private final double driftY;

    public Location(final int hexX, final int hexY, final double driftX, final double driftY) {
        this.hexX = hexX;
        this.hexY = hexY;
        this.driftX = driftX;
        this.driftY = driftY;
    }

    public int hexX() {
        return hexX;
    }

    public int hexY() {
        return hexY;
    }

    public double driftX() {
        return driftX;
    }

    public double driftY() {
        return driftY;
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
