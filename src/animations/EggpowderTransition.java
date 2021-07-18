package animations;

import classes.Manager;
import javafx.animation.Transition;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.util.Duration;

public class EggpowderTransition extends Transition {
    public static int level;
    ImageView imageView;
    public EggpowderTransition(int level , ImageView imageView ) {
        this.level = level;
        this.imageView = imageView;
        this.setCycleDuration(Duration.millis(1000));
        this.setCycleCount(8);
    }

    @Override
    protected void interpolate(double v) {
        int i = (int) Math.floor(v*5);
        imageView.setImage(new Image("/source/factory/eggPowderPlant"+level+i+".png"));
    }

}
