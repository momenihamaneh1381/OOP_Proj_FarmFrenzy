package classes;
import javafx.scene.layout.Pane;

public class Cat extends Animal implements Turn{
    int price;
    Direction direction;
    public Cat(Pane pane) {
        super(1 , pane , AnimalType.CAT);
        this.price = 150;
        direction = Direction.RIGHT.randomDirection();
    }

    @Override
    public void turn() {
    }
}

