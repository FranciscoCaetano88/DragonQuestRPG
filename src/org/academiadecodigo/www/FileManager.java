package org.academiadecodigo.www;

import java.io.*;

public class FileManager {

    private String FILE_PATH = "resources/";
    private String FILE_EXTENSION = ".txt";

    public FileManager() {

    }

    public void write(String fileName, String content) throws IOException {

        //File file = new File(FILE_PATH + fileName + FILE_EXTENSION);

        //BufferedWriter bWriter = new BufferedWriter(fileWriter);

        PrintWriter printWriter = new PrintWriter(new BufferedWriter(new FileWriter(FILE_PATH + fileName + FILE_EXTENSION, true)));

        //bWriter.write(content);
        printWriter.println(content);

        System.out.println("FILE SAVED");

        //bWriter.close();
        printWriter.close();

    }


    public String read(String file) throws IOException {

        FileReader fileReader = new FileReader(FILE_PATH + file + FILE_EXTENSION);

        BufferedReader bReader = new BufferedReader(fileReader);

        String loadFile;
        String result = "";

        while ((loadFile = bReader.readLine()) != null) {
            result += loadFile + "\n";

        }

        System.out.println("LOADING FILE");

        bReader.close();

        return result;

    }

}
