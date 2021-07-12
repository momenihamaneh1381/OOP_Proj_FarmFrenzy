import com.google.gson.Gson;
import com.google.gson.GsonBuilder;


import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class InputProcessor {

    public InputProcessor() {
        account = new Account();
        listOfWholeAccounts = new ListOfAccounts();
        missionRead();
    }

    Account account ;
    ListOfAccounts listOfWholeAccounts;
    Manager manager;
    Scanner scanner = new Scanner(System.in);
    String input = "";
    Missions missions ;
    Task task;
    public void run() {
        listOfWholeAccounts.load();
        enterGame();
    }
    private void enterGame() {
        while (!input.equalsIgnoreCase("login")&&!input.equalsIgnoreCase("signup")&&!input.equalsIgnoreCase("exit")){
            System.out.println("Enter LOGIN or SIGNUP: ");
            input = scanner.nextLine();
        }
        if(input.equalsIgnoreCase("login")){
            System.out.println("Enter your UserName:");
            input = scanner.nextLine();
            if (listOfWholeAccounts ==null)
                listOfWholeAccounts = new ListOfAccounts();
            else
                while (usernameContains(input)){
                    System.out.println("this userName does not exist!");
                    System.out.println("Enter your UserName:");
                    input = scanner.nextLine();
                }
            for (Account account1 : listOfWholeAccounts.accounts) {
                if (input.equalsIgnoreCase(account1.username))
                    account = account1;
            }
            System.out.println("Enter your password:");
            input = scanner.nextLine();
            while (!input.equalsIgnoreCase(account.password)){
                System.out.println("password is wrong!");
                account.logSave("Error" ,  "Wrong password");
                System.out.println("Enter your password:");
                input = scanner.nextLine();
            }
            account.logSave("Info" ,  "login successfuly");
            menu();
        }else if (input.equalsIgnoreCase("signup")){
            System.out.println("Enter your UserName:");
            input = scanner.nextLine();
            if (listOfWholeAccounts ==null)
                listOfWholeAccounts = new ListOfAccounts();
            else
            while (!usernameContains(input)){
                System.out.println("this userName already exist!");
                System.out.println("Enter your UserName:");
                input = scanner.nextLine();
            }
            account.username = input;
            System.out.println("Enter your password:");
            input = scanner.nextLine();
            account.password = input;
            account.completeLevels=1;
            account.coins =missions.tasks[0].initialCoins; // TODO: 6/14/2021 initial coins
            account.logSave("Info" , "account created");
            listOfWholeAccounts.accounts.add(account);
            listOfWholeAccounts.save();
            account.logSave("Info" ,  "Entered the game");
            menu();
        }else if (input.equalsIgnoreCase("exit")){
            listOfWholeAccounts.save();
            return;
        }
    }

    private void menu() {
        manager = new Manager(account);
        System.out.print("Menu: ");
        System.out.println("Enter start , logout , settings or exit:");
        input = scanner.next();
        while (!input.equalsIgnoreCase("start")&&!input.equalsIgnoreCase("logout")&&!input.equalsIgnoreCase("settings")&&!input.equalsIgnoreCase("exit")){
            System.out.println("invalid command!");
            System.out.println("Enter start , logout , settings or exit:");
            input = scanner.next();
        }
        while (!input.equalsIgnoreCase("exit")) {
            if (input.equalsIgnoreCase("start")) {
                int level = scanner.nextInt();
                if (level > account.completeLevels) {
                    System.out.println("Error! this level is locked");
                    account.logSave("Error" , "this level is locked");
                }else if (level!=1){
                    account.coins += missions.tasks[level-1].initialCoins;
                    account.logSave("Info" , "game start");
                    System.out.println("game start");
                    gameInput(level);
                }else{
                    account.coins = missions.tasks[level-1].initialCoins;
                    account.logSave("Info" , "game start");
                    System.out.println("game start");
                    gameInput(level);
                }
                // TODO: 6/15/2021  starting game with level
            } else if (input.equalsIgnoreCase("logout")) {
                account.logSave("Info", "logout");
                account = new Account();
                input = "";
                enterGame();
            } else if (input.equalsIgnoreCase("settings")) {
                // TODO: 6/15/2021  settings
            } else if (input.equalsIgnoreCase("exit"))
                return;
            else
                invalid();

            input = scanner.next();
        }   // TODO: 6/14/2021
        account.logSave("Info", "exit from menu");
        listOfWholeAccounts.save();
        return;
    }


    private void missionRead(){
        FileOperator fileOperator = new FileOperator();
        String json =  fileOperator.read("missions.json");
        Gson gson = new Gson();

        // convert JSON string to Book object
        missions = gson.fromJson(json, Missions.class);
    }

    public void gameInput(int level) {
        task = missions.tasks[level-1];
        Pattern patternBuy = Pattern.compile("(?i)buy\\s(\\w+)");
        Pattern patternPickup = Pattern.compile("(?i)PICKUP\\s(\\d+)\\s(\\d+)");
        Pattern patternWell = Pattern.compile("(?i)well");
        Pattern patternPlant = Pattern.compile("(?i)plant\\s(\\d+)\\s(\\d+)");
        Pattern patternFactory = Pattern.compile("(?i)work\\s(\\w+)");
        Pattern patternCage = Pattern.compile("(?i)cage\\s(\\d+)\\s(\\d+)");
        Pattern patternTurn = Pattern.compile("(?i)turn\\s(\\d+)");
        Pattern patternInquiry = Pattern.compile("(?i)inquiry");
        // truck patterns
        Pattern patternTruckLoad = Pattern.compile("(?i)truck\\sload\\s(\\w+)");
        Pattern patternTruckUnLoad = Pattern.compile("(?i)truck\\sunload\\s(\\w+)");
        Pattern patternTruckGo = Pattern.compile("(?i)truck\\sgo");

        Pattern patternBuild = Pattern.compile("(?i)build\\s(\\w+)");
        Pattern patternPickupCaged = Pattern.compile("(?i)PICKUPwildanimal\\s(\\d+)\\s(\\d+)");
        Pattern patternUpgrade = Pattern.compile("(?i)upgrade\\s(\\w+)");


        input = scanner.nextLine();
        while (!input.equalsIgnoreCase("exit") && !manager.endGame(task)) {
            input = scanner.nextLine();
            Matcher matcherBuy = patternBuy.matcher(input);
            Matcher matcherPickup = patternPickup.matcher(input);
            Matcher matcherWell = patternWell.matcher(input);
            Matcher matcherPlant = patternPlant.matcher(input);
            Matcher matcherFactory = patternFactory.matcher(input);
            Matcher matcherCage = patternCage.matcher(input);
            Matcher matcherTurn = patternTurn.matcher(input);
            Matcher matcherInquiry = patternInquiry.matcher(input);
            // truck matchers
            Matcher matcherTruckLoad = patternTruckLoad.matcher(input);
            Matcher matcherTruckUnLoad = patternTruckUnLoad.matcher(input);
            Matcher matcherTruckGo = patternTruckGo.matcher(input);

            Matcher matcherBuild = patternBuild.matcher(input);
            Matcher matcherUpgarde = patternUpgrade.matcher(input);
            Matcher matcherPickupWildAnimal = patternPickupCaged.matcher(input);
            if (matcherBuy.find()){
                if(manager.buy(matcherBuy.group(1))){
                    System.out.println(matcherBuy.group(1) + " buy successfuly!");
                    account.logSave("Info" , "buy successfuly");
                }else{
                    System.out.println("coins is not enough!");// TODO: 6/16/2021
                    account.logSave("Error" , "buy not successfuly! coins is not enough");
                }
            }else if (matcherPickup.find()){
                if (manager.pickUp(matcherPickup.group(1) , matcherPickup.group(2))){
                    System.out.println("pickup successfuly!");
                    account.logSave("Info" , "pickup successfuly");
                }else{
                    System.out.println("An error occured!");
                    account.logSave("Error" , "pickup not successfuly");
                }
            }else if (matcherWell.find()){
                if (manager.welling()){
                    System.out.println("well unsuccessfuly! beacase already is welling ");
                    account.logSave("Error" , "well unsuccessfuly");
                }
                else if(manager.well()){
                    System.out.println("well successfuly! please wait 3 section of time!");
                    account.logSave("Info" , "well successfuly");
                }else {
                    System.out.println("well unsuccessfuly! beacase well is not empty!");
                    account.logSave("Error" , "well unsuccessfuly! well is not empty");
                }
            }else if (matcherUpgarde.find()){
                if (manager.upgrade(matcherUpgarde.group(1))){
                    System.out.println(matcherUpgarde.group(1)+" upgrade successfuly!");
                    account.logSave("Info" , "upgrade successfuly");
                }else {
                    System.out.println("this factory does not exist! at first biuld factory then upgrade it!");
                    account.logSave("Error" , "this factory does not exist");
                }
            }else if (matcherPlant.find()){
                if (manager.plant(matcherPlant.group(1) , matcherPlant.group(2))){
                    System.out.println("plant successfuly!");
                    account.logSave("Info" , "plant successfuly");
                }else {
                    System.out.println("An error occured!");
                    account.logSave("Error" , "plant not successfuly! An error occured");
                }
            }else if (matcherFactory.find()){
                if (manager.work(matcherFactory.group(1))){
                    System.out.println("workshop worked successfuly!");
                    account.logSave("Info" , "workshop worked successfuly");
                }else {
                    System.out.println("An error occured!");
                    account.logSave("Error" , "workshop does not work! An error occured");
                }
            }else if (matcherCage.find()){
                if (manager.cage(matcherCage.group(1) , matcherCage.group(2))){
                    System.out.println("caged successfuly!");
                    account.logSave("Info" , "caged successfuly");
                    if (manager.pickUpCage(matcherCage.group(1) , matcherCage.group(2))){
                        System.out.println("wildAnimal pickup successfuly!");
                        account.logSave("Info" , "wildAnimal pickup successfuly");
                    }else if (manager.caged(matcherCage.group(1) , matcherCage.group(2))){
                        System.out.println("wildAnimal does not stored! capacity is not enough!");
                        account.logSave("Error" , "wildAnimal does not stored! capacity is not enough");
                    }
                }else{
                    System.out.println("An error occured!");
                    account.logSave("Error" , "cage not successfuly");
                }
            }else if (matcherPickupWildAnimal.find()){
                if (manager.pickUpCage(matcherPickupWildAnimal.group(1) , matcherPickupWildAnimal.group(2))){
                    System.out.println("wildAnimal pickup successfuly!");
                    account.logSave("Info" , "wildAnimal pickup successfuly");
                }else {
                    System.out.println("wildAnimal does not stored! capacity is not enough!");
                    account.logSave("Error" , "wildAnimal does not stored! capacity is not enough");
                }
            }else if (matcherTurn.find()){
                if (manager.turn(matcherTurn.group(1) , task)){
                    System.out.println("please plant! there is no grass and some of animals are in danger!");
                }
                inquiry();
            }else if (matcherTruckLoad.find()){
                if (manager.truckLoad(matcherTruckLoad.group(1))){
                    System.out.println("truck loaded successfuly!");
                    account.logSave("Info" , "truck loaded successfuly");
                }else {
                    account.logSave("Error" , "truck loaded Unsuccessfuly");
                    System.out.println("An error occured!");
                }
            }else if (matcherTruckUnLoad.find()){
                if (manager.truckUnLoad(matcherTruckUnLoad.group(1))){
                    System.out.println("truck unLoaded successfuly!");
                    account.logSave("Info" , "truck unLoaded successfuly");
                }else{
                    account.logSave("Error" , "truck unloaded Unsuccessfuly");
                    System.out.println("An error occured!");
                }
            }else if (matcherTruckGo.find()){
                if (manager.truckGo()){
                    account.logSave("Info" , "truckGo successfuly");
                    System.out.println("truckGo successfuly!");
                }else {
                    account.logSave("Error" , "truckGo Unsuccessfuly! truck is empty");
                    System.out.println("truck is empty!");
                }
            }else if (matcherInquiry.find()){
                inquiry();
            }else if (matcherBuild.find()){
                if (manager.build(matcherBuild.group(1))){
                    account.logSave("Info" , "workshop built successfuly");
                    System.out.println("Factory build successfuly!");
                }else{
                    System.out.println("coins is not enough! ");
                    account.logSave("Error" , "workshop built Unsuccessfuly! coins is not enough");
                }
            }else {
                invalid();
            }
        }
        if (manager.endGame(task)){
            if (level== account.completeLevels)
                account.completeLevels++;
            System.out.println(level+ "complete successfuly!");
            account.logSave("Info" , level+ " complete successfuly");
            if (manager.time<= task.max_time){
                account.coins= task.prize;
                System.out.println("you win the prizeCoin!");
            }
            listOfWholeAccounts.save();
            menu();
        }
        else {
            account.logSave("Info" , "exit from game");
            return;
        }
    }

    private void inquiry(){
        int [][]ground = new int[6][6];

        for (Grass grass : manager.grasses) {
            ground[grass.y-1][grass.x-1]+=1;
        }
        System.out.println("time: "+manager.time);
        for (int i = 0; i < 6; i++) {
            System.out.println("+---+---+---+---+---+---+");
            for (int j = 0; j < 6; j++) {
            System.out.print("| "+ground[i][j]+" ");
            }
            System.out.print("|");
            System.out.println();
        }
        System.out.println("+---+---+---+---+---+---+");
        if(manager.plaesePlant())
            System.out.println("please plant! there is no grass and some of animals are in danger!");


        for (DomesticAnimal domesticAnimal : manager.domesticAnimals) {
            System.out.println(domesticAnimal.animalType+" "+domesticAnimal.live+"% ["+domesticAnimal.x+" , "+domesticAnimal.y+"]");
        }
        for (WildAnimal wildAnimal : manager.wildAnimals) {
            System.out.println(wildAnimal.animalType+" "+wildAnimal.cage+" ["+wildAnimal.x+" , "+wildAnimal.y+"]");
        }
        for (Cat cat : manager.cats) {
            System.out.println(cat.animalType+" ["+cat.x+" , "+cat.y+"]");
        }
        for (Hound hound : manager.hounds) {
            System.out.println(hound.animalType+" ["+hound.x+" , "+hound.y+"]");
        }
        for (Product product : manager.products) {
            System.out.println(product.productType+ " ["+product.x+" , "+product.y+"]");
        }

        if (task.goalCoins!=0){
            System.out.println("coins: "+manager.account.coins+"/"+task.goalCoins);
        }
        else System.out.println("coins: "+ account.coins);
        if (task.goalDomesticAnimal!=null){
            System.out.println(task.goalDomesticAnimal.animalType+": "+manager.numOfDomesticAnimal(task.goalDomesticAnimal.animalType)+"/"+task.numOfGoalDomesticAnimal);
        }
        if (task.goalProduct!=null){
            System.out.println(task.goalProduct.productType+": "+manager.numOfProduct(task.goalProduct.productType)+"/"+task.numOfGoalProduct);
        }
        // TODO: 6/17/2021 sout tasks
    }

    private void invalid() {
        System.out.println("invalid command!");
    }

    private boolean usernameContains(String input) {
        for (Account account1 : listOfWholeAccounts.accounts) {
            if (account1.username.equalsIgnoreCase(input))
                return false;
        }
        return true;
    }

}
class Account{
    String username;
    String password;
    int completeLevels;
    int coins;

