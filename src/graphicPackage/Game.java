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

import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;

import javafx.stage.Stage;


import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Scanner;

public class Game extends Application {
    private static Stage stage;
    public Button eggpowderPlantUpgradeBtn;
    public Button upgradeWeavingBtn;
    public ImageView weavingImg;
    public Button buildWeavingBtn;
    public Label weavingLabel;
    public Button upgradePocketMilkBtn;
    public ImageView pocketMilkImg;
    public Label pocketMilkLabel;
    public Button buildPocketMilkBtn;
    public Button buildBakeryBtn;
    public Button buildSewingBtn;
    public Button buildIcecreamBtn;
    public ImageView bakeryImg;
    public ImageView sewingImg;
    public ImageView icecreamImg;
    public Button upgradeBakeryBtn;
    public Button upgradeSewingBtn;
    public Button upgradeIcecreamBtn;
    public Label sewingLabel;
    public Label icecreamLabel;
    public Label bakeryLabel;
    public ImageView incubatorImg;
    public Label incubatorLabel;
    public AnchorPane storeListPane;
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
//    int flag= 0;


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
     Button eggPowderPlantBtn;
    @FXML
     ImageView eggPowderPlantImg;
    @FXML
     Label eggpowderLabel;

    public void initialize(){
        listOfAccounts = new ListOfAccounts();
        listOfAccounts.load();
        readUser();
        manager = new Manager(account , gamePane , wellSuccessLabel , storeSuccess , storeFail , eggpowderLabel , weavingLabel , pocketMilkLabel , bakeryLabel , sewingLabel , icecreamLabel , incubatorLabel);
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

        storeListPane.setVisible(true);
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
        storeListPane.setVisible(false);
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

    public void workEggPowderPlant(){
        if (manager.work("eggPowderPlant")){
            eggpowderLabel.setText("wait 4 Sec...");
            // TODO: 7/15/2021
        }else {
            account.logSave("Error" , "EGG is not enough to work EGG POWDER PLANT");
        }

    }
    public void buildEggPowderPlant( ) {
        if (!manager.eggPowderPlant.isBiuld) {
            if (manager.canBuild(manager.eggPowderPlant.getPrice())) {
                eggPowderPlantImg.setVisible(true);
                manager.eggPowderPlant.isBiuld = true;
                manager.factories.add(manager.eggPowderPlant);
                account.logSave("Info", "EGG POWDER PLANT build successfully");
                eggPowderPlantBtn.setVisible(false);
                eggpowderPlantUpgradeBtn.setVisible(true);
            } else {
                account.logSave("Error", "coins is not enough");
                lowCoinLabel.setText("Coin is not enough");
            }
        }
        coinLabel.setText(String.valueOf(account.getCoins()));
    }
    public void upgradeEggPowderPlant( )   {
        if (manager.eggPowderPlant.getLevel()==1&&manager.canBuild(manager.eggPowderPlant.getPrice()/2)) {
            manager.eggPowderPlant.upgrade();
            account.logSave("Info", "EGG POWDER PLANT upgrade successfully");
            eggpowderPlantUpgradeBtn.setVisible(false);
            eggPowderPlantImg.setImage(new Image("source/eggpowderPlant2.jpg"));
        }else {
            account.logSave("Error" , "coins is not enough");
            lowCoinLabel.setText("Coin is not enough");
        }
        coinLabel.setText(String.valueOf(account.getCoins()));
    }

    public void buildWeavingFactory( ) {
        if (!manager.weavingFactory.isBiuld) {
            if (manager.canBuild(manager.weavingFactory.getPrice())) {
                weavingImg.setVisible(true);
                manager.weavingFactory.isBiuld = true;
                manager.factories.add(manager.weavingFactory);
                account.logSave("Info", "WEAVING FACTORY build successfully");
                buildWeavingBtn.setVisible(false);
                upgradeWeavingBtn.setVisible(true);
            } else {
                account.logSave("Error", "coins is not enough");
                lowCoinLabel.setText("Coin is not enough");
            }
        }
        coinLabel.setText(String.valueOf(account.getCoins()));
    }
    public void upgradeWeavingFactory( ) {
        if (manager.weavingFactory.getLevel()==1&&manager.canBuild(manager.weavingFactory.getPrice()/2)) {
            manager.weavingFactory.upgrade();
            account.logSave("Info", "WEAVING FACTORY upgrade successfully");
            upgradeWeavingBtn.setVisible(false);
            weavingImg.setImage(new Image("source/eggpowderPlant2.jpg"));
            // TODO: 7/16/2021 image
        }else {
            account.logSave("Error" , "coins is not enough");
            lowCoinLabel.setText("Coin is not enough");
        }
        coinLabel.setText(String.valueOf(account.getCoins()));
    }
    public void workWeaving(){
        if (manager.work("weavingFactory")){
            weavingLabel.setText("wait 5 Sec...");
            // TODO: 7/15/2021
        }else {
            account.logSave("Error" , "WING is not enough to work WEAVING FACTORY");
        }
    }

    public void buildPocketMilkFactory( ) {
        if (!manager.pocketMilkFactory.isBiuld) {
            if (manager.canBuild(manager.pocketMilkFactory.getPrice())) {
                pocketMilkImg.setVisible(true);
                manager.pocketMilkFactory.isBiuld = true;
                manager.factories.add(manager.pocketMilkFactory);
                account.logSave("Info", "POCKET MILK FACTORY build successfully");
                buildPocketMilkBtn.setVisible(false);
                upgradePocketMilkBtn.setVisible(true);
            } else {
                account.logSave("Error", "coins is not enough");
                lowCoinLabel.setText("Coin is not enough");
            }
        }
        coinLabel.setText(String.valueOf(account.getCoins()));
    }
    public void upgradePocketMilkFactory( ) {
        if (manager.pocketMilkFactory.getLevel()==1&&manager.canBuild(manager.pocketMilkFactory.getPrice()/2)) {
            manager.pocketMilkFactory.upgrade();
            account.logSave("Info", "POCKET MILK FACTORY upgrade successfully");
            upgradePocketMilkBtn.setVisible(false);
            pocketMilkImg.setImage(new Image("source/eggpowderPlant2.jpg"));
            // TODO: 7/16/2021  image
        }else {
            account.logSave("Error" , "coins is not enough");
            lowCoinLabel.setText("Coin is not enough");
        }
        coinLabel.setText(String.valueOf(account.getCoins()));
    }
    public void workPocketMilk(){
        if (manager.work("pocketMilkFactory")){
            pocketMilkLabel.setText("wait 6 Sec...");
            // TODO: 7/15/2021
        }else {
            account.logSave("Error" , "MILK is not enough to work POCKET MILK FACTORY");
        }
    }

    public void buildBakery( ) {
        if (!manager.bakery.isBiuld) {
            if (manager.canBuild(manager.bakery.getPrice())) {
                bakeryImg.setVisible(true);
                manager.bakery.isBiuld = true;
                manager.factories.add(manager.bakery);
                account.logSave("Info", "BAKERY build successfully");
                buildBakeryBtn.setVisible(false);
                upgradeBakeryBtn.setVisible(true);
            } else {
                account.logSave("Error", "coins is not enough");
                lowCoinLabel.setText("Coin is not enough");
            }
        }
        coinLabel.setText(String.valueOf(account.getCoins()));
    }
    public void upgradeBakery( ) {
        if (manager.bakery.getLevel()==1&&manager.canBuild(manager.bakery.getPrice()/2)) {
            manager.bakery.upgrade();
            account.logSave("Info", "BAKERY upgrade successfully");
            upgradeBakeryBtn.setVisible(false);
            bakeryImg.setImage(new Image("source/eggpowderPlant2.jpg"));
            // TODO: 7/16/2021  image
        }else {
            account.logSave("Error" , "coins is not enough");
            lowCoinLabel.setText("Coin is not enough");
        }
        coinLabel.setText(String.valueOf(account.getCoins()));
    }
    public void workBakery( ) {
        if (manager.work("bakery")){
            bakeryLabel.setText("wait 5 Sec...");
            // TODO: 7/15/2021
        }else {
            account.logSave("Error" , "POWDER is not enough to work BAKERY");
        }
    }


    public void buildSweingFactory( ) {
        if (!manager.sewingFactory.isBiuld) {
            if (manager.canBuild(manager.sewingFactory.getPrice())) {
                sewingImg.setVisible(true);
                manager.sewingFactory.isBiuld = true;
                manager.factories.add(manager.sewingFactory);
                account.logSave("Info", "SEWING FACTORY build successfully");
                buildSewingBtn.setVisible(false);
                upgradeSewingBtn.setVisible(true);
            } else {
                account.logSave("Error", "coins is not enough");
                lowCoinLabel.setText("Coin is not enough");
            }
        }
        coinLabel.setText(String.valueOf(account.getCoins()));
    }
    public void upgradeSweingFactory( ) {
        if (manager.sewingFactory.getLevel()==1&&manager.canBuild(manager.sewingFactory.getPrice()/2)) {
            manager.sewingFactory.upgrade();
            account.logSave("Info", "SEWING FACTORY upgrade successfully");
            upgradeSewingBtn.setVisible(false);
            sewingImg.setImage(new Image("source/eggpowderPlant2.jpg"));
            // TODO: 7/16/2021  image
        }else {
            account.logSave("Error" , "coins is not enough");
            lowCoinLabel.setText("Coin is not enough");
        }
        coinLabel.setText(String.valueOf(account.getCoins()));
    }
    public void workSewingFactory( ) {
        if (manager.work("sewingFactory")){
            sewingLabel.setText("wait 6 Sec...");
            // TODO: 7/15/2021
        }else {
            account.logSave("Error" , "FABRIC is not enough to work SEWING FACTORY");
        }
    }

    public void buildIceCreamFactory( ) {
        if (!manager.icecreamFactory.isBiuld) {
            if (manager.canBuild(manager.icecreamFactory.getPrice())) {
                icecreamImg.setVisible(true);
                manager.icecreamFactory.isBiuld = true;
                manager.factories.add(manager.icecreamFactory);
                account.logSave("Info", "ICECREAM FACTORY build successfully");
                buildIcecreamBtn.setVisible(false);
                upgradeIcecreamBtn.setVisible(true);
            } else {
                account.logSave("Error", "coins is not enough");
                lowCoinLabel.setText("Coin is not enough");
            }
        }
        coinLabel.setText(String.valueOf(account.getCoins()));
    }
    public void upgradeIceCreamFactory( ) {
        if (manager.icecreamFactory.getLevel()==1&&manager.canBuild(manager.icecreamFactory.getPrice()/2)) {
            manager.icecreamFactory.upgrade();
            account.logSave("Info", "ICECREAM FACTORY upgrade successfully");
            upgradeIcecreamBtn.setVisible(false);
            icecreamImg.setImage(new Image("source/eggpowderPlant2.jpg"));
            // TODO: 7/16/2021  image
        }else {
            account.logSave("Error" , "coins is not enough");
            lowCoinLabel.setText("Coin is not enough");
        }
        coinLabel.setText(String.valueOf(account.getCoins()));
    }
    public void workIcecream( ) {
        if (manager.work("icecreamFactory")){
            icecreamLabel.setText("wait 7 Sec...");
            // TODO: 7/15/2021
        }else {
            account.logSave("Error" , "POCKET MILK is not enough to work ICECREAM FACTORY");
        }
    }

    public void workIncubator( ) {
        if (manager.work("incubator")){
            incubatorLabel.setText("wait 6 Sec...");
            // TODO: 7/15/2021
        }else {
            account.logSave("Error" , "EGG is not enough to work INCUBATOR");
        }
    }

    public void eggTruckload( ) {
    }

    public void wingTruckload( ) {
    }

    public void milkTruckload( ) {
    }

    public void powderTruckload( ) {
    }

    public void fabricTruckload( ) {
    }

    public void pocketMilkTruckload( ) {
    }

    public void breadTruckload( ) {
    }

    public void clothTruckload( ) {
    }

    public void icecreamTruckload( ) {
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
