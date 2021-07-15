package classes;

import com.google.gson.Gson;
import graphicPackage.Game;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;

import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Random;


public class Manager {
    int time;
    Store store ;
    Account account;
    Truck truck;
    public ArrayList<DomesticAnimal> domesticAnimals;
    public ArrayList<Cat> cats;
    public ArrayList<Hound> hounds;
    public ArrayList<WildAnimal> wildAnimals;
    public ArrayList<Product> products;
    public ArrayList<Factory> factories;
    public ArrayList<Grass> grasses;
    WateringSystem wateringSystem;
    int maxProduct;
    int maxAnimal;
    int maxCoin;
    Pane pane;
    Task task;
    Label wellSuccessLabel , storeSuccess , storeFail;
    public Manager(Account account , Pane pane  , Label wellSuccessLabel , Label storeSuccess  , Label storeFail) {
        time = 0;
        this.storeSuccess = storeSuccess;
        this.storeFail = storeFail;
        this.wellSuccessLabel = wellSuccessLabel;
         maxProduct=0;
         maxAnimal=0;
         maxCoin=0;
        this.account = account;
        truck = Truck.getInstance();
        domesticAnimals = new ArrayList<>();
        wildAnimals = new ArrayList<>();
        products = new ArrayList<>();
        factories = new ArrayList<>();
        cats = new ArrayList<>();
        hounds = new ArrayList<>();
        store = Store.getInstanceStore();
        grasses = new ArrayList<>();
        wateringSystem = WateringSystem.getInstanceWateringSystem();
        this.pane = pane;

        // TODO: 7/15/2021
        int [] a = {1};
        task = new Task(100 , a , 3000 , 50 , 200 );
        task.wildAnimals.add(new Tiger(pane));
        // TODO: 7/15/2021 task & missions
    }

    public void missionRead(Missions missions){
//        FileOperator fileOperator = new FileOperator();
//        String json =  fileOperator.read("missions.json");
//        Gson gson = new Gson();
//        missions = new Missions(5);
//        // convert JSON string to Mission object
//        missions = gson.fromJson(json, Missions.class);
        try {
            // create Gson instance
            Gson gson = new Gson();

            // create a reader
            Reader reader = Files.newBufferedReader(Paths.get("missions.json"));

            // convert JSON string to Book object
            Missions mission = gson.fromJson(reader, Missions.class);
            missions = mission;
            // print book

            // close reader
            System.out.println(missions.levels);
            reader.close();

        } catch (Exception ex) {
            System.out.println(1);
        }
    }

    public boolean buy(String animalName) {
        if (animalName.equalsIgnoreCase("hen")){
            if (account.coins>=100) {
//                Hen hen = new Hen(pane);
//                domesticAnimals.add(hen);
                account.coins-=100;
                return true;
                 }return false;
        }else if (animalName.equalsIgnoreCase("turkey")){
                if (account.coins>=200){
//                    Turkey turkey = new Turkey(pane);
//                    domesticAnimals.add(turkey);
                    account.coins-=200;
                    return true;
                }return false;
        }else if (animalName.equalsIgnoreCase("buffalo")){
            if (account.coins>=400) {
//                Buffalo buffalo = new Buffalo(pane);
//                domesticAnimals.add(buffalo);
                account.coins-=400;
                return true;
            }return false;
        }else if (animalName.equalsIgnoreCase("cat")){
            if (account.coins>=150) {
//                Cat cat = new Cat(pane);
//                cats.add(cat);
                account.coins-=150;
                return true;
            }return false;
        }else if (animalName.equalsIgnoreCase("hound")){
            if (account.coins>=100) {
//                Hound hound = new Hound(pane);
//                hounds.add(hound);
                account.coins-=100;
                return true;
            }return false;
        }return false;

    }

    public boolean pickUp(String x0, String y0) {
        int x = Integer.parseInt(x0);
        int y = Integer.parseInt(y0);
        if (x>6||y>6||x<1||y<1)
            return false;
        else {
            ArrayList<Product> goodProduct = new ArrayList<>();
            for (Product product : products) {
                if (product.centerX==x&&product.centerY==y)
                    goodProduct.add(product);
            }
            if (goodProduct.isEmpty())
                return false;
            else {
                for (Product product : goodProduct) {
                    if (store.canAdd(product.capacity)){
                        products.remove(product);
                        store.add(product);
                        return true;
                    }
                }return false;
            }
        }
    }

    public void pickUp(Product product) {
            products.remove(product);
            store.add(product);
            pane.getChildren().remove(product);
    }

    public boolean well() {
        if (wateringSystem.capacity!=0){
            return false;
        }
        else {
            wateringSystem.well = true;
            return true;
        }
    }

