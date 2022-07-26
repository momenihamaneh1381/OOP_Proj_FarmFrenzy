package classes;
import java.util.ArrayList;

public class Truck implements Turn{
    private static Truck instance;

    final int MAX_CAPACITY = 15;
    final int MAX_TIME = 10;
    int time;
    public boolean isGo;
    ArrayList<Product> listOfProducts;
    ArrayList<WildAnimal>listOfWildAnimals;
    public int capacity;

    public static Truck getInstance(){
        if (instance==null)
            instance = new Truck();
        return instance;
    }

    private Truck() {
        time=0;
        isGo=false;
        listOfProducts = new ArrayList<>();
        capacity = 0;
        listOfWildAnimals =new ArrayList<>();
    }

    public void transfer(){
    }

    public void add(Product product) {
        listOfProducts.add(product);
        capacity+=product.capacity;
    }
    public void add(WildAnimal wildAnimal){
        listOfWildAnimals.add(wildAnimal);
        capacity+=wildAnimal.capacity;
    }

    public void remove(Product product) {
        listOfProducts.remove(product);
        capacity-=product.capacity;
    }
    public void remove(WildAnimal wildAnimal){
        listOfWildAnimals.remove(wildAnimal);
        capacity-=wildAnimal.capacity;
    }

    @Override
    public void turn() {
        if (isGo)
        time++;
    }

    public int wholeCoins(){
        int n = 0;
        for (Product product : listOfProducts) {
            n+=product.price;
        }
        for (WildAnimal wildAnimal : listOfWildAnimals) {
            n+=wildAnimal.price;
        }

        return n;
    }

    public void clear() {
        listOfWildAnimals.clear();
        listOfProducts.clear();
    }

    public int numProduct(ProductType productType){
        int n = 0;
        for (Product product : listOfProducts) {
            if (product.productType==productType)
                n++;
        }
        return n;
    }
    public int numProduct(AnimalType animalType){
        int n = 0;
        for (WildAnimal wildAnimal : listOfWildAnimals) {
            if (wildAnimal.animalType==animalType)
                n++;
        }
        return n;
    }
    public WildAnimal getWild(AnimalType animalType){
        for (WildAnimal wildAnimal : listOfWildAnimals) {
            if (wildAnimal.animalType==animalType)
                return wildAnimal;
        }
        return null;
    }
}
