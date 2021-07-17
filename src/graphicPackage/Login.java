package graphicPackage;

import classes.Account;
import classes.ListOfAccounts;
import classes.Missions;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import java.io.FileWriter;
import java.io.IOException;

public class Login {
        static Account account ;
        static ListOfAccounts listOfAccounts = new ListOfAccounts();
        static Main main = new Main();
        @FXML
        Button loginClick;
        @FXML
        TextField userNameTxt;
        @FXML
        PasswordField passwordTxt;
        @FXML
        Label loginLabel;

        public void canLogin() throws IOException {
            listOfAccounts.load();
            if (userNameTxt.getText().isEmpty()){
                loginLabel.setText("UserName field is empty!");
            }else if (passwordTxt.getText().isEmpty()){
                loginLabel.setText("Password field is empty!");
            }else if(!contain(userNameTxt.getText())){
                loginLabel.setText("This UserName does not exist!");
            }else if(!setPass(userNameTxt.getText() , passwordTxt.getText())){
                loginLabel.setText("Password is wrong!");
                account = getAccount(userNameTxt.getText());
                account.logSave("Error" , "password is wrong");
            }else {
                FileWriter file = new FileWriter("username.txt" , false);
                file.write(userNameTxt.getText() );
                file.close();
                account = getAccount(userNameTxt.getText());
                account.logSave("Info" , userNameTxt.getText()+" login successfully");
                // TODO: 7/12/2021 username.txt
                main.goToMenu();
            }

        }

    private boolean setPass(String username, String pass) {
            listOfAccounts.load();
        for (Account account1 : listOfAccounts.accounts) {
            if (account1.getUsername().equalsIgnoreCase(username)&&account1.getPassword().equalsIgnoreCase(pass)){
                account = account1;
                return true;
            }
        }
        return false;
    }

    private boolean contain(String username){
            listOfAccounts.load();
            for (Account account1 : listOfAccounts.accounts) {
                if (account1.getUsername().equalsIgnoreCase(username))
                    return true;
            }
            return false;
        }
        private Account getAccount(String username){
            for (Account account1 : listOfAccounts.accounts) {
                if (account1.getUsername().equalsIgnoreCase(username))
                    return account1;
            }
            return null;
        }
}
