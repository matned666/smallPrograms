package personnelInfo.mechanics;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Scanner;

public class FileOps {
    private String path;

    public FileOps(String path) {
        this.path = path;
    }

    public void writeStringToFile(String value) throws FileNotFoundException {
        PrintWriter out = new PrintWriter(path);
        out.println(value);
        out.close();
    }

    public String readStringFromFile() throws FileNotFoundException {
        StringBuilder temp = new StringBuilder();
        File file = new File(path);
        Scanner sc = new Scanner(file);
        while (sc.hasNextLine()) {
            temp.append(sc.nextLine());
        }
        return temp.toString();
    }
}