    public void logSave(String type , String log){
        try {
            LocalDateTime myDateObj  = LocalDateTime.now();
            DateTimeFormatter myFormatObj = DateTimeFormatter.ofPattern("dd-MM-yyyy - HH:mm:ss");
            String date = myDateObj.format(myFormatObj);
            FileWriter fileWriter = new FileWriter("log.txt" , true);
            fileWriter.append("["+type+"]" +" , ");
            fileWriter.append( date + " , ");
            fileWriter.append(log+" , ");
            fileWriter.append("username:"+this.username+" , ");
            fileWriter.append("\n");
            fileWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
class ListOfAccounts{
    List<Account> accounts ;
    public ListOfAccounts(){
        accounts = new ArrayList<>();
    }


    public void save(){
        GsonBuilder gsonBuilder = new GsonBuilder().setPrettyPrinting();
        Gson gson = gsonBuilder.create();
        String userListText = gson.toJson(this);

        FileOperator fileOperator = new FileOperator();
        fileOperator.write("users.json", userListText, false);
    }

    public static void existFiles(){
        File file = new File("users.json");
        if (!file.exists()){
            GsonBuilder gsonBuilder = new GsonBuilder().setPrettyPrinting();
            Gson gson = gsonBuilder.create();
            String userListText = "";
            FileOperator fileOperator = new FileOperator();
            fileOperator.write("users.json", userListText, false);
        }
    }

    public void load(){
        FileOperator fileOperator = new FileOperator();
        String userListText =  fileOperator.read("users.json");
        ListOfAccounts listOfAccounts = new Gson().fromJson(userListText, ListOfAccounts.class);
        if(listOfAccounts !=null) {
            if (listOfAccounts.accounts != null)
                this.accounts = listOfAccounts.accounts;
        }
    }


}