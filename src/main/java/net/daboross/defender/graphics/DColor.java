package net.daboross.defender.graphics;

import org.lwjgl.opengl.GL11;

/**
 *
 * @author daboross
 */
public class DColor {

    private final float floatRed;
    private final float floatGreen;
    private final float floatBlue;

    public DColor() {
        floatRed = 1;
        floatGreen = 1;
        floatBlue = 1;
    }

    public DColor(float floatRed, float floatGreen, float floatBlue) {
        this.floatRed = floatRed;
        this.floatGreen = floatGreen;
        this.floatBlue = floatBlue;
    }

    public void putOnGL() {
        GL11.glColor3f(floatRed, floatGreen, floatBlue);
    }
}
