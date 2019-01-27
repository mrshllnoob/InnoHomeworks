package inno.l7.homework;

import java.io.*;

/**
 *
 * @author customProgrammer
 */
public class CustomClassLoader extends ClassLoader {

    private String pathtobin;

    public CustomClassLoader(String pathtobin, ClassLoader parent) {
        super(parent);
        this.pathtobin = pathtobin;
    }
    @Override
    public Class<?> findClass(String className) throws
            ClassNotFoundException {
        try {
            byte b[] = fetchClassFromFS(pathtobin + className + ".class");
            return defineClass(className, b, 0, b.length);
        } catch (FileNotFoundException ex) {
            return super.findClass(className);
        } catch (IOException ex) {
            return super.findClass(className);
        }
    }

    private byte[] fetchClassFromFS(String path) throws
            FileNotFoundException, IOException {
        InputStream is = new FileInputStream(new File(path));
        long length = new File(path).length();
        if (length > Integer.MAX_VALUE) {
        }
        byte[] bytes = new byte[(int)length];
        int offset = 0;
        int numRead = 0;
        while (offset < bytes.length
                && (numRead=is.read(bytes, offset, bytes.length-offset)) >= 0) {
            offset += numRead;}

        if (offset < bytes.length) {
            throw new IOException("Could not completely read file "+path);
        }

        is.close();
        return bytes;
    }
}
