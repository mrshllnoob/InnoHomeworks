package inno.l4.homework;

import java.io.*;
import java.util.ArrayList;

public class ListOperator implements Serializable{

    private String filePath = "src/inno/l4/homework/MarkTwen.txt";
    private String serializeDest = "src/inno/l4/homework/serialized/";
    private ArrayList<String> parsedTextFileList = new ArrayList<>();


    public ListOperator() {
        this.setParsedTextFileList(this.parseFile());
    }

    public ListOperator(String filePath) {
        this.setParsedTextFileList(this.parseFile(filePath));
    }


    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    public ArrayList<String> getParsedTextFileList() {
        return parsedTextFileList;
    }

    public void setParsedTextFileList(ArrayList<String> parsedTextFileList) {
        this.parsedTextFileList = parsedTextFileList;
    }

    public String getSerializeDest() {
        return serializeDest;
    }

    public void setSerializeDest(String serializeDest) {
        this.serializeDest = serializeDest;
    }

    public static ArrayList<String> parseFile(String filePath) {
        ArrayList<String> result = new ArrayList<>();
        try (FileReader fin = new FileReader(new File("src/inno/l4/homework/" + filePath));
                BufferedReader in = new BufferedReader(fin)) {
            String line;
            while ((line = in.readLine()) != null) {
                result.add(line);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }


    public ArrayList<String> parseFile() {
        try (FileReader fin = new FileReader(new File(filePath));
             BufferedReader in = new BufferedReader(fin)) {
            String line;
            while ((line = in.readLine()) != null) {
                this.parsedTextFileList.add(line);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return this.parsedTextFileList;
    }

    public void serializeListOperator(String destPath) {
        try(FileOutputStream baos = new FileOutputStream(this.getSerializeDest() + destPath);
            ObjectOutputStream oos = new ObjectOutputStream(baos)) {
            oos.writeObject(this);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static ListOperator deserializeListOperator(String path) {
        ListOperator storedListOperator = null;
        try(FileInputStream baos = new FileInputStream("src/inno/l4/homework/serialized/" + path);
            ObjectInputStream oos = new ObjectInputStream(baos)) {
            System.out.println("Deserializing ListOperator object:");
            storedListOperator = (ListOperator) oos.readObject();
            for(String elem : storedListOperator.getParsedTextFileList())
                System.out.println(elem);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return storedListOperator;
    }

}
