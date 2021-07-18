package animations;

import javafx.animation.Transition;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.util.Duration;

public class PocketTransition extends Transition {
    public static int level;
    ImageView imageView;

    public PocketTransition(ImageView imageView , int level) {
        this.imageView = imageView;
        this.level = level;
        this.setCycleDuration(Duration.millis(1000));
        this.setCycleCount(12);
    }

    @Override
    protected void interpolate(double v) {
        int i = (int) Math.floor(v*5);
        imageView.setImage(new Image("/source/factory/"+level+i+".png"));
    }
}
