package graphicPackage;

import animations.AnimalTransition;
import classes.*;


import com.google.gson.Gson;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;

import javafx.scene.Parent;
import javafx.scene.Scene;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;

import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;

import javafx.stage.Stage;


import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Scanner;

public class Game extends Application {
    private static Stage stage;
    AnimalTransition animalTransition;
//    ArrayList<Animal>animals ;
//    ArrayList<Grass> grasses ;
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
//    Stage stageMenu;
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
    @FXML
     Label lowWellLabel;
    @FXML
     Label wellSuccessLabel;
    @FXML
     Label storeFail;
    @FXML
     Label storeSuccess;
    @FXML
     ImageView menuImg;
    @FXML
     ImageView menuPauseImg;
    @FXML
     Button resumeBtn;
    @FXML
     TableView storeTable;
    @FXML
     TableColumn nameCol;
    @FXML
     TableColumn priceCol;
    @FXML
     TableColumn numCol;
    @FXML
     TableColumn capacityCol;

    public void initialize(){
        listOfAccounts = new ListOfAccounts();
        listOfAccounts.load();
        readUser();
        manager = new Manager(account , gamePane , wellSuccessLabel , storeSuccess , storeFail);
//        manager.missionRead(missions);
//        task = missions.tasks[level-1];
//        account.setCoins(account.getCoins()+ task.initialCoins) ;
        // TODO: 7/14/2021
        coinLabel.setText(String.valueOf(account.getCoins()));
//        domesticAnimals =manager.domesticAnimals;
//        animals = new ArrayList<>();
//        grasses = manager.grasses;
        truck = Truck.getInstance();
        store = Store.getInstanceStore();
        wateringSystem = WateringSystem.getInstanceWateringSystem();
//        Hen hen = new Hen(gamePane);
//        animals.add(hen);
//        gamePane.getChildren().add(hen);
        animalTransition = new AnimalTransition(manager , lowCoinLabel , lowWellLabel , storeSuccess , storeFail);
        animalTransition.play();// TODO: 7/14/2021 in animate
        gamePane.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                if (!manager.existIntersect((int) event.getX(), (int) event.getY())) {
                    plantGrass((int) event.getX(), (int) event.getY());
                }
                manager.intersectWildAnimal((int)event.getX() ,(int) event.getY());
                manager.intersectProduct((int)event.getX() ,(int) event.getY());
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
        if (wateringSystem.canPlant()) {
            Grass grass = new Grass(x, y);
            manager.grasses.add(grass);
            gamePane.getChildren().add(grass);
            wateringSystem.plant();
            account.logSave("Info" , "plant successfully");
        }else {
            lowWellLabel.setText("well is empty!");
            account.logSave("Error" , "plant Unsuccessfully! well is empty");
        }
        // TODO: 7/13/2021
    }

    public void welling(){
        if(manager.well()) {
            wellSuccessLabel.setText("plaese wait 3 Sec...");
            account.logSave("Info", "well successfuly");
        }
    }

    private void showStore(){
        storeTable.setVisible(true);
        // TODO: 7/15/2021  
//        ObservableList<Product> observableList = FXCollections.observableArrayList();
//        for (Product product : store.productsStoreList) {
//            observableList.add(product);
//        }
//        storeTable.setItems(observableList);
//        nameCol.setCellValueFactory(new PropertyValueFactory<Product , String>("name"));
//        capacityCol.setCellValueFactory(new PropertyValueFactory<Product , Integer>("capacity"));
//        priceCol.setCellValueFactory(new PropertyValueFactory<Product , Integer>("price"));
//        numCol.setCellValueFactory(new PropertyValueFactory<Product , Integer>("num"));
////        numCol.setCellValueFactory(new PropertyValueFactory<Product , Integer>("numOfGood"));
    }

    public void storeClick( ) {
        animalTransition.pause();
        showStore();
        // TODO: 7/13/2021
    }

