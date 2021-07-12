import java.util.Random;

public enum Direction {
    RIGHT,
    LEFT,
    UP,
    DOWN;
    public Direction randomDirection(){
        Random random = new Random();
        int n =1+ random.nextInt(4);
        if (n==1)
            return RIGHT;
        else if (n==2)
            return LEFT;
        else if (n==3)
            return UP;
        else if (n==4)
            return DOWN;
        else return UP;
    }
    public Direction randomDirectionExcept(Direction direction1){
        Random random = new Random();
        Direction direction = randomDirection();
        int n ;
        while (direction==direction1){
            n =1+ random.nextInt(4);
        if (n==1)
            direction= RIGHT;
        else if (n==2)
            direction= LEFT;
        else if (n==3)
            direction= UP;
        else if (n==4)
            direction= DOWN;
        }
        return direction;
    }
}
