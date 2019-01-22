package inno.l6.homework;

import java.lang.reflect.Array;
import java.lang.reflect.Field;

public class SimpleXMLSerializerTestDrive {

    public static void main(String[] args) throws ClassNotFoundException {
        SimpleXMLSerializer simpleXMLSerializer = new SimpleXMLSerializer(new Thread());
        try {
            simpleXMLSerializer.useSerialization("kek");
            System.out.println(simpleXMLSerializer.getDocument());
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }

}
