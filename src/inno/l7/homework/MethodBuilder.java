package inno.l7.homework;

import javax.tools.JavaCompiler;
import javax.tools.ToolProvider;
import java.io.*;
import java.lang.reflect.InvocationTargetException;
import java.util.Scanner;

/**
 * Класс содержащий методы для создания и загрузки
 * файлов классов в райнтайме.
 */
public class MethodBuilder {
    /**
     * Читает ввод с консоли
     * @return предполагаемое тело метода
     */
    public StringBuilder getMethodFromStdIn() {
        StringBuilder methodBody = new StringBuilder();
        System.out.println("Method body:");
        try(InputStreamReader isr = new InputStreamReader(System.in);
                BufferedReader br = new BufferedReader(isr);
                Scanner scanner = new Scanner(br)) {
            boolean isStopped = false;
            while(!isStopped) {
                methodBody.append(scanner.nextLine() + "\n");
                if (methodBody.toString().contains("\n\n"))
                    isStopped = true;
            }
            System.out.println("Input ended");

        } catch (IOException e) {
            e.printStackTrace();
        }
        return methodBody;
    }

    /**
     * Вставляет введенный с консоли код в тело метода
     * doWork()  класса SomeClass.java
     * @param methodBody вводимый в метод код
     */
    public void insertMethodBody(String methodBody) {
        try(FileReader fr = new FileReader(new File("SomeClass.java"));
                BufferedReader br = new BufferedReader(fr)) {
            String content = "";
            String line;
            while((line = br.readLine()) != null) {
                content += line + "\n";
                if (line.contains("doWork() {")) {
                    content += "\t\t" + methodBody + "\t}\n}";
                    fr.close();
                    break;
                }
            }
            System.out.println(content);
            try(PrintWriter pw = new PrintWriter("SomeClass.java")) {
                pw.write(content);
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Компилирует SomeClass.java в райнтайме.
     */
    public void compileAtRuntime() {
        JavaCompiler compiler = ToolProvider.getSystemJavaCompiler();
        compiler.run(null, null, null, "SomeClass.java");
    }


    public static void main(String[] args) {
        MethodBuilder methodBuilder = new MethodBuilder();
        methodBuilder.insertMethodBody(methodBuilder.getMethodFromStdIn().toString());
        methodBuilder.compileAtRuntime();

        CustomClassLoader clloader = new CustomClassLoader(
                        "", ClassLoader.getSystemClassLoader());
        try {
            Class objCl = clloader.findClass("SomeClass");
            objCl.getMethod("doWork").invoke(objCl.newInstance());
        } catch (IllegalAccessException|InvocationTargetException
                    |NoSuchMethodException|InstantiationException|ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

}
