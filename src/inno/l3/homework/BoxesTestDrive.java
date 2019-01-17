package inno.l3.homework;

import inno.l2.homework.SortingExecutor;

import java.util.Collection;

import static inno.l3.homework.MathBox.*;

public class BoxesTestDrive {

    private static int arrLen = 18;
    private static int maxRandom = 10;
    private static int divider = 2;
    private static int valueToRemove = 3;

    public static void main(String[] args) {
        MathBox mathBox = MathBox.createInstanceOfMathBox(arrLen, maxRandom);
        System.out.println(mathBox);
        System.out.println("Sum of all elements equals " + MathBox.summator(mathBox.getBoxedElements()));
        Collection s = splitter(mathBox.getBoxedElements(),divider);
        System.out.println("After division on " + divider + " collection look like");
        showNumericCollectionElems(s);
        System.out.println("Current example of MathBox " +
                                " has hashcode equal to:\n"
                                                                + mathBox.hashCode());
        mathBox.removeElementOnValue(valueToRemove);
        System.out.println("Current example of MathBox " +
                                " after deletion of element==" +
                                        valueToRemove + ":\n" + mathBox);

        System.out.println("__________________________________________________________________________");

        ObjectBox objectBox = new ObjectBox();
        Object a  = new Object();
        Object b = new Object();
        Object c = new Object();
        objectBox.addObject(a);
        objectBox.addObject(b);
        objectBox.addObject(c);
        objectBox.dump();
        objectBox.deleteObject(a);
        objectBox.dump();
        objectBox.deleteObject(b);
        objectBox.dump();
        System.out.println();

        mathBox.addObject(2);
        mathBox.dump();
        mathBox.deleteObject(3);
        System.out.println(mathBox);

    }

}
