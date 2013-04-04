package net.daboross.defender.graphics;

/**
 *
 * @author daboross
 */
public class FinePoint implements Cloneable {

    public final double x;
    public final double y;

    public FinePoint() {
        this.x = 0;
        this.y = 0;
    }

    public FinePoint(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public FinePoint(FinePoint screenLocation, double x, double y) {
        this.x = screenLocation.x + x;
        this.y = screenLocation.y + y;
    }

    @Override
    protected Object clone() {
        return new FinePoint(x, y);
    }

    @Override
    public boolean equals(Object other) {
        if (!(other instanceof FinePoint)) {
            return false;
        }
        FinePoint otherFinePoint = (FinePoint) other;
        return this.x == otherFinePoint.x && this.y == otherFinePoint.y;

    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 47 * hash + (int) (Double.doubleToLongBits(this.x) ^ (Double.doubleToLongBits(this.x) >>> 32));
        hash = 47 * hash + (int) (Double.doubleToLongBits(this.y) ^ (Double.doubleToLongBits(this.y) >>> 32));
        return hash;
    }

    @Override
    public String toString() {
        return "[FinePoint] [x:" + x + "] [y:" + y + "]";
    }
}
