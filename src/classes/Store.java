package classes;
import java.util.ArrayList;

public class Store {
    public ArrayList<Product> productsStoreList;
    public ArrayList<WildAnimal> wildAnimalsStoreList;
    final int MAX_CAPACITY = 30;
    public int capacity;


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

    public boolean canAdd(Product product){
        if (MAX_CAPACITY - capacity >= product.capacity)
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
    public int numProduct(ProductType productType){
        int n = 0;
        for (Product product : productsStoreList) {
            if (product.productType==productType)
                n++;
        }
        return n;
    }
    public int numProduct(AnimalType animalType){
        int n = 0;
        for (WildAnimal wildAnimal : wildAnimalsStoreList) {
            if (wildAnimal.animalType==animalType)
                n++;
        }
        return n;
    }
    public WildAnimal getWild(AnimalType animalType){
        for (WildAnimal wildAnimal : wildAnimalsStoreList) {
            if (wildAnimal.animalType==animalType)
                return wildAnimal;
        }
        return null;
    }

}
