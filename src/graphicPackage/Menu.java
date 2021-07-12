package graphicPackage;


import classes.Account;
import classes.ListOfAccounts;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.Scanner;

public class Menu   {
    Account account = new Account();
    @FXML
     Button okBtn;
    @FXML
     Label labelFail;
    @FXML
     TextField levelTxt;
    @FXML
     Button startBtn;
    @FXML
     Button settingBtn;
    @FXML
     Button logoutBtn;
    @FXML
     Button exitBtn;

    public void startGame( ) {
        levelTxt.setVisible(true);
        okBtn.setVisible(true);
    }

    public void setting( ) {
        // TODO: 7/12/2021
    }

    public void logout( ) throws IOException {
        Main main = new Main();
        main.changeScene("LoginOrSignUp.fxml" , "Login or SignUp");
    }

    public void exit( ) {
        Main main = new Main();
        main.closeScene();
        return;
    }

    public void startLevel( ) {
        initialize();
        if (levelTxt.getText().isEmpty()){
            labelFail.setVisible(true);
            labelFail.setText("Level field is empty!");
        }else {
            try {
                int level = Integer.parseInt(levelTxt.getText());
            if (account.getCompleteLevels()<level){
                labelFail.setVisible(true);
                labelFail.setText("This level is lock!");
            }else {
                FileWriter fileWriter = new FileWriter("username.txt" , true);
                fileWriter.append("\n" +levelTxt.getText() );
                fileWriter.close();
                Main main = new Main();
                main.startGame();
            }
            } catch (Exception e) {
                labelFail.setVisible(true);
                labelFail.setText("ooops! there is a problem!");
            }
        }
    }


    public void initialize() {
        ListOfAccounts listOfAccounts = new ListOfAccounts();
        listOfAccounts.load();
        File fileReader = new File("username.txt");
        Scanner scanner = null;
        try {
            scanner = new Scanner(fileReader);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        String userId = scanner.nextLine();
        for (Account account1 : listOfAccounts.accounts) {
            if (account1.getUsername().equalsIgnoreCase(userId))
                account = account1;
        }
    }
}
