package classes;
import javafx.scene.layout.Pane;

public class DomesticAnimal extends Animal implements Turn{
    int price;
    ProductType productType;
    int max_Time;
    int time;
    int live;
    Direction direction;

    public DomesticAnimal(int price, int maxTime , ProductType productType , AnimalType animalType , Pane parent) {
        super(1 , parent , animalType);
        this.price = price;
        this.max_Time = maxTime;
        this.live = 100;
        this.productType = productType;
        direction = Direction.DOWN.randomDirection();
        time = 0;
    }

    public DomesticAnimal(AnimalType animalType , Pane parent) {
        super(1 , parent , animalType);
    }
    
    public boolean isCollissionGrass(Grass grass){
        return this.intersects(grass.getLayoutBounds());
    }
    public boolean isCollissionWildAnimals(WildAnimal wildAnimal) {
        return this.intersects(wildAnimal.getLayoutBounds());
    }

    @Override
    public void turn(){
        if (live>0) {
            live -= 10;
            time++;
        }
    }


}

