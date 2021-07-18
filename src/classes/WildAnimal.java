package classes;

import javafx.scene.layout.Pane;


public class WildAnimal extends Animal implements Turn{
    int max_cage;
    int cage;
    int capacity;
    int price;
    int max_time;
    int time;
    Direction direction;

    public WildAnimal(int max_cage, int speed,int price , AnimalType animalType , Pane parent) {
        super(speed ,parent , animalType);
        this.max_cage = max_cage;
        this.price = price;
        capacity = 15;
        max_time =5;
        cage =0;
        time = 0;
        direction = Direction.DOWN.randomDirection();
    }

    public WildAnimal(AnimalType animalType ,Pane parent) {
        super(0 , parent , animalType);
        switch (animalType){
            case BEAR:
                super.speed=1;
                price =400;
                max_cage =4;
                break;
            case LION:
                super.speed=1;
                price = 300;
                max_cage =3;
                break;
            case TIGER:
                super.speed=2;
                price = 500;
                max_cage =4;
                break;
        }
        this.capacity = 15;
        max_time =5;
        cage =0;
        time = 0;
        direction = Direction.DOWN.randomDirection();
        this.animalType = animalType;
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
        }
    }

    public boolean intersect(int x0 , int y0){
        return x0>=(x-30)&&x0<=(x+30)&&y0>=(y-30)&&y0<=(y+30);
    }
}
