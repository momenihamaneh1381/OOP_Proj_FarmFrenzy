import java.util.Random;

public class Cat implements Turn{
    int price;
    int x;
    int y;
    AnimalType animalType;
    Direction direction;
    public Cat() {
        this.price = 150;
        Random random = new Random();
        x=1+ random.nextInt(6);
        y = 1+ random.nextInt(6);
        animalType = AnimalType.CAT;
        direction = Direction.RIGHT.randomDirection();
    }

    @Override
    public void turn() {
        // TODO: 6/17/2021 emtiazi
        if (x == 1 || y == 1 || x == 6 || y == 6) {
            if (x == 1) {
                if (y == 1)
                    direction = Direction.RIGHT;
                else if (y == 6)
                    direction = Direction.UP;
                else
                    direction = direction.randomDirectionExcept(Direction.LEFT);
            } else if (x == 6) {
                if (y == 1)
                    direction = Direction.DOWN;
                else if (y == 6)
                    direction = Direction.LEFT;
                else
                    direction = direction.randomDirectionExcept(Direction.RIGHT);
            } else if (y == 1) {
                direction = direction.randomDirectionExcept(Direction.UP);
            } else if (y == 6) {
                direction = direction.randomDirectionExcept(Direction.DOWN);
            }
        } else {
            direction = direction.randomDirection();
        }
        switch (direction) {
            case UP:
                y--;
                break;
            case DOWN:
                y++;
                break;
            case RIGHT:
                x++;
                break;
            case LEFT:
                x--;
                break;
        }
    }
}

