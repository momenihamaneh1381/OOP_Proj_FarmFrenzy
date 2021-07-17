package graphicPackage;

import classes.Account;
import classes.FileOperator;
import classes.Missions;
import com.google.gson.Gson;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Rectangle2D;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Screen;
import javafx.stage.Stage;

import java.io.IOException;

public  class Main extends Application {
        private static Stage stage;
        @FXML
         Button loginBtn;
        @FXML
         Button signUpBtn;

    @Override
        public void start(Stage primaryStage) throws Exception{
            stage = primaryStage;
            Parent root = FXMLLoader.load(getClass().getResource("LoginOrSignUp.fxml"));
            primaryStage.setTitle("Login or SignUp");
            Scene loginOrSignUp = new Scene(root , 301 , 236);
            primaryStage.setScene(loginOrSignUp);
            primaryStage.show();
        }

        public void changeScene(String fxml , String title) throws IOException {
            Parent parent = FXMLLoader.load(getClass().getResource(fxml));
            Scene loginOrSignUp = new Scene(parent , 301 , 236);
            stage.setScene(loginOrSignUp);
            stage.getScene().setRoot(parent);
            stage.setTitle(title);
        }


        public void goToMenu() throws IOException{
        if (stage==null)
            stage = new Stage();
            Parent root = FXMLLoader.load(getClass().getResource("MenuForm.fxml"));
            Scene menuScene = new Scene(root , 650 ,365);
            stage.setResizable(false);
            stage.setScene(menuScene);
            stage.setTitle("Menu");
            Rectangle2D primScreenBounds = Screen.getPrimary().getVisualBounds();
            stage.setX((primScreenBounds.getWidth() - stage.getWidth()) / 2);
            stage.setY((primScreenBounds.getHeight() - stage.getHeight()) / 2);
        }

        public void closeScene(){
            stage.close();
        }
        public static void main(String[] args) {
            launch(args);
        }

    public void login( ) throws IOException {
        Main main = new Main();
        main.changeScene("loginForm.fxml" , "Login");
    }

    public void signup( ) throws IOException {
        Main main = new Main();
        main.changeScene("signUpForm.fxml" , "Signup");
    }

    public void startGame() throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("game.fxml"));
//        Scene menuScene = new Scene(root , 600 ,400);
//        Scene menuScene = new Scene(root , 1366 ,700);
        Scene menuScene = new Scene(root , 851 ,639);
//        stage.setResizable(false);
        stage.setScene(menuScene);
        stage.setTitle("Game");
        Rectangle2D primScreenBounds = Screen.getPrimary().getVisualBounds();
        stage.setX((primScreenBounds.getWidth() - stage.getWidth()) / 2);
        stage.setY((primScreenBounds.getHeight() - stage.getHeight()) / 2);
//        stage.setFullScreen(true);
        stage.setResizable(true);
        // TODO: 7/12/2021  
    }
}