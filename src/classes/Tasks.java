package classes;
import java.util.ArrayList;

public class Tasks {
    public int initialCoins;
    public int goalCoins;
    public int max_time;
    public int prize;
    public ArrayList<WildAnimal> wildAnimals;
    public int[] timeOfWildAnimals;
    public Product goalProduct;
    public int numOfGoalProduct;
    public DomesticAnimal goalDomesticAnimal;
    public int numOfGoalDomesticAnimal;

    public Tasks(int initialCoins, int[] timeOfWildAnimals, int goalCoins, int max_time, int prize) {
        this.initialCoins = initialCoins;
        this.wildAnimals = new ArrayList<>();
        this.timeOfWildAnimals = timeOfWildAnimals;
        this.goalCoins = goalCoins;
        this.max_time = max_time;
        this.prize = prize;
    }
}
