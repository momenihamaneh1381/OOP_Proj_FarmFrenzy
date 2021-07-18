package graphicPackage;

import classes.Account;
import classes.ListOfAccounts;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import java.io.FileWriter;
import java.io.IOException;

public class SignUp {
    static Account account ;
    static ListOfAccounts listOfAccounts = new ListOfAccounts();
    static Main main = new Main();
    @FXML
    TextField userNameTxt;
    @FXML
    PasswordField passwordTxt;
    @FXML
    Button signUpClick;
    @FXML
    Label signUpLabel;

    public void canSignUp()throws IOException {
        listOfAccounts.load();
        if(userNameTxt.getText().isEmpty()){
            signUpLabel.setText("username is empty!");
        }else if (passwordTxt.getText().isEmpty()){
            signUpLabel.setText("password is empty!");
        }else if (contain(userNameTxt.getText())){
            signUpLabel.setText("This username already exist!");
        }else  {
            account = new Account(userNameTxt.getText() ,passwordTxt.getText());
            account.logSave("Info" , userNameTxt.getText()+" signUp successfully");
            listOfAccounts.accounts.add(account);
            listOfAccounts.save();
            FileWriter file = new FileWriter("username.txt" , false);
            file.write(userNameTxt.getText() );
            file.close();
            main.goToMenu();
        }

    }

    private boolean contain(String username) {
        listOfAccounts.load();
        for (Account account1 : listOfAccounts.accounts) {
            if (account1.getUsername().equalsIgnoreCase(username))
                return true;
        }
        return false;
    }


}
