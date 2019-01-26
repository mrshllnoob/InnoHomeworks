package inno.l7.homework;

import javax.tools.JavaCompiler;
import javax.tools.ToolProvider;
import java.io.*;
import java.lang.reflect.InvocationTargetException;
import java.util.Scanner;

public class MethodBuilder {

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

    public void insertMethodBody(String methodBody) {
        try(FileReader fr = new FileReader(new File("src/inno/l7/homework/SomeClass.java"));
                BufferedReader br = new BufferedReader(fr)) {
            String content = "";
            String line;
            while((line = br.readLine()) != null) {
                content += line + "\n";
                if (line.contains("doWork() {")) {
                    content += "\t\t" + methodBody;
                }
            }
            System.out.println(content);
            try(PrintWriter pw = new PrintWriter("src/inno/l7/homework/SomeClass.java")) {
                pw.write(content);
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void compileAtRuntime() {
        JavaCompiler compiler = ToolProvider.getSystemJavaCompiler();
        compiler.run(null, null, null, "src/inno/l7/homework/SomeClass.java");
    }


    public static void main(String[] args) throws ClassNotFoundException {
        MethodBuilder methodBuilder = new MethodBuilder();
        methodBuilder.insertMethodBody(methodBuilder.getMethodFromStdIn().toString());
        methodBuilder.compileAtRuntime();
        CustomClassLoader clloader = new CustomClassLoader(
                "./", ClassLoader.getSystemClassLoader());
        Class objCl = clloader.findClass("SomeClass");
        try {
            objCl.getMethod("doWork").invoke(objCl.newInstance());
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        }
    }

}
