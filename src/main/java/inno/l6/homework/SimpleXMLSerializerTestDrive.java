package inno.l6.homework;

public class SimpleXMLSerializerTestDrive {

    public static void main(String[] args) {
        int i=2;
        SimpleSerializer simpleSerializer = new SimpleSerializer(new Tester());
        try {
            simpleSerializer.useSerialization("src/inno/l6/homework/parsed.txt");
            System.out.println(simpleSerializer.getDocument());
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }

}
