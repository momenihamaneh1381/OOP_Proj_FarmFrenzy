package classes;

import com.google.gson.Gson;

import java.io.File;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;

public class Main {
    public static void main(String[] args) {
        ListOfAccounts.existFiles();
        File file = new File("missions.json");

//        InputProcessor inputProcessor = new InputProcessor();
//        inputProcessor.run();
//        Missions missions;
//        FileOperator fileOperator = new FileOperator();
//        String json =  fileOperator.read("missions.json");
//        Gson gson = new Gson();
        try {
            file.createNewFile();
            // create Gson instance
            Gson gson = new Gson();

            // create a reader
            Reader reader = Files.newBufferedReader(Paths.get("missions.json"));

            // convert JSON string to Book object
            Missions book = gson.fromJson(reader, Missions.class);

            // print book
            System.out.println(book);

            // close reader
            reader.close();

        } catch (Exception ex) {
            ex.printStackTrace();
        }
        // convert JSON string to Book object

//        Missions missions = new Missions(5);
//        missions.missionRead(missions);
//        System.out.println(missions.tasks);
    }
}