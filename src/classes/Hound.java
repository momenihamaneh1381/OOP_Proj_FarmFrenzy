package classes;
import javafx.scene.layout.Pane;

import java.util.Random;

public class Hound extends Animal implements Turn{
    int price;
    Direction direction;

    public Hound(Pane pane) {
        super(1 , pane , AnimalType.HOUND);
        this.price = 100;
        direction = Direction.RIGHT.randomDirection();
    }

    public boolean isCollissionWildAnimals(WildAnimal wildAnimal) {
        return this.intersects(wildAnimal.getLayoutBounds());
    }
    @Override
    public void turn() {
    }
}
