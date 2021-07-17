package classes;

public class Step {
    public String goalProductName;
    public int goalProductNum;
    public String goalDomesticAnimalName;
    public int goalDomesticAnimalNum;
    public String []wildNames;
    public int []wildNum;
    public int initCoin;
    public int prize;
    public int maxTime;
    public int goalCoin;

    public Step( ) {
    }

    public Step(String goalProductName, int goalProductNum, String goalDomesticAnimalName, int goalDomesticAnimalNum,
                String[] wildNames, int[] wildNum, int initCoin, int prize, int maxTima, int goalCoin) {
        this.goalProductName = goalProductName;
        this.goalProductNum = goalProductNum;
        this.goalDomesticAnimalName = goalDomesticAnimalName;
        this.goalDomesticAnimalNum = goalDomesticAnimalNum;
        this.wildNames = wildNames;
        this.wildNum = wildNum;
        this.initCoin = initCoin;
        this.prize = prize;
        this.maxTime = maxTima;
        this.goalCoin = goalCoin;
    }
}
