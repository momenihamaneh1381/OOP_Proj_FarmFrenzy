package classes;
import javafx.scene.layout.Pane;

import java.util.Random;

public class Hound extends Animal implements Turn{
    int price;
//    int x;
//    int y;
    Direction direction;

    public Hound(Pane pane) {
        super(1 , pane , AnimalType.HOUND);
        this.price = 100;
//        Random random = new Random();
//        x=1+ random.nextInt(6);
//        y = 1+ random.nextInt(6);
        direction = Direction.RIGHT.randomDirection();
    }

    public boolean isCollissionWildAnimals(WildAnimal wildAnimal) {
        return this.intersects(wildAnimal.getLayoutBounds());
    }
    @Override
    public void turn() {
        // TODO: 6/17/2021 emtiazi
//        if (x == 1 || y == 1 || x == 6 || y == 6) {
//            if (x == 1) {
//                if (y == 1)
//                    direction = Direction.RIGHT;
//                else if (y == 6)
//                    direction = Direction.UP;
//                else
//                    direction = direction.randomDirectionExcept(Direction.LEFT);
//            } else if (x == 6) {
//                if (y == 1)
//                    direction = Direction.DOWN;
//                else if (y == 6)
//                    direction = Direction.LEFT;
//                else
//                    direction = direction.randomDirectionExcept(Direction.RIGHT);
//            } else if (y == 1) {
//                direction = direction.randomDirectionExcept(Direction.UP);
//            } else if (y == 6) {
//                direction = direction.randomDirectionExcept(Direction.DOWN);
//            }
//        } else {
//            direction = direction.randomDirection();
//        }
//        switch (direction) {
//            case UP:
//                y--;
//                break;
//            case DOWN:
//                y++;
//                break;
//            case RIGHT:
//                x++;
//                break;
//            case LEFT:
//                x--;
//                break;
//        }
    }
}