    public boolean plant(String x0, String y0) {
        int x = Integer.parseInt(x0);
        int y = Integer.parseInt(y0);
        if (x>6||y>6||x<1||y<1)
            return false;
        else if (wateringSystem.capacity>0){
            Grass grass = new Grass(x, y);
            grasses.add(grass);
            wateringSystem.capacity--;
            return true;
        }else return false;
    }

    public boolean cage(String x0, String y0) {
        int x = Integer.parseInt(x0);
        int y = Integer.parseInt(y0);
        if (x>6||y>6||x<1||y<1)
            return false;
        else{
            for (WildAnimal wildAnimal : wildAnimals) {
                if (wildAnimal.x==x && wildAnimal.y ==y){
                    wildAnimal.cage++;
                    return true;
                }
            }
            return false;
        }
    }

    public boolean pickUpCage(String x0, String y0) {
        // TODO: 6/16/2021 بازبینی
        int x = Integer.parseInt(x0);
        int y = Integer.parseInt(y0);
        if (x>6||y>6||x<1||y<1)
            return false;
        else {
        for (WildAnimal wildAnimal : wildAnimals) {
            if (wildAnimal.x == x && wildAnimal.y == y) {
                if (wildAnimal.cage == wildAnimal.max_cage) {
                    if (store.canAdd(wildAnimal)) {
                        wildAnimals.remove(wildAnimal);
                        store.add(wildAnimal);
                        return true;
                    }
                }
            }
        }
        }
            return false;
    }

    public boolean build(String workshopName) {
        if (workshopName.equalsIgnoreCase("eggPowderPlant")){
            if (account.coins>=150){
                EggPowderPlant eggPowderPlant = new EggPowderPlant();
                factories.add(eggPowderPlant);
                account.coins-=150;
                return true;
            }return false;
        }else if (workshopName.equalsIgnoreCase("weavingFactory")){
            if (account.coins>=250){
                WeavingFactory weavingFactory = new WeavingFactory();
                factories.add(weavingFactory);
                account.coins-=250;
                return true;
            }return false;
        }else if (workshopName.equalsIgnoreCase("pocketmilkfactory")){
            if (account.coins>=400){
                PocketMilkFactory pocketmilkfactory = new PocketMilkFactory();
                factories.add(pocketmilkfactory);
                account.coins-=400;
                return true;
            }return false;
        }else if (workshopName.equalsIgnoreCase("bakery")){
            if (account.coins>=250){
                Bakery bakery = new Bakery();
                factories.add(bakery);
                account.coins-=250;
                return true;
            }return false;
        }else if (workshopName.equalsIgnoreCase("sewingFactory")){
            if (account.coins>=400){
                SewingFactory sewingFactory = new SewingFactory();
                factories.add(sewingFactory);
                account.coins-=400;
                return true;
            }return false;
        }else if (workshopName.equalsIgnoreCase("IcecreamFactory")){
            if (account.coins>=550){
                IcecreamFactory icecreamFactory = new IcecreamFactory();
                factories.add(icecreamFactory);
                account.coins-=550;
                return true;
            }return false;
        }return false;
    }

