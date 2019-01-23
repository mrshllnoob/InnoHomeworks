package inno.l6.homework;

import java.io.*;

public class SimpleRestorer {

    public static void restoreFromFile(String path) {
        parseContent(readFile(path));
    }

    private static String readFile(String path) {
        StringBuilder result = new StringBuilder();
        try(FileReader fr = new FileReader(new File(path));
            BufferedReader br = new BufferedReader(fr)) {
            String line;
            while ((line = br.readLine())!=null) {
                result.append(line);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new String(result);
    }

    private static void parseContent(String readFile) {

    }

    private static void buildObject(String className) {

    }

}
