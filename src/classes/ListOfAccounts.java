package classes;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class ListOfAccounts {
    public List<Account> accounts;

    public ListOfAccounts() {
        accounts = new ArrayList<>();
    }


    public void save() {
        GsonBuilder gsonBuilder = new GsonBuilder().setPrettyPrinting();
        Gson gson = gsonBuilder.create();
        String userListText = gson.toJson(this);

        FileOperator fileOperator = new FileOperator();
        fileOperator.write("users.json", userListText, false);
    }

    public static void existFiles() {
        File file = new File("users.json");
        if (!file.exists()) {
            GsonBuilder gsonBuilder = new GsonBuilder().setPrettyPrinting();
            Gson gson = gsonBuilder.create();
            String userListText = "";
            FileOperator fileOperator = new FileOperator();
            fileOperator.write("users.json", userListText, false);
        }
    }

    public void load() {
        FileOperator fileOperator = new FileOperator();
        String userListText = fileOperator.read("users.json");
        ListOfAccounts listOfAccounts = new Gson().fromJson(userListText, ListOfAccounts.class);
        if (listOfAccounts != null) {
            if (listOfAccounts.accounts != null)
                this.accounts = listOfAccounts.accounts;
        }
    }


}