    public boolean truckLoad(String itemName) {
        if (truck.isGo)
            return false;
        if (itemName.equalsIgnoreCase("wildAnimal")){
            if (truck.MAX_CAPACITY - truck.capacity >=15){
                if (!store.wildAnimalsStoreList.isEmpty()){
                    WildAnimal wild = store.wildAnimalsStoreList.get(0);
                    store.remove(wild);
                    truck.add(wild);
                    return true;
                }return false;
            }return false;
        }else if (itemName.equalsIgnoreCase("egg")){
            if (truck.MAX_CAPACITY - truck.capacity >=1){
                ArrayList<Product> eggs = new ArrayList<>();
                for (Product product : store.productsStoreList) {
                    if (product.productType == ProductType.EGG)
                        eggs.add(product);
                }
                if (!eggs.isEmpty()){
                    Product egg = eggs.get(0);
                    store.remove(egg);
                    truck.add(egg);
                    return true;
                }return false;
            }return false;
        }else if (itemName.equalsIgnoreCase("milk")){
            if (truck.MAX_CAPACITY - truck.capacity >=1){
                ArrayList<Product> milks = new ArrayList<>();
                for (Product product : store.productsStoreList) {
                    if (product.productType == ProductType.MILK)
                        milks.add( product);
                }
                if (!milks.isEmpty()){
                    Product milk = milks.get(0);
                    store.remove(milk);
                    truck.add(milk);
                    return true;
                }return false;
            }return false;
        }else if (itemName.equalsIgnoreCase("wing")){
            if (truck.MAX_CAPACITY - truck.capacity >=1){
                ArrayList<Product> wings = new ArrayList<>();
                for (Product product : store.productsStoreList) {
                    if (product.productType == ProductType.WING)
                        wings.add( product);
                }
                if (!wings.isEmpty()){
                    Product wing = wings.get(0);
                    store.remove(wing);
                    truck.add(wing);
                    return true;
                }return false;
            }return false;
        }else if (itemName.equalsIgnoreCase("fabric")){
            if (truck.MAX_CAPACITY - truck.capacity >=2){
                ArrayList<Product> products1 = new ArrayList<>();
                for (Product product : store.productsStoreList) {
                    if (product.productType == ProductType.FABRIC)
                        products1.add(product);
                }
                if (!products1.isEmpty()){
                    Product product1= products1.get(0);
                    store.remove(product1);
                    truck.add(product1);
                    return true;
                }return false;
            }return false;
        }else if (itemName.equalsIgnoreCase("powder")){
            if (truck.MAX_CAPACITY - truck.capacity >=2){
                ArrayList<Product> products1 = new ArrayList<>();
                for (Product product : store.productsStoreList) {
                    if (product.productType == ProductType.POWDER)
                        products1.add(product);
                }
                if (!products1.isEmpty()){
                    Product product1= products1.get(0);
                    store.remove(product1);
                    truck.add(product1);
                    return true;
                }return false;
            }return false;
        }else if (itemName.equalsIgnoreCase("POCKETMILK")){
            if (truck.MAX_CAPACITY - truck.capacity >=2){
                ArrayList<Product> products1 = new ArrayList<>();
                for (Product product : store.productsStoreList) {
                    if (product.productType == ProductType.POCKET_MILK)
                        products1.add(product);
                }
                if (!products1.isEmpty()){
                    Product product1= products1.get(0);
                    store.remove(product1);
                    truck.add(product1);
                    return true;
                }return false;
            }return false;
        }else if (itemName.equalsIgnoreCase("bread")){
            if (truck.MAX_CAPACITY - truck.capacity >=4){
                ArrayList<Product> products1 = new ArrayList<>();
                for (Product product : store.productsStoreList) {
                    if (product.productType == ProductType.BREAD)
                        products1.add(product);
                }
                if (!products1.isEmpty()){
                    Product product1= products1.get(0);
                    store.remove(product1);
                    truck.add(product1);
                    return true;
                }return false;
            }return false;
        }else if (itemName.equalsIgnoreCase("CLOTH")){
            if (truck.MAX_CAPACITY - truck.capacity >=4){
                ArrayList<Product> products1 = new ArrayList<>();
                for (Product product : store.productsStoreList) {
                    if (product.productType == ProductType.CLOTH)
                        products1.add(product);
                }
                if (!products1.isEmpty()){
                    Product product1= products1.get(0);
                    store.remove(product1);
                    truck.add(product1);
                    return true;
                }return false;
            }return false;
        }else if (itemName.equalsIgnoreCase("ICECREAM")){
            if (truck.MAX_CAPACITY - truck.capacity >=4){
                ArrayList<Product> products1 = new ArrayList<>();
                for (Product product : store.productsStoreList) {
                    if (product.productType == ProductType.ICE_CREAM)
                        products1.add(product);
                }
                if (!products1.isEmpty()){
                    Product product1= products1.get(0);
                    store.remove(product1);
                    truck.add(product1);
                    return true;
                }return false;
            }return false;
        }return false;
    }

