package inno.l6.homework;

import java.io.*;
import java.lang.reflect.Field;


public class SimpleRestorer {

    private String content;
    private Object resultObj;

    private void readFile (String path) {
        StringBuilder result = new StringBuilder();
        try(FileReader fr = new FileReader(new File(path));
            BufferedReader br = new BufferedReader(fr)) {
            String line;
            while ((line = br.readLine())!=null) {
                result.append(line + "\n");
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        this.content = new String(result).replaceAll("^\n","");
    }

    private void parseContent() throws ClassNotFoundException, IllegalAccessException, InstantiationException, NoSuchFieldException {
        String[] contentArr = content.split("\n");
        for (int i=0;i<contentArr.length;i++) {
            if (contentArr[i].contains("ClassName=")) {
                String[] temp = contentArr[i].split("=");
                resultObj = Class.forName(temp[1]).newInstance();
            }
            if (contentArr[i].contains("Field-")) {
                Field f = resultObj.getClass().getDeclaredField(getFieldNameFromContent(contentArr[i]));
                setValueFromContent(contentArr[i],f);
            }

        }
    }

    private void setValueFromContent(String s, Field f) throws IllegalAccessException {
        String[] arr = s.split(";");
        String[] temp1  = arr[2].split("=");
        String[] temp2 = arr[3].split("=");
        String fType = temp1[1];
        String fVal = temp2[1];
        switch (fType) {
            case "byte":
                f.setAccessible(true);
                f.setByte(resultObj,Byte.parseByte(fVal));
                break;
            case "short":
                f.setAccessible(true);
                f.setShort(resultObj,Short.parseShort(fVal));
                break;
            case "int":
                f.setAccessible(true);
                f.setInt(resultObj,Integer.parseInt(fVal));
                break;
            case "long":
                f.setAccessible(true);
                f.setLong(resultObj, Long.parseLong(fVal));
                break;
            case "float":
                f.setAccessible(true);
                f.setFloat(resultObj, Float.parseFloat(fVal));
                break;
            case "double":
                f.setAccessible(true);
                f.setDouble(resultObj, Double.parseDouble(fVal));
                break;
            case "boolean":
                f.setAccessible(true);
                f.setBoolean(resultObj, Boolean.parseBoolean(fVal));
                break;
            case "java.lang.String":
                f.setAccessible(true);
                f.set(resultObj, fVal);
                break;
        }

    }

    private String getFieldNameFromContent(String s) {
        String[] arr = s.split(";");
        String[] temp  = arr[1].split("=");
        return temp[1];
    }

    public static void main(String[] args) {
        SimpleRestorer simpleRestorer = new SimpleRestorer();
        simpleRestorer.readFile("src/inno/l6/homework/parsed.txt");


        try {
            simpleRestorer.parseContent();
            System.out.println(simpleRestorer.resultObj.getClass().
                    getDeclaredField("a").get(simpleRestorer.resultObj));
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        }

    }

}
