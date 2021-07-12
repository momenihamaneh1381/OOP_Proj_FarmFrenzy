import java.util.ArrayList;
import java.util.Random;

public class DomesticAnimal implements Turn{
    int price;
    int x;
    int y;
    ProductType productType;
    int max_Time;
    int time;
    int live;
    Direction direction;
    AnimalType animalType;
    public DomesticAnimal(int price, int maxTime , ProductType productType , AnimalType animalType) {
        this.price = price;
        this.max_Time = maxTime;
        this.live = 100;
        this.productType = productType;
        Random random = new Random();
        direction = Direction.DOWN.randomDirection();
        x=1+ random.nextInt(6);
        y = 1+ random.nextInt(6);
        time = 0;
        this.animalType = animalType;
    }

    public DomesticAnimal(AnimalType animalType) {
        this.animalType = animalType;
    }


//    public void turn(ArrayList<Grass>grasses) {
//        if (live>0) {
//            live-=10;
//            time++;
//            turn();
//            if (live<=50){
//                int min;
//                ArrayList<Grass>goodGrasses = new ArrayList<>();
//                for (Grass grass : grasses) {
//                    if (grass.x==x||grass.y==y){
//                        if (grass.x==x&&grass.y==y){
//
//                        }else {
//                            goodGrasses.add(grass);
//                        }
//                    }
//                }
//                if (goodGrasses.isEmpty())
//                    turn();
//                else {
//                    for (Grass goodGrass : goodGrasses) {
//                        if (goodGrass.y==y){
//
//                        }
//                    }
//                }
//            }else {
//                turn();
//            }
//        }
//    }
    @Override
    public void turn(){
        if (live>0) {
            live -= 10;
            time++;
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