    public boolean truckUnLoad(String itemName) {
        if (truck.isGo)
            return false;
        if (itemName.equalsIgnoreCase("wildAnimal")){
            if (store.MAX_CAPACITY - store.capacity >=15){
                if (!truck.listOfWildAnimals.isEmpty()){
                    WildAnimal wild = truck.listOfWildAnimals.get(0);
                    store.add(wild);
                    truck.remove(wild);
                    return true;
                }return false;
            }return false;
        }else if (itemName.equalsIgnoreCase("egg")){
            if (store.MAX_CAPACITY - store.capacity >=1){
                ArrayList<Product> eggs = new ArrayList<>();
                for (Product product : truck.listOfProducts) {
                    if (product.productType == ProductType.EGG)
                        eggs.add( product);
                }
                if (!eggs.isEmpty()){
                    Product egg = eggs.get(0);
                    store.add(egg);
                    truck.remove(egg);
                    return true;
                }return false;
            }return false;
        }else if (itemName.equalsIgnoreCase("milk")){
            if (store.MAX_CAPACITY - store.capacity >=1){
                ArrayList<Product> milks = new ArrayList<>();
                for (Product product : truck.listOfProducts) {
                    if (product.productType == ProductType.MILK)
                        milks.add( product);
                }
                if (!milks.isEmpty()){
                    Product milk = milks.get(0);
                    store.add(milk);
                    truck.remove(milk);
                    return true;
                }return false;
            }return false;
        }else if (itemName.equalsIgnoreCase("wing")){
            if (store.MAX_CAPACITY - store.capacity >=1){
                ArrayList<Product> wings = new ArrayList<>();
                for (Product product : truck.listOfProducts) {
                    if (product.productType == ProductType.WING)
                        wings.add( product);
                }
                if (!wings.isEmpty()){
                    Product wing = wings.get(0);
                    store.add(wing);
                    truck.remove(wing);
                    return true;
                }return false;
            }return false;
        }else if (itemName.equalsIgnoreCase("fabric")){
            if (store.MAX_CAPACITY - store.capacity >=2){
                ArrayList<Product> products1 = new ArrayList<>();
                for (Product product : truck.listOfProducts) {
                    if (product.productType == ProductType.FABRIC)
                        products1.add(product);
                }
                if (!products1.isEmpty()){
                    Product product1= products1.get(0);
                    store.add(product1);
                    truck.remove(product1);
                    return true;
                }return false;
            }return false;
        }else if (itemName.equalsIgnoreCase("powder")){
            if (store.MAX_CAPACITY - store.capacity >=2){
                ArrayList<Product> products1 = new ArrayList<>();
                for (Product product : truck.listOfProducts) {
                    if (product.productType == ProductType.POWDER)
                        products1.add(product);
                }
                if (!products1.isEmpty()){
                    Product product1= products1.get(0);
                    store.add(product1);
                    truck.remove(product1);
                    return true;
                }return false;
            }return false;
        }else if (itemName.equalsIgnoreCase("POCKETMILK")){
            if (store.MAX_CAPACITY - store.capacity >=2){
                ArrayList<Product> products1 = new ArrayList<>();
                for (Product product : truck.listOfProducts) {
                    if (product.productType == ProductType.POCKET_MILK)
                        products1.add(product);
                }
                if (!products1.isEmpty()){
                    Product product1= products1.get(0);
                    store.add(product1);
                    truck.remove(product1);
                    return true;
                }return false;
            }return false;
        }else if (itemName.equalsIgnoreCase("bread")){
            if (store.MAX_CAPACITY - store.capacity >=4){
                ArrayList<Product> products1 = new ArrayList<>();
                for (Product product : truck.listOfProducts) {
                    if (product.productType == ProductType.BREAD)
                        products1.add(product);
                }
                if (!products1.isEmpty()){
                    Product product1= products1.get(0);
                    store.add(product1);
                    truck.remove(product1);
                    return true;
                }return false;
            }return false;
        }else if (itemName.equalsIgnoreCase("CLOTH")){
            if (store.MAX_CAPACITY - store.capacity >=4){
                ArrayList<Product> products1 = new ArrayList<>();
                for (Product product : truck.listOfProducts) {
                    if (product.productType == ProductType.CLOTH)
                        products1.add(product);
                }
                if (!products1.isEmpty()){
                    Product product1= products1.get(0);
                    store.add(product1);
                    truck.remove(product1);
                    return true;
                }return false;
            }return false;
        }else if (itemName.equalsIgnoreCase("ICECREAM")){
            if (store.MAX_CAPACITY - store.capacity >=4){
                ArrayList<Product> products1 = new ArrayList<>();
                for (Product product : truck.listOfProducts) {
                    if (product.productType == ProductType.ICE_CREAM)
                        products1.add(product);
                }
                if (!products1.isEmpty()){
                    Product product1= products1.get(0);
                    store.add(product1);
                    truck.remove(product1);
                    return true;
                }return false;
            }return false;
        }return false;
    }

