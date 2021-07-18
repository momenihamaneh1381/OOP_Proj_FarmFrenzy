package classes;

import javafx.scene.image.Image;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;

public class Grass extends Rectangle {
    int centerX;
    int centerY;

    public Grass(int x, int y) {
        super(x-15 , y-15 , 30 , 30);
        this.centerX = x;
        this.centerY = y;
    }
    public void setBackGround(String url){
        this.setFill(new ImagePattern(new Image(getClass().getResource(url).toExternalForm())));
    }
}
