package classes;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import javafx.scene.layout.Pane;

import java.io.File;
import java.io.IOException;
import java.io.Writer;
import java.nio.file.Files;
import java.nio.file.Paths;

public class Main {
    private static Pane pane;
    public static void main(String[] args) throws IOException {
        String []s ={"tiger" , "lion"};
        int []n = {2 , 15};
        Step step = new Step("powder" ,2  , "turkey", 2 , s ,n ,
                1000 , 200 , 40 , 0 );

//        File file = new File("step1.json");
//        file.createNewFile();
//        Gson gson = new Gson();
//
//        // create a writer
//        Writer writer = Files.newBufferedWriter(Paths.get("step1.json"));
//
//        // convert book object to JSON file
//        gson.toJson(step, writer);
//
//        // close writer
//        writer.close();
////
//        GsonBuilder gsonBuilder = new GsonBuilder().setPrettyPrinting();
//        Gson gson = gsonBuilder.create();
//        String userListText = gson.toJson(step);
//
//        FileOperator fileOperator = new FileOperator();
//        fileOperator.write("step5.json", userListText, false);
//        try {
////             int []a= {4 , 7};
////
////
////            new Tasks(100, a, 1200, 50, 100);
////            Tasks tasks;
////                    int []b = {5};
////                    Bear bear = new Bear(pane);
////                    Tiger tiger  = new Tiger(pane);
////                    tasks = new Tasks(100 , a , 500 , 10 , 100);
////                    tasks.wildAnimals.add(bear);
////                    tasks.wildAnimals.add(tiger);
////                    tasks.goalProduct = new Product(ProductType.EGG );
////                    tasks.numOfGoalProduct = 5;
////                    tasks.goalDomesticAnimal = new DomesticAnimal(AnimalType.TURKEY , pane);
////                    tasks.numOfGoalDomesticAnimal = 2;
//
//            Turkey turkey  = new Turkey(pane);
//
//            // create Gson instance
//            Gson gson = new Gson();
//
//            // create a writer
//            Writer writer = Files.newBufferedWriter(Paths.get("task1.json"));
//
//            // convert book object to JSON file
//            gson.toJson(turkey, writer);
//
//            // close writer
//            writer.close();
//
//        } catch (Exception ex) {
//            ex.printStackTrace();
//        }
//        ListOfAccounts.existFiles();
//        File file = new File("missions.json");
//
////        InputProcessor inputProcessor = new InputProcessor();
////        inputProcessor.run();
////        Missions missions;
////        FileOperator fileOperator = new FileOperator();
////        String json =  fileOperator.read("missions.json");
////        Gson gson = new Gson();
//        try {
//            file.createNewFile();
//            // create Gson instance
//            Gson gson = new Gson();
//
//            // create a reader
//            Reader reader = Files.newBufferedReader(Paths.get("missions.json"));
//
//            // convert JSON string to Book object
//            Missions book = gson.fromJson(reader, Missions.class);
//
//            // print book
//            System.out.println(book);
//
//            // close reader
//            reader.close();
//
//        } catch (Exception ex) {
//            ex.printStackTrace();
//

        // convert JSON string to Book object

//        Missions missions = new Missions(5);
//        missions.missionRead(missions);
//        System.out.println(missions.tasks);
    }

    }