    public boolean truckGo() {
        if (truck.capacity==0||truck.isGo)
            return false;

            truck.isGo=true;
        return true;
    }

//    public boolean turn(int n ,Task task) {
//        account.logSave("Info" , "turned "+n0+" successfuly");
//        int n = Integer.parseInt(n0);
        public boolean turn(int n ) {
        ArrayList<DomesticAnimal> diedDomesticAnimal = new ArrayList<>();
        ArrayList<WildAnimal> diedWildAnimals = new ArrayList<>();
        ArrayList<Product> diedProducts = new ArrayList<>();
        ArrayList<Hound> diedHounds = new ArrayList<>();
        ArrayList<DomesticAnimal> hungryAnimals = new ArrayList<>();
        ArrayList<Grass> diedGrasses = new ArrayList<>();

        // TODO: 6/17/2021 locations check copy paste
        for (int i = 0; i < n; i++) {
            //create wildAnimal
            time++;
            for (int j = 0; j < task.wildAnimals.size(); j++) {
                if (time==task.timeOfWildAnimals[j]){
                    WildAnimal wildAnimal = new WildAnimal(task.wildAnimals.get(j).animalType , pane);
                    wildAnimals.add(wildAnimal);
                    pane.getChildren().add(wildAnimal);
                    account.logSave("Info" , wildAnimal.animalType+" created");
                }
            }

            diedDomesticAnimal.clear();
            diedWildAnimals.clear();
            diedProducts.clear();
            diedHounds.clear();
            hungryAnimals.clear();
            diedGrasses.clear();

            //domesticAnimals
            for (DomesticAnimal domesticAnimal : domesticAnimals) {
                domesticAnimal.turn();
            }
            //grasses
            diedGrasses.clear();
            hungryAnimals.clear();
            for (Grass grass : grasses) {
                hungryAnimals.clear();
                for (DomesticAnimal domesticAnimal : domesticAnimals) {
                    if (domesticAnimal.isCollissionGrass(grass)&&domesticAnimal.live<=50){
                        hungryAnimals.add(domesticAnimal);
                    }
                }
                if (!hungryAnimals.isEmpty()){
                minHungryAnimals(hungryAnimals);
                diedGrasses.add(grass);
                }
            }
            for (Grass diedGrass : diedGrasses) {
                grasses.remove(diedGrass);
                pane.getChildren().remove(diedGrass);
            }
            for (DomesticAnimal domesticAnimal : domesticAnimals) {
                if (domesticAnimal.live==0)
                    diedDomesticAnimal.add(domesticAnimal);
                if (domesticAnimal.time>= domesticAnimal.max_Time){
                    domesticAnimal.time =0;
                    Product product = new Product(domesticAnimal.productType , (int)domesticAnimal.x, (int)domesticAnimal.y );
                    products.add(product);
                    pane.getChildren().add(product);
                    account.logSave("Info" , "new "+product.productType+" created");
                }
            }
            for (DomesticAnimal animal : diedDomesticAnimal) {
                domesticAnimals.remove(animal);
                pane.getChildren().remove(animal);
                account.logSave("Info" , animal.animalType+" died from hungry");
            }
            //Wildanimals
            diedDomesticAnimal.clear();
            diedProducts.clear();
            for (WildAnimal wildAnimal : wildAnimals) {
                wildAnimal.turn();
                if (!wildAnimal.caged()){
                    for (DomesticAnimal domesticAnimal : domesticAnimals) {
                        if (domesticAnimal.isCollissionWildAnimals(wildAnimal)){
                            diedDomesticAnimal.add(domesticAnimal);
                            account.logSave("Info" , domesticAnimal.animalType+" disapeared with "+wildAnimal.animalType);
                        }
                    }
                    for (Product product : products) {
                        if (product.isCollissionWildAnimals(wildAnimal)){
                            diedProducts.add(product);
                            account.logSave("Info" , product.productType+" disapeared with "+wildAnimal.animalType);
                        }
                    }
                }
                // TODO: 6/21/2021 logsave
                if (wildAnimal.time>= wildAnimal.max_time){
                    diedWildAnimals.add(wildAnimal);
                    // TODO: 7/15/2021 escape from cage
                    account.logSave("Info" , "caged "+wildAnimal.animalType+" escaped with passing time");
                }
            }
            for (WildAnimal diedWildAnimal : diedWildAnimals) {
                wildAnimals.remove(diedWildAnimal);
                pane.getChildren().remove(diedWildAnimal);
                // TODO: 7/15/2021 escape from cage
            }
            for (DomesticAnimal domesticAnimal : diedDomesticAnimal) {
                domesticAnimals.remove(domesticAnimal);
                pane.getChildren().remove(domesticAnimal);
            }
            for (Product diedProduct : diedProducts) {
                products.remove(diedProduct);
                pane.getChildren().remove(diedProduct);
            }
            //cats
            diedProducts.clear();
            for (Cat cat : cats) {
                cat.turn();
                for (Product product : products) {
                    if (product.isCollissionCat(cat)){
                        diedProducts.add(product);
                    }
                }
            }
            for (Product diedProduct : diedProducts) {
                if (store.canAdd(diedProduct.capacity)){
                    pickUp(diedProduct);
                    account.logSave("Info" , "cat pickuped "+diedProduct.productType);
                }
            }
            //hounds
            diedWildAnimals.clear();
            for (Hound hound : hounds) {
                hound.turn();
                for (WildAnimal wildAnimal : wildAnimals) {
                    if (hound.isCollissionWildAnimals(wildAnimal)&&!wildAnimal.caged()){
                        account.logSave("Info" , wildAnimal.animalType+" and HOUND died");
                        diedWildAnimals.add(wildAnimal);
                        diedHounds.add(hound);
                    }
                }
            }
            for (WildAnimal diedWildAnimal : diedWildAnimals) {
                wildAnimals.remove(diedWildAnimal);
                pane.getChildren().remove(diedWildAnimal);
            }
            for (Hound diedHound : diedHounds) {
                hounds.remove(diedHound);
                pane.getChildren().remove(diedHound);
            }
            //watering system
            wateringSystem.turn();
            if (wateringSystem.well){
                if (wateringSystem.time>= wateringSystem.max_time){
                    wellSuccessLabel.setText("");
                    account.logSave("Info" , "well is ready ");
                    wateringSystem.time = 0;
                    wateringSystem.capacity = wateringSystem.MAX_CAPACITY;
                    wateringSystem.well = false;
                }
            }// TODO: 7/15/2021 welling animate 
            //products
            diedProducts.clear();
            for (Product product : products) {
                product.turn();
                if (product.time>=product.max_time){
                    diedProducts.add(product);
                    account.logSave("Info" , product.productType+" disapeared from passing of time");
                }
            }
            for (Product diedProduct : diedProducts) {
                products.remove(diedProduct);
                pane.getChildren().remove(diedProduct);
            }
            //workshops
            for (Factory factory : factories) {
                factory.turn();
                if (factory.work&&factory.time>=factory.max_time){
                    account.logSave("Info" , factory.factoryName+ " produce "+factory.productTypeOutput );

                    // TODO: 7/15/2021 factory
                    Random random = new Random();
                    int x = 1+random.nextInt(6);
                    int y = 1+random.nextInt(6);
                    factory.time = 0;
                    factory.work =false;
                    Product product = new Product(factory.productTypeOutput , x ,y);
                    products.add(product);
                    if (factory.level==2&&factory.numOfProduct==2){
                        factory.numOfProduct=1;
                        x = 1+random.nextInt(6);
                        y = 1+random.nextInt(6);
                        Product product1 = new Product(factory.productTypeOutput , x ,y);
                        products.add(product1);
                        account.logSave("Info" , factory.factoryName+ " produce "+factory.productTypeOutput +" , level = 2");
                    }
                }
            }
            //truck
            truck.turn();
            if (truck.isGo&&truck.time>=truck.MAX_TIME){
                account.logSave("Info" , "truck comed back! it is ready");
                account.coins+=truck.wholeCoins();
                truck.isGo=false;
                truck.time=0;
                truck.capacity=0;
                truck.clear();
            }


        }
        if(plaesePlant())
            account.logSave("Alarm" , "please plant! there is no grass and some of animals are hungry");
        return plaesePlant();
    }
    public boolean plaesePlant(){
        if (grasses.isEmpty()&&existHungry()){
            return true;
        }
        return false;
    }

