package gdd.sprite;

import javax.swing.ImageIcon;
import static gdd.Global.*;

public class Explosion extends Sprite {

    private int visibleFrames = 10;

    public Explosion(int x, int y) {
        initExplosion(x, y);
    }

    private void initExplosion(int x, int y) {
        var ii = new ImageIcon(IMG_EXPLOSION); // Make sure IMG_EXPLOSION is defined in Global.java

        var scaledImage = ii.getImage().getScaledInstance(
            ii.getIconWidth() * SCALE_FACTOR,
            ii.getIconHeight() * SCALE_FACTOR,
            java.awt.Image.SCALE_SMOOTH
        );

        setImage(scaledImage);
        setX(x);
        setY(y);
        setVisible(true);
    }

    public void visibleCountDown() {
        if (visibleFrames > 0) {
            visibleFrames--;
        } else {
            setVisible(false);
        }
    }
}