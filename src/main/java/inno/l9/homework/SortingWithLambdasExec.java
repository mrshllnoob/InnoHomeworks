package inno.l9.homework;


public class SortingWithLambdasExec {

    private static Comparable[] values;
    private static int arrLen = 60000;
    private static int randomRange = 60000;

    /**
     * Метод main является точкой входа в программу.
     *
     * Для осуществления сортировки в нём необходимо создать экземпляр класса
     * конкретного алгоритма:
     * <blockquote><pre>
     *      alg = new ИмяКлассаКонкретнойСортировки;
     * </pre></blockquote><p>
     *
     * Сортировка выбором - класс SelectionSorting
     * Сортировка вставками - класс InsertionSorting
     * Сортировка Шелла - класс ShellSorting
     */
    public static void main(String[] args) {

        int[] arr = Utility.generateArray(arrLen,randomRange);
        Utility.show(arr);
        System.out.println("____________________________________________________________");
        int[] sortedArr = Algorithms.insertionSorting(arr);
        Utility.show(sortedArr);
        System.out.println(Utility.isSorted(sortedArr));

    }

}
