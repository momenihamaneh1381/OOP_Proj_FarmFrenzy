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

    public String getAnimalType() {
        String n = "";
        switch (animalType){
            case CAT :
                n="cat";
                break;
            case HEN:
                n="hen";
                break;
            case HOUND:
                n="hound";
                break;
            case TIGER :
                n="tiger";
                break;
            case TURKEY :
                n="turkey";
                break;
            case BEAR:
                n="bear";
                break;
            case BUFFALO :
                n="buffalo";
                break;
            case LION :
                n="lion";
                break;

        }
        return n;
    }

    public AnimalType animalType;

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
        x =61+  random.nextInt(360);
        y =61+  random.nextInt(210);
//        super.setX(x-20);
//        super.setY(y-20);
//        super.setWidth(40);
//        super.setHeight(40);
        super.setX(x-30);
        super.setY(y-30);
        super.setWidth(60);
        super.setHeight(60);
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
