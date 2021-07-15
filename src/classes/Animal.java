package classes;

import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;

import java.util.Random;

public class Animal extends Rectangle {
    private Pane parent;
    int speed;
    int theta;
    double x ;
    double y;
    AnimalType animalType;

    public String getUrl() {
        // TODO: 7/14/2021
//        return url;
        return "/source/hen.jpg";
    }

    String url;

    public int getSpeed() {
        return speed;
    }

    public int getTheta() {
        return theta;
    }

    public Animal(int speed  ,Pane parent , AnimalType animalType) {
        super();
        this.parent = parent;
        this.animalType = animalType;
        this.speed = speed;
        Random random =  new Random();
        theta = random.nextInt(360);
//        theta = 45;
//        x= 0;
//        y =0;
        x =1+  random.nextInt(421);
        y =1+  random.nextInt(268);
        super.setX(x-20);
        super.setY(y-20);
        super.setWidth(40);
        super.setHeight(40);
    }

    public void setTheta(int theta) {
        this.theta = theta;
    }

    public void move(double dx, double dy) {
        x +=dx;
        y +=dy;
        this.setX(this.getX() + dx);
        this.setY(this.getY() + dy);
    }

    public boolean hitTopWall(){
        return this.getY() <= 0;
    }
    public boolean hitFloor(){
        return this.getY() + this.getHeight() >= 270;
    }
    public boolean hitRightWall(){
        return this.getX() + this.getWidth()>=423; // TODO: 7/14/2021
    }
    public boolean hitLeftWall(){
        return this.getX() <= 0;
    }

    public void setBackGround(String url){
        this.setFill(new ImagePattern(new Image(getClass().getResource(url).toExternalForm())));

//        this.setFill;
    }
}
