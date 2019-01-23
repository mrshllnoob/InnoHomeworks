package inno.l6.homework;

import java.lang.reflect.Array;
import java.lang.reflect.Field;
import java.util.ArrayList;

public class SimpleXMLSerializerTestDrive {

    public static void main(String[] args) {
        SimpleXMLSerializer simpleXMLSerializer = new SimpleXMLSerializer(new Thread());
        try {
            simpleXMLSerializer.useSerialization("src/inno/l6/homework/parsed.xml");
            System.out.println(simpleXMLSerializer.getDocument());
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }

}
