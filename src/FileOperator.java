import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class FileOperator {
    public int write(String fileName, String string, boolean append){
        try {
            File file = new File( fileName);
            if (!file.exists()) file.createNewFile();
            FileWriter fileWriter = new FileWriter(file);
            fileWriter.write(string);
            fileWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return 1;
    }

    public String read(String fileName){
        File file = new File( fileName);
        String output = "";
        try {
            Scanner scanner = new Scanner(file);
            while(scanner.hasNextLine()){
                output = output + scanner.nextLine();
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        return output;
    }
}
