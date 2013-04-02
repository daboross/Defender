package net.daboross.defender.graphics;

/**
 *
 * @author daboross
 */
public class HexagonStatics {

    public static final double hexagonMultiplier = Math.PI / 3;
    public static final double hexagonDistance = 100;
    public static final double xDistance = (int) (Math.sin(hexagonMultiplier) * hexagonDistance);
    //Don't know why I need a cast to integer, but it is really weird if I don't have it.
    public static final double yDistance = hexagonDistance;
    public static final double hexagonSize = hexagonDistance / 1.8;
}