package classes;
import java.util.ArrayList;

public class Store {
    ArrayList<Product> productsStoreList;
    ArrayList<WildAnimal> wildAnimalsStoreList;
    final int MAX_CAPACITY = 30;
    int capacity;


    //singleton design
    private static Store instance = null;
    private Store() {
        productsStoreList = new ArrayList<>();
        wildAnimalsStoreList = new ArrayList<>();
    }
    public static Store getInstanceStore() {
        if(instance == null) {
            instance = new Store();
        }
        return instance;
    }
    public boolean canAdd(int num){
        if (MAX_CAPACITY - capacity >= num)
            return true;
        return false;
    }

    public boolean canAdd(WildAnimal wildAnimal){
        if (MAX_CAPACITY - capacity >= wildAnimal.capacity)
            return true;
        return false;
    }

    public void add(Product product) {
        productsStoreList.add(product);
        capacity+=product.capacity;
    }
    public void add(WildAnimal wildAnimal){
        wildAnimalsStoreList.add(wildAnimal);
        capacity+=wildAnimal.capacity;
    }
    public void remove(Product product) {
        productsStoreList.remove(product);
        capacity-=product.capacity;
    }
    public void remove(WildAnimal wildAnimal){
        wildAnimalsStoreList.remove(wildAnimal);
        capacity-=wildAnimal.capacity;
    }
}
