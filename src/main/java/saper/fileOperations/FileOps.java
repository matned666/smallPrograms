package saper.fileOperations;

import java.io.*;
import java.util.LinkedList;

class FileOps<E> {

    private static String path = "src\\main\\resources\\saper\\data\\score.s";

    FileOps() {
    }

    void writeToFile(LinkedList list) {
        try {
            FileOutputStream fileOut = new FileOutputStream(path);
            ObjectOutputStream objectOut = new ObjectOutputStream(fileOut);
            objectOut.writeObject(list);
            objectOut.close();
        } catch (Exception ex) {
            System.out.println("There was a problem!");
        }
    }

    LinkedList readTop100ListFromFile() {
        LinkedList list = new LinkedList<>();
        try
        {
            FileInputStream fis = new FileInputStream(path);
            ObjectInputStream ois = new ObjectInputStream(fis);
            list = (LinkedList) ois.readObject();
            ois.close();
            fis.close();
            return list;
        }
        catch (IOException ioe)
        {
            ioe.printStackTrace();
            return null;
        }
        catch (ClassNotFoundException c)
        {
            System.out.println("Class not found");
            c.printStackTrace();
            return null;
        }
    }

}



