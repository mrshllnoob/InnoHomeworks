package inno.l6.homework;


import java.io.*;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;

public class SimpleXMLSerializer<T extends Object> {

    private T objct;
    private StringBuilder document = new StringBuilder();
    private int tabsAmount = -1;

    public SimpleXMLSerializer(T obj) {
        this.objct = obj;
    }

    public String useSerialization(String filepath) throws IllegalAccessException {
        System.out.println("Serializing " + objct.getClass().getName() + ":\n" +
                "___________________________________________________________\n");
        this.parseObject(this.objct);
        return wrapAndWrite(filepath);
    }

    public void parseObject(T obj) throws IllegalAccessException {
        Class objClass = obj.getClass();
        this.getDocument().append("\n" + generateTabs(++tabsAmount) + "Object {" +
                                     "\n" + generateTabs(++tabsAmount)
                                    + "ClassName=" + obj.getClass().getName() + "\n"
                                    + generateTabs(tabsAmount));
        Field[] objFields = objClass.getDeclaredFields();
        this.getDocument().append("ClassFields " + "\n" + generateTabs(++tabsAmount));
        for (Field f : objFields) {
            f.setAccessible(true);
            if (!isViable(f, obj))
                continue;
            this.getDocument().append("Field:");
            if (f.getType().isPrimitive()) {
                this.buildPrimitiveNode(f, obj);
                this.getDocument().append("\n" + generateTabs(tabsAmount));
                continue;
            }
            else if (f.getType().isArray()) {
                this.buildArrayNode(f, obj);
                this.getDocument().append("\n" + generateTabs(tabsAmount));
                continue;
            }
            else {
                this.buildComplexNode(f, obj);
                this.getDocument().append("\n" + generateTabs(tabsAmount));
            }

        }
        tabsAmount--;
        this.getDocument().append("\n" + generateTabs(--tabsAmount)
                                    + "} ");
    }

    private boolean isViable(Field f, T obj) throws IllegalAccessException {
        if (Modifier.toString(f.getModifiers()).contains("transient")
            || Modifier.toString(f.getModifiers()).contains("static")
           // || Modifier.toString(f.getModifiers()).contains("final")
            || f.get(obj) == null)
            return false;
        return true;
    }

    private void buildComplexNode(Field f, T obj) throws IllegalAccessException {
        this.getDocument().append("Object");
        String type = f.getType().getTypeName();
        String name = f.getName();
        T value = (T) f.get(obj);
        this.getDocument().append("name=" + name + ";type=" + type + ";value=" + value);
        if (value.getClass().getDeclaredFields().length>0)
            parseObject(value);
        this.getDocument().append("\n" + generateTabs(tabsAmount)
                                    + "\n" + generateTabs(--tabsAmount));
    }

    private void buildArrayNode(Field field, T obj) throws IllegalAccessException {
        this.getDocument().append("Array:");
        String name = field.getName();
        String type = field.getType().getTypeName();
        this.getDocument().append("type=" + type + ";values=");
        if (field.getType().getTypeName().equals("char[]")) {
            operateCharArray(field.get(obj));
            return;
        }
        if (field.getType().getTypeName().equals("byte[]")) {
            operateByteArray(field.get(obj));
            return;
        }
        if (field.getType().getTypeName().equals("int[]")) {
            operateIntArray(field.get(obj));
            return;
        }
        if (field.getType().getTypeName().equals("short[]")) {
            operateShortArray(field.get(obj));
            return;
        }
        if (field.getType().getTypeName().equals("long[]")) {
            operateLongArray(field.get(obj));
            return;
        }
        if (field.getType().getTypeName().equals("float[]")) {
            operateFloatArray(field.get(obj));
            return;
        }
        if (field.getType().getTypeName().equals("double[]")) {
            operateDoubleArray(field.get(obj));
            return;
        }
        if (field.getType().getTypeName().equals("boolean[]")) {
            operateBooleanArray(field.get(obj));
            return;
        }
        else {
            operateObjectArray(field.get(obj));
            return;
        }
    }

    private void operateCharArray(Object obj) {
        char[] value = (char[]) obj;
        for (char elem : value)
            this.getDocument().append(elem);
        this.getDocument().append("/Array");
    }

    private void operateByteArray(Object obj) {
        byte[] value = (byte[]) obj;
        for (byte elem : value)
            this.getDocument().append(elem + "::");
        this.getDocument().substring(0,this.getDocument().length()-2);
        this.getDocument().append("/Array");
    }

    private void operateIntArray(Object obj) {
        int[] value = (int[]) obj;
        for (int elem : value)
            this.getDocument().append(elem + "::");
        this.getDocument().substring(0,this.getDocument().length()-2);
        this.getDocument().append("/Array");
    }

    private void operateShortArray(Object obj) {
        short[] value = (short[]) obj;
        for (short elem : value)
            this.getDocument().append(elem + "::");
        this.getDocument().substring(0,this.getDocument().length()-2);
        this.getDocument().append("/Array");
    }

    private void operateLongArray(Object obj) {
        long[] value = (long[]) obj;
        for (long elem : value)
            this.getDocument().append(elem + "::");
        this.getDocument().substring(0,this.getDocument().length()-2);
        this.getDocument().append("/Array");
    }

    private void operateFloatArray(Object obj) {
        float[] value = (float[]) obj;
        for (float elem : value)
            this.getDocument().append(elem + "::");
        this.getDocument().substring(0,this.getDocument().length()-2);
        this.getDocument().append("/Array");
    }

    private void operateDoubleArray(Object obj) {
        double[] value = (double[]) obj;
        for (double elem : value)
            this.getDocument().append(elem + "::");
        this.getDocument().substring(0,this.getDocument().length()-2);
        this.getDocument().append("/Array");
    }

    private void operateBooleanArray(Object obj) {
        boolean[] value = (boolean[]) obj;
        for (boolean elem : value)
            this.getDocument().append(elem + "::");
        this.getDocument().substring(0,this.getDocument().length()-2);
        this.getDocument().append("/Array");
    }

    private void operateObjectArray(Object obj) throws IllegalAccessException {
//        Object[] value = (Object[]) obj;
//        for (Object elem : value)
//            parseObject((T) elem);
        this.getDocument().append("/Array");
    }

    private void buildPrimitiveNode(Field field, T obj) {
        String name = field.getName();
        String tname = field.getType().toString();
        String value = null;
        try {
            value = field.get(obj).toString();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        this.getDocument().append("Primitive:" +
                "name=" + name +
                ";type=" + tname +
                ";value=" + value +
                "/Primitive");
    }

    private String generateTabs(int amount) {
        StringBuilder builder = new StringBuilder();
        for(int i=0;i<amount;i++)
            builder.append("\t");
        return new String(builder);
    }

    public String wrapAndWrite(String path) {
        try(FileWriter fw = new FileWriter(new File(path));
            BufferedWriter bw = new BufferedWriter(fw)) {
            bw.write(this.getDocument().toString());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "";
    }

    public T getObject() {
        return objct;
    }

    public void setObject(T object) {
        this.objct = object;
    }

    public StringBuilder getDocument() {
        return document;
    }

}