    public boolean existHungry(){
        for (DomesticAnimal domesticAnimal : domesticAnimals) {
            if (domesticAnimal.live<=50)
                return true;
        }
        return false;
    }
    private void minHungryAnimals(ArrayList<DomesticAnimal> hungryAnimals) {
        int min=100;
        ArrayList<DomesticAnimal> minhungryAnimals = new ArrayList<>();
        for (DomesticAnimal hungryAnimal : hungryAnimals) {
            if (min>=hungryAnimal.live){
                if (min>hungryAnimal.live)
                minhungryAnimals.clear();
                min = hungryAnimal.live;
                minhungryAnimals.add(hungryAnimal);
            }
        }
        DomesticAnimal target = minhungryAnimals.get(0);
        target.live=100;
        account.logSave("Info" , target.animalType+" ate grass");
    }

    public boolean work(String workshopName) {
        ArrayList<Product> goodProduct = new ArrayList<>();
        if (workshopName.equalsIgnoreCase("eggPowderPlant")){
            for (Product product : store.productsStoreList) {
                if (product.productType == ProductType.EGG){
                    goodProduct.add(product);
                }
            }
            if (goodProduct.isEmpty()){
                return false;
            }
            for (Factory factory : factories) {
                if (factory.factoryName==FactoryName.EGG_POWDER_PLANT&&!factory.work){
                    if (factory.level==1||goodProduct.size()==1) {
                        startWork(factory, goodProduct.get(0));
                        return true;
                    }else {
                        startWork(factory, goodProduct.get(0) , goodProduct.get(1));
                        return true;
                    }
                }
            }
            return false;
        }else if (workshopName.equalsIgnoreCase("bakery")){
            for (Product product : store.productsStoreList) {
                if (product.productType == ProductType.POWDER){
                    goodProduct.add(product);
                }
            }
            if (goodProduct.isEmpty()){
                return false;
            }
            for (Factory factory : factories) {
                if (factory.factoryName==FactoryName.BAKERY&&!factory.work){
                    if (factory.level==1||goodProduct.size()==1) {
                        startWork(factory, goodProduct.get(0));
                        return true;
                    }else {
                        startWork(factory, goodProduct.get(0) , goodProduct.get(1));
                        return true;
                    }
                }
            }
            return false;
        }else if (workshopName.equalsIgnoreCase("icecreamFactory")){
            for (Product product : store.productsStoreList) {
                if (product.productType == ProductType.POCKET_MILK){
                    goodProduct.add(product);
                }
            }
            if (goodProduct.isEmpty()){
                return false;
            }
            for (Factory factory : factories) {
                if (factory.factoryName==FactoryName.ICECREAM_FACTORY&&!factory.work){
                    if (factory.level==1||goodProduct.size()==1) {
                        startWork(factory, goodProduct.get(0));
                        return true;
                    }else {
                        startWork(factory, goodProduct.get(0) , goodProduct.get(1));
                        return true;
                    }
                }
            }
            return false;
        }else if (workshopName.equalsIgnoreCase("pocketMilkFactory")){
            for (Product product : store.productsStoreList) {
                if (product.productType == ProductType.MILK){
                    goodProduct.add(product);
                }
            }
            if (goodProduct.isEmpty()){
                return false;
            }
            for (Factory factory : factories) {
                if (factory.factoryName==FactoryName.POCKET_MILK_FACTORY&&!factory.work){
                    if (factory.level==1||goodProduct.size()==1) {
                        startWork(factory, goodProduct.get(0));
                        return true;
                    }else {
                        startWork(factory, goodProduct.get(0) , goodProduct.get(1));
                        return true;
                    }
                }
            }
            return false;
        }else if (workshopName.equalsIgnoreCase("sewingFactory")){
            for (Product product : store.productsStoreList) {
                if (product.productType == ProductType.FABRIC){
                    goodProduct.add(product);
                }
            }
            if (goodProduct.isEmpty()){
                return false;
            }
            for (Factory factory : factories) {
                if (factory.factoryName==FactoryName.SEWING_FACTORY&&!factory.work){
                    if (factory.level==1||goodProduct.size()==1) {
                        startWork(factory, goodProduct.get(0));
                        return true;
                    }else {
                        startWork(factory, goodProduct.get(0) , goodProduct.get(1));
                        return true;
                    }
                }
            }
            return false;
        }else if (workshopName.equalsIgnoreCase("weavingFactory")){
            for (Product product : store.productsStoreList) {
                if (product.productType == ProductType.WING){
                    goodProduct.add(product);
                }
            }
            if (goodProduct.isEmpty()){
                return false;
            }
            for (Factory factory : factories) {
                if (factory.factoryName==FactoryName.WEAVING_FACTORY&&!factory.work){
                    if (factory.level==1||goodProduct.size()==1) {
                        startWork(factory, goodProduct.get(0));
                        return true;
                    }else {
                        startWork(factory, goodProduct.get(0) , goodProduct.get(1));
                        return true;
                    }
                }
            }
            return false;
        }else return false;
    }

