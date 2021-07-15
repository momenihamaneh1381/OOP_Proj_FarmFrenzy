package classes;
import java.util.ArrayList;

public class Task {
    public int initialCoins;
    int goalCoins;
    int max_time;
    int prize;
    ArrayList<WildAnimal> wildAnimals;
    int[] timeOfWildAnimals;
    Product goalProduct;
    int numOfGoalProduct;
    DomesticAnimal goalDomesticAnimal;
    int numOfGoalDomesticAnimal;

    public Task(int initialCoins, int[] timeOfWildAnimals, int goalCoins, int max_time, int prize) {
        this.initialCoins = initialCoins;
        this.wildAnimals = new ArrayList<>();
        this.timeOfWildAnimals = timeOfWildAnimals;
//        this.goalProducts = new ;
//        this.goalDomesticAnimal = new ArrayList<>();
        this.goalCoins = goalCoins;
        this.max_time = max_time;
        this.prize = prize;
    }

}
