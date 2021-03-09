package main;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/***
 * The class manages the file handling.
 */
public class FileManager {
    public static final String FILE_NAME = "results.txt";

    /***
     * Writes the result list to a file.
     * @param resultList a list of the main.Result object.
     * @throws IOException
     */
    public static void write(List<Result> resultList) throws IOException {
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(FILE_NAME))) {
            for (Result result : resultList) {
                out.writeObject(result);
            }
        }
    }

    /***
     * Reads the result list from a file.
     * @return returns the result list.
     * @throws IOException
     * @throws ClassNotFoundException
     */
    public static List<Result> read() throws IOException, ClassNotFoundException {
        List<Result> resultList = new ArrayList<>();
        FileInputStream fis = new FileInputStream(FILE_NAME);
        ObjectInputStream input = new ObjectInputStream(fis);

        Result result;
        boolean go = true;

        while (go) {
            if (fis.available() != 0) {
                result = (Result) input.readObject();
                resultList.add(result);
            } else {
                go = false;
            }
        }

        return resultList;
    }

    /***
     * Automatically opens the text file
     * @throws IOException
     */
    public static void openFileAndDisplay() throws IOException {
        java.awt.Desktop.getDesktop().open(new File(FILE_NAME));
    }
}