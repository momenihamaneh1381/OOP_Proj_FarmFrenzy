package animations;

import classes.Animal;
import classes.DomesticAnimal;
import classes.Manager;
import classes.WildAnimal;
import javafx.animation.Transition;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;

import java.util.ArrayList;

public class AnimalTransition extends Transition {
    ArrayList<Animal>animals;
//    Manager manager;


    public AnimalTransition(ArrayList<Animal> animals ) {
        this.setCycleDuration(Duration.millis(2000));
        this.setCycleCount(-1);
        this.animals = animals;
//        this.manager = manager;
    }

    @Override
    protected void interpolate(double v) {
        for (Animal animal : animals) {
            double dx , dy;
            dx = animal.getSpeed()*Math.cos(Math.toRadians(animal.getTheta()));
            dy = (-1)*animal.getSpeed()*Math.sin(Math.toRadians(animal.getTheta()));
            animal.move(dx , dy);
            if (animal.hitFloor())
                animal.setTheta(360 - animal.getTheta());
            if (animal.hitTopWall())
                animal.setTheta( - animal.getTheta());
            if (animal.hitRightWall()||animal.hitLeftWall())
                animal.setTheta(180 - animal.getTheta());
            henTr(v , animal);

        }
//        if (v==1){
////            manager.turn(1 , );
//        }
    }
    private void henTr(double v , Animal animal){
        int i = (int) Math.floor(v*5);
        animal.setBackGround("/source/hen"+i+".jpg");
    }

}
