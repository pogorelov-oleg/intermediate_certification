package Data;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class FileManager {

    public void addToFile(String fileName, String str) {
        try {
            FileWriter fWriter = new FileWriter(fileName, true);
            BufferedWriter bufferWriter = new BufferedWriter(fWriter);
            bufferWriter.write(str + "\n");
            bufferWriter.close();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

}