    public boolean upgrade(String workshopName) {

        if (workshopName.equalsIgnoreCase("eggPowderPlant")) {
            for (Factory factory : factories) {
                if (factory.factoryName == FactoryName.EGG_POWDER_PLANT && !factory.work) {
                    factory.upgrade();
                    return true;
                }
            }
            return false;
        } else if (workshopName.equalsIgnoreCase("bakery")) {
            for (Factory factory : factories) {
                if (factory.factoryName == FactoryName.BAKERY && !factory.work) {
                    factory.upgrade();
                    return true;
                }
            }
            return false;
        } else if (workshopName.equalsIgnoreCase("icecreamFactory")) {
            for (Factory factory : factories) {
                if (factory.factoryName == FactoryName.ICECREAM_FACTORY && !factory.work) {
                    factory.upgrade();
                    return true;
                }
            }
            return false;
        } else if (workshopName.equalsIgnoreCase("pocketMilkFactory")) {
            for (Factory factory : factories) {
                if (factory.factoryName == FactoryName.POCKET_MILK_FACTORY && !factory.work) {
                    factory.upgrade();
                    return true;
                }
            }
            return false;
        } else if (workshopName.equalsIgnoreCase("sewingFactory")) {
            for (Factory factory : factories) {
                if (factory.factoryName == FactoryName.SEWING_FACTORY && !factory.work) {
                    factory.upgrade();
                    return true;
                }
            }
            return false;
        } else if (workshopName.equalsIgnoreCase("weavingFactory")) {
            for (Factory factory : factories) {
                if (factory.factoryName == FactoryName.WEAVING_FACTORY && !factory.work) {
                    factory.upgrade();
                    return true;
                }
            }
        }
            return false;
    }
    public void startWork(Factory factory , Product product) {
        store.remove(product);
        factory.work = true;
    }

