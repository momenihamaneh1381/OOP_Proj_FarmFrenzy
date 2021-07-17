package classes;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.ArrayList;
import java.util.HashMap;

public class Tasks {
    public int initialCoins;
    public int goalCoins;
    public int max_time;
    public int prize;
    public ArrayList<WildAnimal> wildAnimals;
    public int[] timeOfWildAnimals;
//    HashMap<WildAnimal , Integer>wildAnimals;
//    HashMap<Product , Integer> product;
//    HashMap<DomesticAnimal , Integer>domesticAnimals;
    public Product goalProduct;
    public int numOfGoalProduct;
    public DomesticAnimal goalDomesticAnimal;
    public int numOfGoalDomesticAnimal;

    public Tasks(int initialCoins, int[] timeOfWildAnimals, int goalCoins, int max_time, int prize) {
        this.initialCoins = initialCoins;
        this.wildAnimals = new ArrayList<>();
        this.timeOfWildAnimals = timeOfWildAnimals;
//        this.goalProducts = new ;
//        this.goalDomesticAnimal = new ArrayList<>();
        this.goalCoins = goalCoins;
        this.max_time = max_time;
        this.prize = prize;
    }

//    public Tasks() {
//
//    }



//    public void save() {
//        GsonBuilder gsonBuilder = new GsonBuilder().setPrettyPrinting();
//        Gson gson = gsonBuilder.create();
//        String userListText = gson.toJson(this);
//
//        FileOperator fileOperator = new FileOperator();
//        fileOperator.write("task1.json", userListText, false);
//    }

}
