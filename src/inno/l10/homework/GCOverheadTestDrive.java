package inno.l10.homework;

import inno.l7.homework.CustomClassLoader;


import java.util.LinkedList;

public class GCOverheadTestDrive {

    static LinkedList<CustomClassLoader> cls = new LinkedList<>();

    public static void main(String[] args) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i=0;i<100000*100000; i++)
                    invoker();
            }
        }).start();
        new Thread(new Runnable() {
            @Override
            public void run() {
                cls.clear();
            }
        }).start();

    }

    public static final void invoker() {
            CustomClassLoader cl0 = new CustomClassLoader("./", ClassLoader.getSystemClassLoader());
            cls.add(cl0);
            CustomClassLoader cl1 = new CustomClassLoader("./", ClassLoader.getSystemClassLoader());
            cls.add(cl1);
            CustomClassLoader cl2 = new CustomClassLoader("./", ClassLoader.getSystemClassLoader());
            cls.add(cl2);
            CustomClassLoader cl3 = new CustomClassLoader("./", ClassLoader.getSystemClassLoader());
            cls.add(cl3);
            CustomClassLoader cl4 = new CustomClassLoader("./", ClassLoader.getSystemClassLoader());
            cls.add(cl4);
    }
}

