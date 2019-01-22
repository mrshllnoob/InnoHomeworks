package inno.l6.homework;


import java.lang.reflect.Field;
import java.lang.reflect.Modifier;

public class SimpleXMLSerializer<T extends Object> {

    private T objct;
    private StringBuilder document = new StringBuilder();
    private int tabsAmount = -1;

    public SimpleXMLSerializer(T obj) {
        System.out.println("Serializing " + obj.getClass().getName() + ":\n" +
                "___________________________________________________________\n");
        this.objct = obj;
    }

    public String useSerialization(String filepath) throws IllegalAccessException {
        this.parseObject(this.objct);
        return wrapAndWrite(filepath);
    }

    public void parseObject(T obj) throws IllegalAccessException {
        Class objClass = obj.getClass();
        this.getDocument().append("\n" + generateTabs(++tabsAmount) + "<CLASSNODE>\n" + generateTabs(++tabsAmount)
                                    + "<CLASSNAME>" + obj.getClass().getName() + "</CLASSNAME>\n"
                                    + generateTabs(tabsAmount));
        Field[] objFields = objClass.getDeclaredFields();
        this.getDocument().append("<FIELDS>\n" + generateTabs(++tabsAmount));
        for (Field f : objFields) {
            f.setAccessible(true);
            if (!isViable(f, obj))
                continue;
            this.getDocument().append("<FIELD>");
            if (f.getType().isPrimitive()) {
                this.buildPrimitiveNode(f, obj);
                this.getDocument().append("</FIELD>" + "\n" + generateTabs(tabsAmount));
                continue;
            }
            else if (f.getType().isArray()) {
                this.buildArrayNode(f, obj);
                this.getDocument().append("</FIELD>" + "\n" + generateTabs(tabsAmount));
                continue;
            }
            else {
                this.buildComplexNode(f, obj);
                this.getDocument().append("</FIELD>" + "\n" + generateTabs(tabsAmount));
            }

        }
        tabsAmount--;
        this.getDocument().append("\b" + "</FIELDS>\n" +
                generateTabs(--tabsAmount) + "</CLASSNODE>");
    }

    private boolean isViable(Field f, T obj) throws IllegalAccessException {
        if (Modifier.toString(f.getModifiers()).contains("transient")
            || Modifier.toString(f.getModifiers()).contains("static")
            || Modifier.toString(f.getModifiers()).contains("final")
            || f.get(obj) == null || (f.getType().isPrimitive()
                                        && obj.equals(0)))
            return false;
        return true;
    }

    private void buildComplexNode(Field f, T obj) throws IllegalAccessException {
        this.getDocument().append("<Object type>");
        String type = f.getType().getTypeName();
        String name = f.getName();
        T value = (T) f.get(obj);
        this.getDocument().append("name=" + name + ";type=" + type + ";value=" + value);
        if (value.getClass().getDeclaredFields().length>0)
            parseObject(value);
        this.getDocument().append("\n" + generateTabs(tabsAmount) + "</Object type>"
                                    + "\n" + generateTabs(--tabsAmount));
    }

    private void buildArrayNode(Field field, T obj) throws IllegalAccessException {
        this.getDocument().append("<Array>");
        T value = (T) field.get(obj);
        String name = field.getName();
        String type = field.getType().getTypeName();
        this.getDocument().append("type=" + type + ";values=" + value);
        this.getDocument().append("</Array>");
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
        this.getDocument().append("<Primitive>" +
                "name=" + name +
                ";type=" + tname +
                ";value=" + value +
                "</Primitive>");
    }

    private String generateTabs(int amount) {
        StringBuilder builder = new StringBuilder();
        for(int i=0;i<amount;i++)
            builder.append("\t");
        return new String(builder);
    }

    public String wrapAndWrite(String path) {
        //wrapping information 'bout obj into JSON view
        //output built JSON into file path
        //returns file path
        return "";
    }

    public T restoreObject(String filepath) {
        //parse file
        //use Reflection API to build new object
        //return built Object
        return null;
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
