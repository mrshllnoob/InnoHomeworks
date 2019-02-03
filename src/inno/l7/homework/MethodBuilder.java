package inno.l7.homework;

import javax.tools.JavaCompiler;
import javax.tools.ToolProvider;
import java.io.*;
import java.lang.reflect.InvocationTargetException;
import java.nio.file.Files;
import java.nio.file.Paths;

/**
 * Класс содержащий методы для создания и загрузки
 * файлов классов в райнтайме.
 */
public class MethodBuilder {

    private static String className;
    private static String pathToOutDir;
    private static String fullPathToJavaFile;
    private static String fullPathToClassFile;
    private static String methodName = "doWork";
    private static String packageName = "inno.l7.homework";

    /**
     * Читает ввод с консоли и формирует строку,
     * содержащую тело класса SomeClass.
     *
     * @return предполагаемое тело метода
     */
    public String getSomeClassListing() {
        String someclass = "package " + packageName + ";\n" +
                "/**\n" +
                " * Класс, тело метода которого заполняется введенным с\n" +
                " * консоли содержимым.\n" +
                " */\n" +
                "public class SomeClass {\n" +
                "\n" +
                "    public void doWork() {\n" +
                "\t\t%TEMPLATE%" +
                "\t}\n" +
                "}";
        StringBuilder methodBody = new StringBuilder();
        System.out.println("Method body:");
        try(InputStreamReader isr = new InputStreamReader(System.in);
                BufferedReader br = new BufferedReader(isr)) {
            String line;
            while(true) {
                line = br.readLine() + System.lineSeparator();
                methodBody.append(line);
                if (line.equals(System.lineSeparator()))
                    break;
            }
            System.out.println("Input ended");
        } catch (IOException e) {
            e.printStackTrace();
        }
        return someclass.replaceAll("%TEMPLATE%", methodBody.toString());
    }

    /**
     * Вставляет введенный с консоли код в тело метода
     * doWork()  класса SomeClass.java
     * @param content содержимое записываемого java-файла
     */
    public void writeListingIntoFile(String content) {

            System.out.println(content);

            if (Files.exists(Paths.get(fullPathToJavaFile)) == false) {
                try {
                    new File(fullPathToJavaFile).createNewFile();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            try(PrintWriter pw = new PrintWriter(fullPathToJavaFile)) {
                pw.write(content);
            } catch (FileNotFoundException e1) {
                e1.printStackTrace();
            }
    }

    /**
     * Компилирует SomeClass.java в райнтайме.
     */
    public void compileAtRuntime() {
        JavaCompiler compiler = ToolProvider.getSystemJavaCompiler();
        compiler.run(null, null, null, fullPathToJavaFile);
    }

    /**
     *
     * @param args 1-путь до папки с выводом, 2-имя формируемого класса без расширения.
     * @throws IOException
     */
    public static void main(String[] args) {

        if (args.length!=2) {
            System.out.println("Wrong number of args");
            return;
        }

        pathToOutDir = args[0];
        className = args[1];
        fullPathToJavaFile = args[0] + args[1] + ".java";
        fullPathToClassFile = args[0] + args[1] + ".class";

        MethodBuilder methodBuilder = new MethodBuilder();
        methodBuilder.writeListingIntoFile(methodBuilder.getSomeClassListing());
        methodBuilder.compileAtRuntime();
        CustomClassLoader customClassLoader =
                new CustomClassLoader(fullPathToClassFile, ClassLoader.getSystemClassLoader());

        Class objCl = null;

        try {
            objCl = customClassLoader.findClass(packageName + "." + className);
            objCl.getMethod(methodName).invoke(objCl.newInstance());
        } catch (ClassNotFoundException|IllegalAccessException|InvocationTargetException
                    |InstantiationException|NoSuchMethodException e) {
            e.printStackTrace();
        }

    }

}
