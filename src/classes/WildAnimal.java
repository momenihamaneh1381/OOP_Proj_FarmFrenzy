package classes;

import java.util.Random;

public class WildAnimal implements Turn{
    int x;
    int y;
    int max_cage;
    int cage;
    int speed;
    int capacity;
    int price;
    int max_time;
    int time;
    Direction direction;
    AnimalType animalType;

    public WildAnimal(int max_cage, int speed,int price , AnimalType animalType) {
        this.max_cage = max_cage;
        this.speed = speed;
        this.price = price;
        capacity = 15;
        max_time =5;
        cage =0;
        time = 0;
        Random random  = new Random();
        x = 1+random.nextInt(6);
        y = 1+random.nextInt(6);
        direction = Direction.DOWN.randomDirection();
        this.animalType = animalType;
    }

    public WildAnimal(AnimalType animalType) {
        this.animalType = animalType;
        capacity = 15;
        max_time =5;
        cage =0;
        time = 0;
        Random random  = new Random();
        x = 1+random.nextInt(6);
        y = 1+random.nextInt(6);
        direction = Direction.DOWN.randomDirection();
        this.animalType = animalType;
        switch (animalType){
            case BEAR:
                speed =1;
                price =400;
                max_cage =4;
                break;
            case LION:
                speed =1;
                price = 300;
                max_cage =3;
                break;
            case TIGER:
                speed =2;
                price = 500;
                max_cage =4;
                break;
        }
    }

    public boolean caged(){
        if (max_cage<=cage)
            return true;
        return false;
    }
    @Override
    public void turn() {
        if (caged()){
            time++;
        }else {
            if(cage>0)
            cage--; // TODO: 6/18/2021 doubt
            for (int i = 0; i < speed; i++) {
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
    }
}
