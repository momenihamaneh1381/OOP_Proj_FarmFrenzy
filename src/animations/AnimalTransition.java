package animations;

import classes.*;
import graphicPackage.Game;
import javafx.animation.Transition;
import javafx.scene.control.Label;
import javafx.util.Duration;

import java.io.IOException;
import java.util.Random;

public class AnimalTransition extends Transition {
    Manager manager;
    Label lowCoinLabel;
    Label lowWellLabel , storeSuccess , storeFail;
    Game game;


    public AnimalTransition(Manager manager , Label label  , Label lowWellLabel , Label storeSuccess ,
                            Label storeFail , Game game) {
        this.setCycleDuration(Duration.millis(2000));
        this.setCycleCount(-1);
        this.game = game;
        this.storeFail = storeFail;
        this.storeSuccess = storeSuccess;
        this.manager = manager;
        lowCoinLabel = label;
        this.lowWellLabel = lowWellLabel;
    }

    @Override
    protected void interpolate(double v) {
            for (Product product : manager.products) {
                product.setBackGround("/source/products/" + product.getProductType() + ".png");
            }
            for (Grass grass : manager.grasses) {
                grass.setBackGround("/source/products/grass.png");
            }
            for (Animal animal : manager.domesticAnimals) {
                double dx, dy;
                dx = animal.getSpeed() * Math.cos(Math.toRadians(animal.getTheta()));
                dy = (-1) * animal.getSpeed() * Math.sin(Math.toRadians(animal.getTheta()));
                animal.move(dx, dy);
                if (animal.hitFloor())
                    animal.setTheta(360 - animal.getTheta());
                if (animal.hitTopWall())
                    animal.setTheta(-animal.getTheta());
                if (animal.hitRightWall() || animal.hitLeftWall())
                    animal.setTheta(180 - animal.getTheta());
                transition(v, animal);

            }
            for (Animal animal : manager.cats) {
                double dx, dy;
                dx = animal.getSpeed() * Math.cos(Math.toRadians(animal.getTheta()));
                dy = (-1) * animal.getSpeed() * Math.sin(Math.toRadians(animal.getTheta()));
                animal.move(dx, dy);
                if (animal.hitFloor())
                    animal.setTheta(360 - animal.getTheta());
                if (animal.hitTopWall())
                    animal.setTheta(-animal.getTheta());
                if (animal.hitRightWall() || animal.hitLeftWall())
                    animal.setTheta(180 - animal.getTheta());
                transition(v, animal);
            }
            for (Animal animal : manager.hounds) {
                double dx, dy;
                dx = animal.getSpeed() * Math.cos(Math.toRadians(animal.getTheta()));
                dy = (-1) * animal.getSpeed() * Math.sin(Math.toRadians(animal.getTheta()));
                animal.move(dx, dy);
                if (animal.hitFloor())
                    animal.setTheta(360 - animal.getTheta());
                if (animal.hitTopWall())
                    animal.setTheta(-animal.getTheta());
                if (animal.hitRightWall() || animal.hitLeftWall())
                    animal.setTheta(180 - animal.getTheta());
                transition(v, animal);

            }
            for (WildAnimal animal : manager.wildAnimals) {
                if (!animal.caged()) {
                    double dx, dy;
                    dx = animal.getSpeed() * Math.cos(Math.toRadians(animal.getTheta()));
                    dy = (-1) * animal.getSpeed() * Math.sin(Math.toRadians(animal.getTheta()));
                    animal.move(dx, dy);
                    if (animal.hitFloor())
                        animal.setTheta(randTheta(0, 180));
                    if (animal.hitTopWall())
                        animal.setTheta(randTheta(180, 360));
                    if (animal.hitRightWall() || animal.hitLeftWall())
                        animal.setTheta(180 - animal.getTheta());
                    transition(v, animal);
                } else {
                    animal.setBackGround("/source/animals/caged" + animal.getAnimalType() + ".png");
                }
            }

            if (v == 1) {
                lowCoinLabel.setText("");
                lowWellLabel.setText("");
                storeSuccess.setText("");
                storeFail.setText("");
                if (manager.turn(1)){
                }else {
                    try {
                        System.out.println("level complete!");
                        game.end();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }

    }
    private void transition(double v , Animal animal){
        int i = (int) Math.floor(v*5);
        animal.setBackGround("/source/animals/"+animal.getAnimalType()+i+".png");
    }
    private int randTheta(int min , int max){
        Random random = new Random();
        int n = 30+ min+ random.nextInt(max-min -60 );
        return n;
    }

}
