package inno.l3.homework;

import java.util.Collection;

/**
 * Класс (@code BoxesTestDrive) предназначе для тестирования
 * возможностей разработанных Box-классов. Статические поля класса:
 * (@code arrLen) - длина генерируемых функцией (@code generateIntegersArray())
 * массивов.
 * (@code maxRandom) - диапазон генерируемых функцией (@code generateIntegersArray())
 * значений.
 * (@code divider) - делитель элементов коллекции для метода (@code splitter())
 * (@code valueToRemove) - значение аргумента метода (@code removeIfExists)
 *
 * @author Tsapin Anton
 */
public class BoxesTestDrive {

    private static int arrLen = 100;
    private static int maxRandom = 50;
    private static int divider = 2;
    private static int valueToRemove = 3;

    public static void main(String[] args) {
        MathBox mathBox = MathBox.createInstanceOfMathBox(arrLen, maxRandom);
        System.out.println(mathBox);
        System.out.println(mathBox.getBoxedElements().hashCode());
        System.out.println("Sum of all elements equals " + MathBox.summator(mathBox.getBoxedElements()));
        Collection s = MathBox.splitter(mathBox.getBoxedElements(),divider);
        System.out.println("\nAfter division on " + divider + " collection look like");
        MathBox.showNumericCollectionElems(s);
        System.out.println("\nCurrent example of MathBox " +
                                " has hashcode equal to:\n"
                                                                + mathBox.hashCode());
        mathBox.removeIfExists(valueToRemove);
        System.out.println("\nCurrent example of MathBox " +
                                " after deletion of element==" +
                                        valueToRemove + ":\n" + mathBox);
        System.out.println(mathBox.hashCode());
        System.out.println(mathBox.getBoxedElements().hashCode());

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

        System.out.println("__________________________________________________________________________\n\n");

        mathBox.addObject(1);
        mathBox.dump();
        System.out.println();
        mathBox.deleteObject(1);
        System.out.println(mathBox);

    }

}
