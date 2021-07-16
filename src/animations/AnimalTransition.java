package animations;

import classes.*;
import javafx.animation.Transition;
import javafx.scene.control.Label;
import javafx.util.Duration;

import java.util.Random;

public class AnimalTransition extends Transition {
//    ArrayList<Animal>animals;
    Manager manager;
    Label lowCoinLabel;
    Label lowWellLabel , storeSuccess , storeFail;



//    public AnimalTransition(ArrayList<Animal> animals ) {
//        this.setCycleDuration(Duration.millis(2000));
//        this.setCycleCount(-1);
//        this.animals = animals;
////        this.manager = manager;
//    }

    public AnimalTransition(Manager manager , Label label  , Label lowWellLabel , Label storeSuccess ,Label storeFail) {
        this.setCycleDuration(Duration.millis(2000));
        this.setCycleCount(-1);
        this.storeFail = storeFail;
        this.storeSuccess = storeSuccess;
//        this.animals = animals;
        this.manager = manager;
        lowCoinLabel = label;
        this.lowWellLabel = lowWellLabel;
    }

    @Override
    protected void interpolate(double v) {
        for (Animal animal : manager.domesticAnimals) {
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
            transition(v , animal);

        }
        for (Animal animal : manager.cats) {
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
            transition(v , animal);
        }
        for (Animal animal : manager.hounds) {
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
            transition(v , animal);

        }
        for (Animal animal : manager.wildAnimals) {
            double dx , dy;
            dx = animal.getSpeed()*Math.cos(Math.toRadians(animal.getTheta()));
            dy = (-1)*animal.getSpeed()*Math.sin(Math.toRadians(animal.getTheta()));
            animal.move(dx , dy);
            if (animal.hitFloor())
                animal.setTheta(randTheta(0 , 180));
//                animal.setTheta(360 - animal.getTheta());
            if (animal.hitTopWall())
                animal.setTheta(randTheta(180 , 360));
//                animal.setTheta( - animal.getTheta());
            if (animal.hitRightWall()||animal.hitLeftWall())
                animal.setTheta(180 - animal.getTheta());
            transition(v , animal);

        }

        if (v==1){
            lowCoinLabel.setText("");
            lowWellLabel.setText("");
            storeSuccess.setText("");
            storeFail.setText("");
            manager.turn(1 );
//            manager.turn(1 , );
        }
    }
    private void transition(double v , Animal animal){
        int i = (int) Math.floor(v*5);
        animal.setBackGround("/source/animals/hen1"+i+".png");
//        animal.setBackGround("/source/"+animal.getAnimalType()+i+".jpg");
        // TODO: 7/15/2021  
    }
    private int randTheta(int min , int max){
        Random random = new Random();
        int n = 10+ min+ random.nextInt(max-min -20 );
        return n;
    }

}
