package net.daboross.defender.graphics;

import java.nio.ByteBuffer;
import net.daboross.defender.Location;

/**
 *
 * @author daboross
 */
public abstract class GraphicsObject {

    public abstract Location getLocation();

    public abstract ByteBuffer getImage();
}
