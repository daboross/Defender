package net.daboross.defender.graphics;

import net.daboross.defender.Location;

/**
 *
 * @author daboross
 */
public interface GraphicsObject {

    public Location getLocation();

    public void paintOnScreen(FinePoint screenLocation);
}
