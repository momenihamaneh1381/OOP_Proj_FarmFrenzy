package graphicPackage;

import animations.AnimalTransition;
import classes.*;


import com.google.gson.Gson;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;

import javafx.scene.Parent;
import javafx.scene.Scene;

import javafx.scene.control.Label;
import javafx.scene.image.ImageView;

import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;

import javafx.stage.Stage;


import java.io.File;
import java.io.FileNotFoundException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Scanner;

public class Game extends Application {
    private static Stage stage;
    AnimalTransition animalTransition;
    ArrayList<Animal>animals ;
    ArrayList<Grass> grasses ;
//    ArrayList<DomesticAnimal> domesticAnimals;
//    ArrayList<Cat> cats;
//    ArrayList<Hound> hounds;
//    ArrayList<WildAnimal> wildAnimals;
//    ArrayList<Product> products;
//    ArrayList<Factory> factories;
//    ArrayList<Grass> grasses;


    WateringSystem wateringSystem ;
    Truck truck;
    Store store;
    Manager manager;
    Account account;
    ListOfAccounts listOfAccounts;
    Missions missions ;
    Task task;
    int level;
//    int time = 0;


    @FXML
     Pane gamePane;
    @FXML
     Label coinLabel;
    @FXML
     ImageView wellRectImg;
    @FXML
     ImageView storeImg;
    @FXML
     ImageView truckImg;
    @FXML
     ImageView buyHenImg;
    @FXML
     ImageView buyTurkeyImg;
    @FXML
     ImageView buyBuffaloImg;
    @FXML
     ImageView buyCatImg;
    @FXML
     ImageView buyDogimg;
    @FXML
     Label lowCoinLabel;

    public void initialize(){
        listOfAccounts = new ListOfAccounts();
        listOfAccounts.load();
        readUser();
        manager = new Manager(account , gamePane);
//        manager.missionRead(missions);
//        task = missions.tasks[level-1];
//        account.setCoins(account.getCoins()+ task.initialCoins) ;
        // TODO: 7/14/2021
        coinLabel.setText(String.valueOf(account.getCoins()));
//        domesticAnimals =manager.domesticAnimals;
        animals = new ArrayList<>();
        grasses = manager.grasses;
        truck = Truck.getInstance();
        store = Store.getInstanceStore();
        wateringSystem = WateringSystem.getInstanceWateringSystem();
//        Hen hen = new Hen(gamePane);
//        animals.add(hen);
//        gamePane.getChildren().add(hen);
        animalTransition = new AnimalTransition(animals );
        animalTransition.play();// TODO: 7/14/2021 in animate
        gamePane.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                plantGrass((int)event.getX() ,(int) event.getY());
            }
        });
    }

    private void readUser( ) {
        try {
            File myObj = new File("username.txt");
            Scanner myReader = new Scanner(myObj);
            String username = myReader.nextLine();
            level = myReader.nextInt();
            myReader.close();
            for (Account account1 : listOfAccounts.accounts) {
                if (username.equals(account1.getUsername()))
                    account = account1;
            }
        } catch (FileNotFoundException e) {

        }
    }

    public void plantGrass(int x , int y ) {
        Grass grass = new Grass(x ,y);
        grasses.add(grass);
        gamePane.getChildren().add(grass);
        // TODO: 7/13/2021
    }

    public void welling(){
        // TODO: 7/13/2021
    }

    public void storeClick( ) {
        // TODO: 7/13/2021
    }

    public void truckClick( ) {
        // TODO: 7/13/2021
    }

//    public void buyAnimal( ) {
//        Hen hen  = new Hen(gamePane);
//        gamePane.getChildren().add(hen);
//        animals.add(hen);
//        AnimalTransition animalTransition = new AnimalTransition(animals);
//        animalTransition.play();
//    }


    @Override
    public void start(Stage primaryStage) throws Exception {
//        Parent root = FXMLLoader.load(getClass().getResource("game.fxml"));
////        Scene menuScene = new Scene(root , 600 ,400);
////        Scene menuScene = new Scene(root , 1366 ,700);
//        Scene menuScene = new Scene(root , 851 ,639);
////        stage.setResizable(false);
//        stage.setScene(menuScene);
//        stage.setTitle("Game");
////        stage.setFullScreen(true);
        stage = primaryStage;
        Parent root = FXMLLoader.load(getClass().getResource("game.fxml"));
        primaryStage.setTitle("Game");
        Scene loginOrSignUp = new Scene(root , 851 , 639);
        primaryStage.setScene(loginOrSignUp);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch();
    }

    public void buyDog( ) {
        if(manager.buy("hound")){
        Hound hound = new Hound(gamePane);
        coinLabel.setText(String.valueOf(account.getCoins()));
        animate(hound);
        }else {
            lowCoinLabel.setText("Coin is not enough");
            // TODO: 7/14/2021
        }
    }

    private void animate(Animal animal) {
        animals.add(animal);
        gamePane.getChildren().add(animal);
//        animalTransition.play();
    }

    public void buyCat( ) {
        if(manager.buy("cat")){
            Cat cat = new Cat(gamePane);
            coinLabel.setText(String.valueOf(account.getCoins()));
            animate(cat);
        }else {
            lowCoinLabel.setText("Coin is not enough");
            // TODO: 7/14/2021
        }
    }

    public void buyBuffalo( ) {
            if(manager.buy("buffalo")){
                Buffalo buffalo = new Buffalo(gamePane);
                coinLabel.setText(String.valueOf(account.getCoins()));
                animate(buffalo);
            }else {
                lowCoinLabel.setText("Coin is not enough");
                // TODO: 7/14/2021
            }
    }

    public void buyTurkey( ) {
        if(manager.buy("turkey")){
            Turkey turkey = new Turkey(gamePane);
            coinLabel.setText(String.valueOf(account.getCoins()));
            animate(turkey);
        }else {
            lowCoinLabel.setText("Coin is not enough");
            // TODO: 7/14/2021
        }
    }

    public void buyHen( ) {
        if(manager.buy("hen")){
            Hen  hen = new Hen(gamePane);
            coinLabel.setText(String.valueOf(account.getCoins()));
            animate(hen);
        }else {
            lowCoinLabel.setText("Coin is not enough");
            // TODO: 7/14/2021
        }
    }
//    private void missionRead(){
//        FileOperator fileOperator = new FileOperator();
//        String json =  fileOperator.read("missions.json");
//        Gson gson = new Gson();
//        missions = new Missions(5);
//        // convert JSON string to Mission object
//        missions = gson.fromJson(json, Missions.class);
//    }
}
