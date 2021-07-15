package classes;
import java.util.ArrayList;

public class Truck implements Turn{
    private static Truck instance;

    final int MAX_CAPACITY = 15;
    final int MAX_TIME = 10;
    int time;
    boolean isGo;
    ArrayList<Product> listOfProducts;
    ArrayList<WildAnimal>listOfWildAnimals;
    int capacity;

    // TODO: 6/18/2021 domesticAnimals beacase have not any capacity

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
        // TODO: 6/14/2021
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
}
