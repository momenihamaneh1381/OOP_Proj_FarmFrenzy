package classes;
import com.google.gson.Gson;

import java.util.ArrayList;

public class Missions {
    public int levels;
    public Task[] tasks ;

    public Missions(int levels) {
        this.levels = levels;
        this.tasks = new Task[levels];
//        for (int i = 0; i < levels; i++) {
//            tasks[i]  = new Task();
//        }

//        int []a= {4 , 7};
//        int []b = {5};
//        Bear bear = new Bear();
//        Tiger tiger  = new Tiger();
//        tasks[0] = new Task(100 , a , 500 , 10 , 100);
//        tasks[0].wildAnimals.add(bear);
//        tasks[0].wildAnimals.add(tiger);
//        tasks[0].goalProduct = new Product(ProductType.EGG );
//        tasks[0].numOfGoalProduct = 5;
//        tasks[0].goalDomesticAnimal = new DomesticAnimal(AnimalType.TURKEY);
//        tasks[0].numOfGoalDomesticAnimal = 2;
//
//        tasks[1]= new Task(150 , b ,0 , 15 , 150 );
//        tasks[1].wildAnimals.add(bear);
//        tasks[1].goalDomesticAnimal = new DomesticAnimal(AnimalType.HEN);
//        tasks[1].numOfGoalDomesticAnimal = 1;
//
//        tasks[2] = new Task(100 , null , 500 , 10 , 100);
//        tasks[2].goalProduct = new Product(ProductType.MILK );
//        tasks[2].numOfGoalProduct = 5;
//        tasks[2].goalDomesticAnimal = new DomesticAnimal(AnimalType.BUFFALO);
//        tasks[2].numOfGoalDomesticAnimal = 2;
//
//        tasks[3] = new Task(100 , b , 500 , 10 , 100);
//        tasks[3].wildAnimals.add(tiger);
//        tasks[3].goalProduct = new Product(ProductType.ICE_CREAM );
//        tasks[3].numOfGoalProduct = 1;
//
//        tasks[4] = new Task(100 , a , 500 , 10 , 100);
//        tasks[4].wildAnimals.add(bear);
//        tasks[4].wildAnimals.add(new Lion());


    }

//    private void missionRead(Missions missions){
//        FileOperator fileOperator = new FileOperator();
//        String json =  fileOperator.read("missions.json");
//        Gson gson = new Gson();
//
//        // convert JSON string to Mission object
//        missions = gson.fromJson(json, Missions.class);
//    }

}
