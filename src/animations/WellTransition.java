package animations;

import javafx.animation.Transition;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.util.Duration;

public class WellTransition extends Transition {
    ImageView imageView;
    public boolean isPause;

    public WellTransition(ImageView imageView) {
        this.imageView = imageView;
        this.setCycleDuration(Duration.millis(1000));
        this.setCycleCount(6);
        isPause = false;
    }


    @Override
    protected void interpolate(double v) {
        int i = (int) Math.floor(v*5);
        imageView.setImage(new Image("/source/factory/well"+i+".png"));
    }
}