    public void truckClick( ) {
        storeTable.setVisible(false);
        animalTransition.playFromStart();
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

    public void buyHound( ) {
        if(manager.buy("hound")){
        Hound hound = new Hound(gamePane);
        coinLabel.setText(String.valueOf(account.getCoins()));
        animate(hound);
            account.logSave("Info" , "HOUND buy successfuly");
        manager.hounds.add(hound);
        }else {
            lowCoinLabel.setText("Coin is not enough");
            account.logSave("Error" , "buy not successfuly! coins is not enough");
            // TODO: 7/14/2021
        }
    }

    private void animate(Animal animal) {
//        animals.add(animal);
        gamePane.getChildren().add(animal);
//        animalTransition.play();
    }

    public void buyCat( ) {
        if(manager.buy("cat")){
            Cat cat = new Cat(gamePane);
            coinLabel.setText(String.valueOf(account.getCoins()));
            manager.cats.add(cat);
            account.logSave("Info" , "CAT buy successfuly");
            animate(cat);
        }else {
            lowCoinLabel.setText("Coin is not enough");
            account.logSave("Error" , "buy not successfuly! coins is not enough");
            // TODO: 7/14/2021
        }
    }

    public void buyBuffalo( ) {
            if(manager.buy("buffalo")){
                Buffalo buffalo = new Buffalo(gamePane);
                coinLabel.setText(String.valueOf(account.getCoins()));
                manager.domesticAnimals.add(buffalo);
                account.logSave("Info" , "BUFFALO buy successfuly");
                animate(buffalo);
            }else {
                lowCoinLabel.setText("Coin is not enough");
                account.logSave("Error" , "buy not successfuly! coins is not enough");
                // TODO: 7/14/2021
            }
    }

    public void buyTurkey( ) {
        if(manager.buy("turkey")){
            Turkey turkey = new Turkey(gamePane);
            coinLabel.setText(String.valueOf(account.getCoins()));
            manager.domesticAnimals.add(turkey);
            account.logSave("Info" , "TURKEY buy successfuly");
            animate(turkey);
        }else {
            lowCoinLabel.setText("Coin is not enough");
            account.logSave("Error" , "buy not successfuly! coins is not enough");
            // TODO: 7/14/2021
        }
    }

    public void buyHen( ) {
        if(manager.buy("hen")){
            Hen  hen = new Hen(gamePane);
            coinLabel.setText(String.valueOf(account.getCoins()));
            manager.domesticAnimals.add(hen);
            account.logSave("Info" , "HEN buy successfuly");
            animate(hen);
        }else {
            lowCoinLabel.setText("Coin is not enough");
            account.logSave("Error" , "buy not successfuly! coins is not enough");
            // TODO: 7/14/2021
        }
    }

    public void menu( )   {
        animalTransition.pause();
        menuPauseImg.setVisible(true);
        resumeBtn.setVisible(true);
//        stageMenu = new Stage();
//        FXMLLoader fxmlLoader = new FXMLLoader();
//        Parent root = fxmlLoader.load(getClass().getResource("menuGame.fxml").openStream());
//        stageMenu.setTitle("Menu");
//        Scene loginOrSignUp = new Scene(root , 600 , 345);
//        stageMenu.setScene(loginOrSignUp);
//        stageMenu.showAndWait();
    }
    public void resume( ){
//        stageMenu.close();
        menuPauseImg.setVisible(false);
        resumeBtn.setVisible(false);
        animalTransition.playFromStart();
    }

//    public void newGame( ) throws IOException {
//        stageMenu.close();
//        stage = new Stage();
//        Parent root = FXMLLoader.load(getClass().getResource("menuForm.fxml"));
//        stage.setTitle("Menu");
//        Scene loginOrSignUp = new Scene(root , 650 , 358);
//        stage.setScene(loginOrSignUp);
//        stage.show();
//    }
//    private void missionRead(){
//        FileOperator fileOperator = new FileOperator();
//        String json =  fileOperator.read("missions.json");
//        Gson gson = new Gson();
//        missions = new Missions(5);
//        // convert JSON string to Mission object
//        missions = gson.fromJson(json, Missions.class);
//    }
}
