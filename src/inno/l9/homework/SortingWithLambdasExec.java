package inno.l9.homework;

import java.util.Arrays;
import java.util.Random;
import java.util.function.*;
import java.util.stream.IntStream;


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
     * Усовершенствованный алгоритм сортировки вставками -
     *   класс ImprovedInsertionSorting
     * Сортировка Шелла - класс ShellSorting
     * Нисходящая сортировка слиянием - класс DescendingMergeSorting
     * Восходящая сортировка слиянием - класс AscendingMergeSorting
     */
    public static void main(String[] args) {
        int[] generatedArray = new Random().ints(arrLen, 0, randomRange)
                                            .toArray();
        BiFunction<Integer,Integer,Boolean> less = (a, b) -> a.compareTo(b)<0;
        Consumer<int[]> show = (array -> Arrays.stream(array)
                                                    .forEach(System.out::println));
        Predicate<int []> isSorted = (array) -> IntStream.range(0, array.length - 1)
                                                        .allMatch(i -> array[i] <= array[i + 1]);
        Predicate<int []> isNUll = (a) -> a == null;

        /**
         * ----------------------------------------Sorting algorithms -----------------------------------------------------------------------------
         */
        UnaryOperator<int[]> insertionSorting = (array) -> {
            if (isNUll.test(array))
                throw new NullPointerException();
            for(int i = 1; i< array.length; i++)
                for(int j=i;j>0 && less.apply(array[j],array[j-1]);j--)
                    exchangeElements(array, j,j-1);
            return array;
        };

        UnaryOperator<int[]> selectionSorting = (array) -> {
            if (isNUll.test(array))
                throw new NullPointerException();
            for (int i = 0; i < array.length; i++) {
                int min = i;
                for (int j = i + 1; j < array.length; j++)
                    if (less.apply(array[j], array[min]))
                        min = j;
                exchangeElements(array, i, min);
            }
            return array;
        };

        UnaryOperator<int[]> shellSorting = (array) -> {
            if (isNUll.test(array))
                throw new NullPointerException();
            int h = 1;
            while(h< array.length /3)
                h = 3*h + 1;

            while(h>=1) {
                for(int i = h; i< array.length; i++) {
                    for(int j=i;j>=h && less.apply(array[j],array[j-h]);j-=h)
                        exchangeElements(array,j,j-h);
                }
                h = h/3;
            }
            return array;
        };

        /**
         * -----------------------------------------Part to test algs----------------------------------------------------
         */
        int[] arr = generatedArray;
        show.accept(arr);
        System.out.println("____________________________________________________________");
        int[] sortedArr = insertionSorting.apply(arr);
        show.accept(sortedArr);
        System.out.println(isSorted.test(sortedArr));

    }

    /**
     * Метод меняет местами элементы указанного массива по переданным индексам
     *  @param comp ссылка на объект массива
     * @param i индекс элемента 1
     * @param j индекс элемента 2
     */
    public static void exchangeElements(int[] comp, int i, int j) {
        int temp = comp[i];
        comp[i] = comp[j];
        comp[j] = temp;
    }

}
