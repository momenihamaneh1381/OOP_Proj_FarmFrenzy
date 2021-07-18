package graphicPackage;

import animations.*;
import classes.*;


import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;

import javafx.scene.Parent;
import javafx.scene.Scene;

import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;

import javafx.stage.Stage;


import java.io.*;
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
    public Label eggNumLabel;
    public Label milkNumLabel;
    public Label wingNumLabel;
    public Label powderNumLabel;
    public Label fabricNumLabel;
    public Label pocketMilkNumLabel;
    public Label breadNumLabel;
    public Label clothNumLabel;
    public Label icecreamNumLabel;
    public Label lionNumLabel;
    public Label tigerNumlabel;
    public Label bearNumLabel;
    public Label addSuccessLabel;
    public Button backBtn;
    public Button truckGoBtn;
    public Label capacityLabel;
    public Label maxtimeLabel;
    public Label timeLabel;
    public Label goalProductLabel;
    public Label goalCoinLabel;
    public Label goalDomesticAnimalLabel;
    public Label prizeLabel;
    public ImageView starProductImg;
    public ImageView starCionImg;
    public ImageView starDomesticImg;
    AnimalTransition animalTransition;
    WellTransition wellTransition ;
    boolean instore , intruck;
    WateringSystem wateringSystem ;
    Truck truck;
    Store store;
    Manager manager;
    Account account;
    ListOfAccounts listOfAccounts;
    public static int level;
    EggpowderTransition eggpowderTransition ;
    WeavingTransition weavingTransition ;
    BakeryTransition bakeryTransition;
    SewingTransition sewingTransition;
    IcecreamTransition icecreamTransition;


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


        manager = new Manager(account , gamePane , wellSuccessLabel , storeSuccess , storeFail , eggpowderLabel
                , weavingLabel , pocketMilkLabel , bakeryLabel , sewingLabel , icecreamLabel , incubatorLabel
                , truckImg , coinLabel , timeLabel , starProductImg,starCionImg ,starDomesticImg);
        account.setCoins(account.getCoins()+manager.step.initCoin);
        maxtimeLabel.setText(String.valueOf(manager.step.maxTime));
        prizeLabel.setText("prize : "+String.valueOf(manager.step.prize));
        wellTransition = new WellTransition(wellRectImg);
        eggpowderTransition = new EggpowderTransition(manager.eggPowderPlant.level , eggPowderPlantImg);
         weavingTransition = new WeavingTransition(weavingImg , manager.weavingFactory.level);
         bakeryTransition = new BakeryTransition(bakeryImg , manager.bakery.level);
         sewingTransition = new SewingTransition(sewingImg , manager.sewingFactory.level);
         icecreamTransition = new IcecreamTransition(icecreamImg , manager.icecreamFactory.level);

        if (manager.step.goalCoin==0)
        goalCoinLabel.setVisible(false);
        else {
            goalCoinLabel.setVisible(true);
            goalCoinLabel.setText(String.valueOf("coin : "+manager.step.goalCoin));
        }
        if (manager.step.goalDomesticAnimalName.length()==0)
            goalDomesticAnimalLabel.setVisible(false);
        else {
            goalDomesticAnimalLabel.setVisible(true);
            goalDomesticAnimalLabel.setText(manager.step.goalDomesticAnimalName+" : "+manager.step.goalDomesticAnimalNum);
        }
        if (manager.step.goalProductName.length()==0)
            goalProductLabel.setVisible(false);
        else {
            goalProductLabel.setVisible(true);
            goalProductLabel.setText(manager.step.goalProductName+" : "+manager.step.goalProductNum);
        }
        coinLabel.setText(String.valueOf(account.getCoins()));
        truck = Truck.getInstance();
        store = Store.getInstanceStore();
        wateringSystem = WateringSystem.getInstanceWateringSystem();
        animalTransition = new AnimalTransition(manager , lowCoinLabel , lowWellLabel , storeSuccess , storeFail , this);
        animalTransition.play();
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
    }

    public void welling(){
        if(manager.well()) {
            wellSuccessLabel.setText("plaese wait 3 Sec...");
            account.logSave("Info", "well successfuly");
            wellTransition.play();
        }
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
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
        }
    }

    private void animate(Animal animal) {
        gamePane.getChildren().add(animal);
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
        }
    }

    public void menu( )   {
        animationsPause();
        menuPauseImg.setVisible(true);
        resumeBtn.setVisible(true);
    }
    public void resume( ){
        menuPauseImg.setVisible(false);
        resumeBtn.setVisible(false);
        animationsResume();
    }

    public void workEggPowderPlant(){
        if (manager.work("eggPowderPlant")){
            eggpowderLabel.setText("wait 4 Sec...");
            eggpowderTransition.play();
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
        if (!manager.eggPowderPlant.work&&manager.eggPowderPlant.getLevel()==1&&manager.canBuild(manager.eggPowderPlant.getPrice()/2)) {
            manager.eggPowderPlant.upgrade();
            account.logSave("Info", "EGG POWDER PLANT upgrade successfully");
            eggpowderPlantUpgradeBtn.setVisible(false);
            eggPowderPlantImg.setImage(new Image("source/factory/eggPowderPlantL2.png"));
        }else if (!manager.eggPowderPlant.work){
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
        if (!manager.weavingFactory.work&&manager.weavingFactory.getLevel()==1&&manager.canBuild(manager.weavingFactory.getPrice()/2)) {
            manager.weavingFactory.upgrade();
            account.logSave("Info", "WEAVING FACTORY upgrade successfully");
            upgradeWeavingBtn.setVisible(false);
            weavingImg.setImage(new Image("source/factory/weavingFactoryL2.png"));
        }else if (!manager.weavingFactory.work){
            account.logSave("Error" , "coins is not enough");
            lowCoinLabel.setText("Coin is not enough");
        }
        coinLabel.setText(String.valueOf(account.getCoins()));
    }
    public void workWeaving(){
        if (manager.work("weavingFactory")){
            weavingLabel.setText("wait 5 Sec...");
            weavingTransition.play();
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
        if (!manager.pocketMilkFactory.work&&manager.pocketMilkFactory.getLevel()==1&&manager.canBuild(manager.pocketMilkFactory.getPrice()/2)) {
            manager.pocketMilkFactory.upgrade();
            account.logSave("Info", "POCKET MILK FACTORY upgrade successfully");
            upgradePocketMilkBtn.setVisible(false);
            pocketMilkImg.setImage(new Image("source/factory/pocketMilkL2.png"));
        }else if (!manager.pocketMilkFactory.work){
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
        if (!manager.bakery.work&&manager.bakery.getLevel()==1&&manager.canBuild(manager.bakery.getPrice()/2)) {
            manager.bakery.upgrade();
            account.logSave("Info", "BAKERY upgrade successfully");
            upgradeBakeryBtn.setVisible(false);
            bakeryImg.setImage(new Image("source/factory/bakeryL2.png"));
        }else if (!manager.bakery.work){
            account.logSave("Error" , "coins is not enough");
            lowCoinLabel.setText("Coin is not enough");
        }
        coinLabel.setText(String.valueOf(account.getCoins()));
    }
    public void workBakery( ) {
        if (manager.work("bakery")){
            bakeryLabel.setText("wait 5 Sec...");
            bakeryTransition.play();
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
        if (!manager.sewingFactory.work&&manager.sewingFactory.getLevel()==1&&manager.canBuild(manager.sewingFactory.getPrice()/2)) {
            manager.sewingFactory.upgrade();
            account.logSave("Info", "SEWING FACTORY upgrade successfully");
            upgradeSewingBtn.setVisible(false);
            sewingImg.setImage(new Image("source/factory/sewingFactoryL2.png"));
        }else if (!manager.sewingFactory.work){
            account.logSave("Error" , "coins is not enough");
            lowCoinLabel.setText("Coin is not enough");
        }
        coinLabel.setText(String.valueOf(account.getCoins()));
    }
    public void workSewingFactory( ) {
        if (manager.work("sewingFactory")){
            sewingLabel.setText("wait 6 Sec...");
            sewingTransition.play();
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
        if (!manager.icecreamFactory.work&&manager.icecreamFactory.getLevel()==1&&manager.canBuild(manager.icecreamFactory.getPrice()/2)) {
            manager.icecreamFactory.upgrade();
            account.logSave("Info", "ICECREAM FACTORY upgrade successfully");
            upgradeIcecreamBtn.setVisible(false);
            icecreamImg.setImage(new Image("source/factory/icecreamL2.png"));
        }else if (!manager.icecreamFactory.work){
            account.logSave("Error" , "coins is not enough");
            lowCoinLabel.setText("Coin is not enough");
        }
        coinLabel.setText(String.valueOf(account.getCoins()));
    }
    public void workIcecream( ) {
        if (manager.work("icecreamFactory")){
            icecreamLabel.setText("wait 7 Sec...");
            icecreamTransition.play();
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

    private void setLabelsStore(){
        eggNumLabel.setText(String.valueOf(store.numProduct(ProductType.EGG)));
        wingNumLabel.setText(String.valueOf(store.numProduct(ProductType.WING)));
        milkNumLabel.setText(String.valueOf(store.numProduct(ProductType.MILK)));
        powderNumLabel.setText(String.valueOf(store.numProduct(ProductType.POWDER)));
        fabricNumLabel.setText(String.valueOf(store.numProduct(ProductType.FABRIC)));
        pocketMilkNumLabel.setText(String.valueOf(store.numProduct(ProductType.POCKET_MILK)));
        breadNumLabel.setText(String.valueOf(store.numProduct(ProductType.BREAD)));
        clothNumLabel.setText(String.valueOf(store.numProduct(ProductType.CLOTH)));
        icecreamNumLabel.setText(String.valueOf(store.numProduct(ProductType.ICE_CREAM)));
        lionNumLabel.setText(String.valueOf(store.numProduct(AnimalType.LION)));
        bearNumLabel.setText(String.valueOf(store.numProduct(AnimalType.BEAR)));
        tigerNumlabel.setText(String.valueOf(store.numProduct(AnimalType.TIGER)));
        addSuccessLabel.setText("");
    }
    private void setLabelsTruck(){
        eggNumLabel.setText(String.valueOf(truck.numProduct(ProductType.EGG)));
        wingNumLabel.setText(String.valueOf(truck.numProduct(ProductType.WING)));
        milkNumLabel.setText(String.valueOf(truck.numProduct(ProductType.MILK)));
        powderNumLabel.setText(String.valueOf(truck.numProduct(ProductType.POWDER)));
        fabricNumLabel.setText(String.valueOf(truck.numProduct(ProductType.FABRIC)));
        pocketMilkNumLabel.setText(String.valueOf(truck.numProduct(ProductType.POCKET_MILK)));
        breadNumLabel.setText(String.valueOf(truck.numProduct(ProductType.BREAD)));
        clothNumLabel.setText(String.valueOf(truck.numProduct(ProductType.CLOTH)));
        icecreamNumLabel.setText(String.valueOf(truck.numProduct(ProductType.ICE_CREAM)));
        lionNumLabel.setText(String.valueOf(truck.numProduct(AnimalType.LION)));
        bearNumLabel.setText(String.valueOf(truck.numProduct(AnimalType.BEAR)));
        tigerNumlabel.setText(String.valueOf(truck.numProduct(AnimalType.TIGER)));
        addSuccessLabel.setText("");
    }
    public void storeClick( ) {
        instore = true;
        intruck = false;
        capacityLabel.setText(String.valueOf(store.capacity));
        animationsPause();
        truckGoBtn.setVisible(false);
        setLabelsStore();
        storeListPane.setVisible(true);
    }

    public void truckClick( ) {
        instore = false;
        intruck = true;
        capacityLabel.setText(String.valueOf(truck.capacity));
        animationsPause();
        storeListPane.setVisible(true);
        truckGoBtn.setVisible(true);
        setLabelsTruck();
    }


    public void eggTruckload( ) {
        if (instore) {
            if (!truck.isGo && manager.truckLoad("egg")) {
                addSuccessLabel.setText("1 Egg added to truck");
                account.logSave("Info", "1 Egg added to truck");
                eggNumLabel.setText(String.valueOf(store.numProduct(ProductType.EGG)));
                capacityLabel.setText(String.valueOf(store.capacity));
            } else if (truck.isGo) {
                addSuccessLabel.setText("truck is not ready");
                account.logSave("Error", "truck is not ready");
            } else if (store.numProduct(ProductType.EGG) == 0) {
                addSuccessLabel.setText("num of eggs is 0 !");
                account.logSave("Error", "num of eggs is 0 ");
            } else {
                addSuccessLabel.setText("capacity of truck is not enough");
                account.logSave("Error", "capacity of truck is not enough ");
            }
        }else if (intruck){
            if (!truck.isGo && manager.truckUnLoad("egg")) {
                addSuccessLabel.setText("1 Egg unLoaded");
                account.logSave("Info", "1 Egg unLoaded");
                eggNumLabel.setText(String.valueOf(truck.numProduct(ProductType.EGG)));
                capacityLabel.setText(String.valueOf(truck.capacity));
            } else if (truck.isGo) {
                addSuccessLabel.setText("truck is not ready");
                account.logSave("Error", "truck is not ready");
            } else if (truck.numProduct(ProductType.EGG) == 0) {
                addSuccessLabel.setText("num of eggs is 0 !");
                account.logSave("Error", "num of eggs is 0 ");
            } else {
                addSuccessLabel.setText("capacity of store is not enough");
                account.logSave("Error", "capacity of store is not enough ");
            }
        }
    }

    public void wingTruckload( ) {
        if (instore) {
            if (!truck.isGo && manager.truckLoad("wing")) {
                addSuccessLabel.setText("1 Wing added to truck");
                account.logSave("Info", "1 Wing added to truck");
                wingNumLabel.setText(String.valueOf(store.numProduct(ProductType.WING)));
                capacityLabel.setText(String.valueOf(store.capacity));
            } else if (truck.isGo) {
                addSuccessLabel.setText("truck is not ready");
                account.logSave("Error", "truck is not ready");
            } else if (store.numProduct(ProductType.WING) == 0) {
                addSuccessLabel.setText("num of Wing is 0 !");
                account.logSave("Error", "num of Wing is 0 ");
            } else {
                addSuccessLabel.setText("capacity of truck is not enough");
                account.logSave("Error", "capacity of truck is not enough ");
            }
        }else if (intruck){
            if (!truck.isGo && manager.truckUnLoad("wing")) {
                addSuccessLabel.setText("1 Wing unLoaded");
                account.logSave("Info", "1 Wing unLoaded");
                wingNumLabel.setText(String.valueOf(truck.numProduct(ProductType.WING)));
                capacityLabel.setText(String.valueOf(truck.capacity));
            } else if (truck.isGo) {
                addSuccessLabel.setText("truck is not ready");
                account.logSave("Error", "truck is not ready");
            } else if (truck.numProduct(ProductType.WING) == 0) {
                addSuccessLabel.setText("num of Wing is 0 !");
                account.logSave("Error", "num of Wing is 0 ");
            } else {
                addSuccessLabel.setText("capacity of store is not enough");
                account.logSave("Error", "capacity of store is not enough ");
            }
        }
    }

    public void milkTruckload( ) {
        if (instore) {
            if (!truck.isGo && manager.truckLoad("milk")) {
                addSuccessLabel.setText("1 Milk added to truck");
                account.logSave("Info", "1 Milk added to truck");
                milkNumLabel.setText(String.valueOf(store.numProduct(ProductType.MILK)));
                capacityLabel.setText(String.valueOf(store.capacity));
            } else if (truck.isGo) {
                addSuccessLabel.setText("truck is not ready");
                account.logSave("Error", "truck is not ready");
            } else if (store.numProduct(ProductType.MILK) == 0) {
                addSuccessLabel.setText("num of Milk is 0 !");
                account.logSave("Error", "num of Milk is 0 ");
            } else {
                addSuccessLabel.setText("capacity of truck is not enough");
                account.logSave("Error", "capacity of truck is not enough ");
            }
        }else if (intruck){
            if (!truck.isGo && manager.truckUnLoad("milk")) {
                addSuccessLabel.setText("1 Milk unLoaded");
                account.logSave("Info", "1 Milk unLoaded");
                milkNumLabel.setText(String.valueOf(truck.numProduct(ProductType.MILK)));
                capacityLabel.setText(String.valueOf(truck.capacity));
            } else if (truck.isGo) {
                addSuccessLabel.setText("truck is not ready");
                account.logSave("Error", "truck is not ready");
            } else if (truck.numProduct(ProductType.MILK) == 0) {
                addSuccessLabel.setText("num of Milk is 0 !");
                account.logSave("Error", "num of Milk is 0 ");
            } else {
                addSuccessLabel.setText("capacity of store is not enough");
                account.logSave("Error", "capacity of store is not enough ");
            }
        }
    }

    public void powderTruckload( ) {
        if (instore) {
            if (!truck.isGo && manager.truckLoad("powder")) {
                addSuccessLabel.setText("1 Powder added to truck");
                account.logSave("Info", "1 Powder added to truck");
                powderNumLabel.setText(String.valueOf(store.numProduct(ProductType.POWDER)));
                capacityLabel.setText(String.valueOf(store.capacity));
            } else if (truck.isGo) {
                addSuccessLabel.setText("truck is not ready");
                account.logSave("Error", "truck is not ready");
            } else if (store.numProduct(ProductType.POWDER) == 0) {
                addSuccessLabel.setText("num of Powder is 0 !");
                account.logSave("Error", "num of Powder is 0 ");
            } else {
                addSuccessLabel.setText("capacity of truck is not enough");
                account.logSave("Error", "capacity of truck is not enough ");
            }
        }else if (intruck){
            if (!truck.isGo && manager.truckUnLoad("Powder")) {
                addSuccessLabel.setText("1 Powder unLoaded");
                account.logSave("Info", "1 Powder unLoaded");
                powderNumLabel.setText(String.valueOf(truck.numProduct(ProductType.POWDER)));
                capacityLabel.setText(String.valueOf(truck.capacity));
            } else if (truck.isGo) {
                addSuccessLabel.setText("truck is not ready");
                account.logSave("Error", "truck is not ready");
            } else if (truck.numProduct(ProductType.POWDER) == 0) {
                addSuccessLabel.setText("num of Powder is 0 !");
                account.logSave("Error", "num of Powder is 0 ");
            } else {
                addSuccessLabel.setText("capacity of store is not enough");
                account.logSave("Error", "capacity of store is not enough ");
            }
        }
    }

    public void fabricTruckload( ) {
        if (instore) {
            if (!truck.isGo && manager.truckLoad("fabric")) {
                addSuccessLabel.setText("1 Fabric added to truck");
                account.logSave("Info", "1 Fabric added to truck");
                fabricNumLabel.setText(String.valueOf(store.numProduct(ProductType.FABRIC)));
                capacityLabel.setText(String.valueOf(store.capacity));
            } else if (truck.isGo) {
                addSuccessLabel.setText("truck is not ready");
                account.logSave("Error", "truck is not ready");
            } else if (store.numProduct(ProductType.FABRIC) == 0) {
                addSuccessLabel.setText("num of Fabric is 0 !");
                account.logSave("Error", "num of Fabric is 0 ");
            } else {
                addSuccessLabel.setText("capacity of truck is not enough");
                account.logSave("Error", "capacity of truck is not enough ");
            }
        }else if (intruck){
            if (!truck.isGo && manager.truckUnLoad("Fabric")) {
                addSuccessLabel.setText("1 Fabric unLoaded");
                account.logSave("Info", "1 Fabric unLoaded");
                fabricNumLabel.setText(String.valueOf(truck.numProduct(ProductType.FABRIC)));
                capacityLabel.setText(String.valueOf(truck.capacity));
            } else if (truck.isGo) {
                addSuccessLabel.setText("truck is not ready");
                account.logSave("Error", "truck is not ready");
            } else if (truck.numProduct(ProductType.FABRIC) == 0) {
                addSuccessLabel.setText("num of Fabric is 0 !");
                account.logSave("Error", "num of Fabric is 0 ");
            } else {
                addSuccessLabel.setText("capacity of store is not enough");
                account.logSave("Error", "capacity of store is not enough ");
            }
        }
    }

    public void pocketMilkTruckload( ) {
        if (instore) {
            if (!truck.isGo && manager.truckLoad("PocketMilk")) {
                addSuccessLabel.setText("1 PocketMilk added to truck");
                account.logSave("Info", "1 PocketMilk added to truck");
                pocketMilkNumLabel.setText(String.valueOf(store.numProduct(ProductType.POCKET_MILK)));
                capacityLabel.setText(String.valueOf(store.capacity));
            } else if (truck.isGo) {
                addSuccessLabel.setText("truck is not ready");
                account.logSave("Error", "truck is not ready");
            } else if (store.numProduct(ProductType.POCKET_MILK) == 0) {
                addSuccessLabel.setText("num of PocketMilk is 0 !");
                account.logSave("Error", "num of PocketMilk is 0 ");
            } else {
                addSuccessLabel.setText("capacity of truck is not enough");
                account.logSave("Error", "capacity of truck is not enough ");
            }
        }else if (intruck){
            if (!truck.isGo && manager.truckUnLoad("PocketMilk")) {
                addSuccessLabel.setText("1 PocketMilk unLoaded");
                account.logSave("Info", "1 PocketMilk unLoaded");
                pocketMilkNumLabel.setText(String.valueOf(truck.numProduct(ProductType.POCKET_MILK)));
                capacityLabel.setText(String.valueOf(truck.capacity));
            } else if (truck.isGo) {
                addSuccessLabel.setText("truck is not ready");
                account.logSave("Error", "truck is not ready");
            } else if (truck.numProduct(ProductType.POCKET_MILK) == 0) {
                addSuccessLabel.setText("num of PocketMilk is 0 !");
                account.logSave("Error", "num of PocketMilk is 0 ");
            } else {
                addSuccessLabel.setText("capacity of store is not enough");
                account.logSave("Error", "capacity of store is not enough ");
            }
        }
    }

    public void breadTruckload( ) {
        if (instore) {
            if (!truck.isGo && manager.truckLoad("Bread")) {
                addSuccessLabel.setText("1 Bread added to truck");
                account.logSave("Info", "1 Bread added to truck");
                breadNumLabel.setText(String.valueOf(store.numProduct(ProductType.BREAD)));
                capacityLabel.setText(String.valueOf(store.capacity));
            } else if (truck.isGo) {
                addSuccessLabel.setText("truck is not ready");
                account.logSave("Error", "truck is not ready");
            } else if (store.numProduct(ProductType.BREAD) == 0) {
                addSuccessLabel.setText("num of Bread is 0 !");
                account.logSave("Error", "num of Bread is 0 ");
            } else {
                addSuccessLabel.setText("capacity of truck is not enough");
                account.logSave("Error", "capacity of truck is not enough ");
            }
        }else if (intruck){
            if (!truck.isGo && manager.truckUnLoad("Bread")) {
                addSuccessLabel.setText("1 Bread unLoaded");
                account.logSave("Info", "1 Bread unLoaded");
                breadNumLabel.setText(String.valueOf(truck.numProduct(ProductType.BREAD)));
                capacityLabel.setText(String.valueOf(truck.capacity));
            } else if (truck.isGo) {
                addSuccessLabel.setText("truck is not ready");
                account.logSave("Error", "truck is not ready");
            } else if (truck.numProduct(ProductType.BREAD) == 0) {
                addSuccessLabel.setText("num of Bread is 0 !");
                account.logSave("Error", "num of Bread is 0 ");
            } else {
                addSuccessLabel.setText("capacity of store is not enough");
                account.logSave("Error", "capacity of store is not enough ");
            }
        }
    }

    public void clothTruckload( ) {
        if (instore) {
            if (!truck.isGo && manager.truckLoad("Cloth")) {
                addSuccessLabel.setText("1 Cloth added to truck");
                account.logSave("Info", "1 Cloth added to truck");
                clothNumLabel.setText(String.valueOf(store.numProduct(ProductType.CLOTH)));
                capacityLabel.setText(String.valueOf(store.capacity));
            } else if (truck.isGo) {
                addSuccessLabel.setText("truck is not ready");
                account.logSave("Error", "truck is not ready");
            } else if (store.numProduct(ProductType.CLOTH) == 0) {
                addSuccessLabel.setText("num of Cloth is 0 !");
                account.logSave("Error", "num of Cloth is 0 ");
            } else {
                addSuccessLabel.setText("capacity of truck is not enough");
                account.logSave("Error", "capacity of truck is not enough ");
            }
        }else if (intruck){
            if (!truck.isGo && manager.truckUnLoad("Cloth")) {
                addSuccessLabel.setText("1 Cloth unLoaded");
                account.logSave("Info", "1 Cloth unLoaded");
                clothNumLabel.setText(String.valueOf(truck.numProduct(ProductType.CLOTH)));
                capacityLabel.setText(String.valueOf(truck.capacity));
            } else if (truck.isGo) {
                addSuccessLabel.setText("truck is not ready");
                account.logSave("Error", "truck is not ready");
            } else if (truck.numProduct(ProductType.CLOTH) == 0) {
                addSuccessLabel.setText("num of Cloth is 0 !");
                account.logSave("Error", "num of Cloth is 0 ");
            } else {
                addSuccessLabel.setText("capacity of store is not enough");
                account.logSave("Error", "capacity of store is not enough ");
            }
        }
    }

    public void icecreamTruckload( ) {
        if (instore) {
            if (!truck.isGo && manager.truckLoad("Icecream")) {
                addSuccessLabel.setText("1 Icecream added to truck");
                account.logSave("Info", "1 Icecream added to truck");
                icecreamNumLabel.setText(String.valueOf(store.numProduct(ProductType.ICE_CREAM)));
                capacityLabel.setText(String.valueOf(store.capacity));
            } else if (truck.isGo) {
                addSuccessLabel.setText("truck is not ready");
                account.logSave("Error", "truck is not ready");
            } else if (store.numProduct(ProductType.ICE_CREAM) == 0) {
                addSuccessLabel.setText("num of Icecream is 0 !");
                account.logSave("Error", "num of Icecream is 0 ");
            } else {
                addSuccessLabel.setText("capacity of truck is not enough");
                account.logSave("Error", "capacity of truck is not enough ");
            }
        }else if (intruck){
            if (!truck.isGo && manager.truckUnLoad("Icecream")) {
                addSuccessLabel.setText("1 Icecream unLoaded");
                account.logSave("Info", "1 Icecream unLoaded");
                icecreamNumLabel.setText(String.valueOf(truck.numProduct(ProductType.ICE_CREAM)));
                capacityLabel.setText(String.valueOf(truck.capacity));
            } else if (truck.isGo) {
                addSuccessLabel.setText("truck is not ready");
                account.logSave("Error", "truck is not ready");
            } else if (truck.numProduct(ProductType.ICE_CREAM) == 0) {
                addSuccessLabel.setText("num of Icecream is 0 !");
                account.logSave("Error", "num of Icecream is 0 ");
            } else {
                addSuccessLabel.setText("capacity of store is not enough");
                account.logSave("Error", "capacity of store is not enough ");
            }
        }
    }

    public void lionTruckload() {
        if (instore) {
            if (!truck.isGo && manager.truckLoad("Lion")) {
                addSuccessLabel.setText("1 Lion added to truck");
                account.logSave("Info", "1 Lion added to truck");
                lionNumLabel.setText(String.valueOf(store.numProduct(AnimalType.LION)));
                capacityLabel.setText(String.valueOf(store.capacity));
            } else if (truck.isGo) {
                addSuccessLabel.setText("truck is not ready");
                account.logSave("Error", "truck is not ready");
            } else if (store.numProduct(AnimalType.LION) == 0) {
                addSuccessLabel.setText("num of Lion is 0 !");
                account.logSave("Error", "num of Lion is 0 ");
            } else {
                addSuccessLabel.setText("capacity of truck is not enough");
                account.logSave("Error", "capacity of truck is not enough ");
            }
        }else if (intruck){
            if (!truck.isGo && manager.truckUnLoad("Lion")) {
                addSuccessLabel.setText("1 Lion unLoaded");
                account.logSave("Info", "1 Lion unLoaded");
                lionNumLabel.setText(String.valueOf(truck.numProduct(AnimalType.LION)));
                capacityLabel.setText(String.valueOf(truck.capacity));
            } else if (truck.isGo) {
                addSuccessLabel.setText("truck is not ready");
                account.logSave("Error", "truck is not ready");
            } else if (truck.numProduct(AnimalType.LION) == 0) {
                addSuccessLabel.setText("num of Lion is 0 !");
                account.logSave("Error", "num of Lion is 0 ");
            } else {
                addSuccessLabel.setText("capacity of store is not enough");
                account.logSave("Error", "capacity of store is not enough ");
            }
        }
    }

    public void bearTruckload( ) {
        if (instore) {
            if (!truck.isGo && manager.truckLoad("Bear")) {
                addSuccessLabel.setText("1 Bear added to truck");
                account.logSave("Info", "1 Bear added to truck");
                bearNumLabel.setText(String.valueOf(store.numProduct(AnimalType.BEAR)));
                capacityLabel.setText(String.valueOf(store.capacity));
            } else if (truck.isGo) {
                addSuccessLabel.setText("truck is not ready");
                account.logSave("Error", "truck is not ready");
            } else if (store.numProduct(AnimalType.BEAR) == 0) {
                addSuccessLabel.setText("num of Bear is 0 !");
                account.logSave("Error", "num of Bear is 0 ");
            } else {
                addSuccessLabel.setText("capacity of truck is not enough");
                account.logSave("Error", "capacity of truck is not enough ");
            }
        }else if (intruck){
            if (!truck.isGo && manager.truckUnLoad("Bear")) {
                addSuccessLabel.setText("1 Bear unLoaded");
                account.logSave("Info", "1 Bear unLoaded");
                bearNumLabel.setText(String.valueOf(truck.numProduct(AnimalType.BEAR)));
                capacityLabel.setText(String.valueOf(truck.capacity));
            } else if (truck.isGo) {
                addSuccessLabel.setText("truck is not ready");
                account.logSave("Error", "truck is not ready");
            } else if (truck.numProduct(AnimalType.BEAR) == 0) {
                addSuccessLabel.setText("num of Bear is 0 !");
                account.logSave("Error", "num of Bear is 0 ");
            } else {
                addSuccessLabel.setText("capacity of store is not enough");
                account.logSave("Error", "capacity of store is not enough ");
            }
        }
    }

    public void tigerTruckload( ) {
        if (instore) {
            if (!truck.isGo && manager.truckLoad("Tiger")) {
                addSuccessLabel.setText("1 Tiger added to truck");
                account.logSave("Info", "1 Tiger added to truck");
                tigerNumlabel.setText(String.valueOf(store.numProduct(AnimalType.TIGER)));
                capacityLabel.setText(String.valueOf(store.capacity));
            } else if (truck.isGo) {
                addSuccessLabel.setText("truck is not ready");
                account.logSave("Error", "truck is not ready");
            } else if (store.numProduct(AnimalType.TIGER) == 0) {
                addSuccessLabel.setText("num of Tiger is 0 !");
                account.logSave("Error", "num of Tiger is 0 ");
            } else {
                addSuccessLabel.setText("capacity of truck is not enough");
                account.logSave("Error", "capacity of truck is not enough ");
            }
        }else if (intruck){
            if (!truck.isGo && manager.truckUnLoad("Tiger")) {
                addSuccessLabel.setText("1 Tiger unLoaded");
                account.logSave("Info", "1 Tiger unLoaded");
                tigerNumlabel.setText(String.valueOf(truck.numProduct(AnimalType.TIGER)));
                capacityLabel.setText(String.valueOf(truck.capacity));
            } else if (truck.isGo) {
                addSuccessLabel.setText("truck is not ready");
                account.logSave("Error", "truck is not ready");
            } else if (truck.numProduct(AnimalType.TIGER) == 0) {
                addSuccessLabel.setText("num of Tiger is 0 !");
                account.logSave("Error", "num of Tiger is 0 ");
            } else {
                addSuccessLabel.setText("capacity of store is not enough");
                account.logSave("Error", "capacity of store is not enough ");
            }
        }
    }

    public void backToGame( ) {
        storeListPane.setVisible(false);
        animationsResume();
    }

    public void truckGo( ) {
        if (truck.isGo){
            addSuccessLabel.setText("truck is not ready");
            account.logSave("Error", "truck is not ready");
        }else if (truck.capacity==0){
            addSuccessLabel.setText("truck is empty");
            account.logSave("Error", "truck is empty");
        }else {
            truck.isGo=true;
            truckImg.setVisible(false);
            storeListPane.setVisible(false);
            animationsResume();
        }
    }

    public void end() throws IOException {
        account.logSave("Info", "level complete successfully");
        account.logSave("Info" , "game end in "+manager.time+" Sec");
        if (level == account.getCompleteLevels())
        account.setCompleteLevels(account.getCompleteLevels()+1);
        if (manager.time<=manager.step.maxTime){
            account.setCoins(manager.step.prize);
            account.logSave("Info" , "you win "+manager.step.prize+" prize");
        }
        else
            account.setCoins(0);
        listOfAccounts.save();
        animalTransition.stop();
        Main main = new Main();
        main.goToMenu(stage);
    }
    private void animationsPause(){
        animalTransition.pause();
        if (manager.welling())
        wellTransition.pause();
        if (manager.eggPowderPlant.work)
        eggpowderTransition.pause();
        if (manager.weavingFactory.work)
            weavingTransition.pause();
        if (manager.bakery.work)
            bakeryTransition.pause();
        if (manager.sewingFactory.work)
            sewingTransition.pause();
        if (manager.icecreamFactory.work)
            icecreamTransition.pause();
    }
    private void animationsResume(){
        animalTransition.playFromStart();
        if (manager.welling())
        wellTransition.play();
        if (manager.eggPowderPlant.work)
        eggpowderTransition.play();
        if (manager.weavingFactory.work)
            weavingTransition.play();
        if (manager.bakery.work)
            bakeryTransition.play();
        if (manager.sewingFactory.work)
            sewingTransition.play();
        if (manager.icecreamFactory.work)
            icecreamTransition.play();
    }
}
