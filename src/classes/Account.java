package classes;

import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Account {
    public Account() {
    }

    public Account(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    String username;
    String password;

    public int getCompleteLevels() {
        return completeLevels;
    }

    int completeLevels;
    int coins;

    public void logSave(String type, String log) {
        try {
            LocalDateTime myDateObj = LocalDateTime.now();
            DateTimeFormatter myFormatObj = DateTimeFormatter.ofPattern("dd-MM-yyyy - HH:mm:ss");
            String date = myDateObj.format(myFormatObj);
            FileWriter fileWriter = new FileWriter("log.txt", true);
            fileWriter.append("[" + type + "]" + " , ");
            fileWriter.append(date + " , ");
            fileWriter.append(log + " , ");
            fileWriter.append("username:" + this.username + " , ");
            fileWriter.append("\n");
            fileWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