    private void startWork(Factory factory , Product product1 ,Product product2){
        store.remove(product1);
        store.remove(product2);
        factory.numOfProduct=2;
        factory.work = true;
    }
    public int numOfProduct(ProductType productType){
        int n=0;
        for (Product product : store.productsStoreList) {
            if (product.productType==productType)
                n++;
        }
        return n;
    }

    public int numOfDomesticAnimal(AnimalType animalType){
        int n=0;
        for (DomesticAnimal domesticAnimal : domesticAnimals) {
            if (domesticAnimal.animalType==animalType)
                n++;
        }
        return n;
    }

    public boolean endGame(Task task) {
        // TODO: 6/19/2021
        if (goalCoins(task)&&goalDomesticAnimal(task)&&goalProduct(task))
            return true;
        return false;
//        goalDomesticAnimal = task.goalDomesticAnimal;
//        goalProduct = task.goalProduct;
//        if (task.goalCoins!=0){
//            if (account.coins>=task.goalCoins){
//                if (task.goalDomesticAnimal==null){
//                    if (task.goalProduct==null){
//                        return true;
//                    }else{
//                        if (task.numOfGoalProduct<=numOfProduct(task.goalProduct.productType)){
//                            return true;
//                        }return false;
//                    }
//                }else {
//                    if (task.numOfGoalDomesticAnimal<=numOfDomesticAnimal(task.goalDomesticAnimal.animalType)){
//                        if ()
//                    }
//
//                }
//            }else {
//
//            }
//
//        }return false;
    }
    public boolean goalCoins(Task task){
        if (task.goalCoins!=0){
//            if (maxCoin<account.coins)
//            maxCoin = account.coins;
            if (account.coins>= task.goalCoins)
                return true;
            return false;
        }
        return true;
    }
    public boolean goalDomesticAnimal(Task task){
        if (task.goalDomesticAnimal!=null){
            if (numOfDomesticAnimal(task.goalDomesticAnimal.animalType)>=task.numOfGoalDomesticAnimal)
                return true;
            return false;
        }
        return true;
    }
    public boolean goalProduct(Task task){
        if (task.goalProduct!=null){
//            if (maxProduct <numOfProduct(task.goalProduct.productType))
//            maxProduct = numOfProduct(task.goalProduct.productType);
            if (numOfProduct(task.goalProduct.productType)>=task.numOfGoalProduct)
                return true;
            return false;
        }
        return true;
    }

    public boolean caged(String x0, String y0) {
        int x = Integer.parseInt(x0);
        int y = Integer.parseInt(y0);
        for (WildAnimal wildAnimal : wildAnimals) {
            if (wildAnimal.caged()&&x==wildAnimal.x&&y==wildAnimal.y){
                return true;
            }
        }
        return false;
    }

    public boolean welling() {
        return wateringSystem.well;
    }


    public void intersectWildAnimal(int x, int y) {
        ArrayList<WildAnimal>died = new ArrayList<>();
        for (WildAnimal wildAnimal : wildAnimals) {
            if (!wildAnimal.caged()&&wildAnimal.intersect(x , y)){
                wildAnimal.cage++;
                account.logSave("Info" , wildAnimal.animalType+" caged successfuly");
            }else if (wildAnimal.caged()&&wildAnimal.intersect(x , y)){
                if (store.canAdd(wildAnimal)){
                    store.add(wildAnimal);
                    died.add(wildAnimal);
                    storeSuccess.setText(wildAnimal.animalType+" stored successfully");
                    account.logSave("Info" , wildAnimal.animalType+" stored successfully");
                }else {
                    storeFail.setText("capacity is not enough");
                    account.logSave("Error" , wildAnimal.animalType+" does not store! capacity is not enough");
                }
            }
        }
        for (WildAnimal wildAnimal : died) {
            wildAnimals.remove(wildAnimal);
            pane.getChildren().remove(wildAnimal);
        }
    }

    public void intersectProduct(int x, int y) {
        ArrayList<Product>died = new ArrayList<>();
        for (Product product : products) {
            if (product.intersect(x,y)){
                if (store.canAdd(product)){
                    store.add(product);
                    died.add(product);
                    storeSuccess.setText(product.productType+" stored successfully");
                    account.logSave("Info" , product.productType+" stored successfully");
                }else {
                    storeFail.setText("capacity is not enough");
                    account.logSave("Error" , product.productType+" does not store! capacity is not enough");
                }
            }
        }
        for (Product product : died) {
            products.remove(product);
            pane.getChildren().remove(product);
        }
    }

    public boolean existIntersect(int x, int y) {
        for (Product product : products) {
            if (product.intersect(x,y))
                return true;
        }
        for (WildAnimal wildAnimal : wildAnimals) {
            if (wildAnimal.intersect(x,y))
                return true;
        }
        return false;
    }
}
