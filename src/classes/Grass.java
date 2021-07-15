package classes;

import javafx.scene.shape.Rectangle;

public class Grass extends Rectangle {
    int centerX;
    int centerY;

    public Grass(int x, int y) {
        super(x-13 , y-8 , 26 , 16);
        this.centerX = x;
        this.centerY = y;
    }
}
