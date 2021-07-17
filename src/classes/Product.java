package classes;

import javafx.scene.image.Image;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;

public class Product extends Rectangle implements Turn{
    String name;
    int centerX;
    int centerY;
    int price;
    int capacity;
    int max_time;
    int time;
    int num = 1;
    Store store = Store.getInstanceStore();
    ProductType productType;

    public int getNum() {
        return num;
    }

    public Product(ProductType productType) {
        name = String.valueOf(productType);
        this.productType = productType;
    }

    public String getName() {
        return name;
    }

    public int getPrice() {
        return price;
    }

    public int getCapacity() {
        return capacity;
    }

    public Product(ProductType productType , int centerx , int centery) {
        super(centerx - 20 , centery - 20 , 40 , 40);
        name = String.valueOf(productType);
        this.centerX = centerx;
        time=0;
        this.centerY=centery;
        this.productType = productType;
        switch (productType){
            case EGG:
                capacity=1;
                max_time =4;
                price=15;
                break;
            case MILK:
                capacity=1;
                max_time =4;
                price=25;
                break;
            case WING:
                capacity=1;
                max_time =4;
                price=20;
                break;
            case POWDER:
                capacity=2;
                max_time =5;
                price=40;
                break;
            case FABRIC:
                capacity=2;
                max_time =5;
                price=50;
                break;
            case POCKET_MILK:
                capacity=2;
                max_time =5;
                price=60;
                break;
            case BREAD:
                capacity=4;
                max_time =6;
                price=80;
                break;
            case CLOTH:
                capacity=4;
                max_time =6;
                price=100;
                break;
            case ICE_CREAM:
                capacity=4;
                max_time =6;
                price=120;
                break;
        }
    }

    public boolean isCollissionWildAnimals(WildAnimal wildAnimal) {
        return this.intersects(wildAnimal.getLayoutBounds());
    }
    public boolean isCollissionCat(Cat cat) {
        return this.intersects(cat.getLayoutBounds());
    }
    @Override
    public void turn() {
        time++;
    }
    public boolean intersect(int x0 , int y0){
        return x0>=(centerX-20)&&x0<=(centerX+20)&&y0>=(centerY-20)&&y0<=(centerY+20);
    }
    public void setBackGround(String url){
        this.setFill(new ImagePattern(new Image(getClass().getResource(url).toExternalForm())));
//        this.setFill;
    }

    public String getProductType() {
        if(productType==ProductType.ICE_CREAM){
            return "icecream";
        }else if (productType==ProductType.POCKET_MILK){
            return "pocketMilk";
        }else
        return productType.toString().toLowerCase();
    }
}
