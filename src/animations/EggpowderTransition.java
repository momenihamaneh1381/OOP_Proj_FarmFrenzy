package animations;

import classes.Manager;
import javafx.animation.Transition;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.util.Duration;

public class EggpowderTransition extends Transition {
    Manager manager;
    ImageView imageView;
    public EggpowderTransition(Manager manager , ImageView imageView ) {
        this.manager = manager;
        this.imageView = imageView;
        this.setCycleDuration(Duration.millis(1000));
        this.setCycleCount(8);
    }

    @Override
    protected void interpolate(double v) {
        int i = (int) Math.floor(v*3);
        imageView.setImage(new Image("/source/factory/eggPowderPlant"+i+".png"));
    }
}
