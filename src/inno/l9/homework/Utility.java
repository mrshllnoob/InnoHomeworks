package inno.l9.homework;

import java.util.Arrays;
import java.util.Random;
import java.util.function.BiFunction;
import java.util.function.Consumer;
import java.util.function.Predicate;
import java.util.stream.IntStream;

public class Utility {

    public static int[] generateArray(int arrLen, int randomRange) {
        return new Random().ints(arrLen, 0, randomRange)
                .toArray();
    }

    public static boolean less(Integer comp1, Integer comp2) {
        BiFunction<Integer,Integer,Boolean> less = (a, b) -> a.compareTo(b)<0;
        return less.apply(comp1, comp2);
    }

    public static void show(int[] array) {
        Consumer<int[]> show = (arr -> Arrays.stream(arr)
                .forEach(System.out::println));
        show.accept(array);
    }

    public static boolean isSorted(int[] arr) {
        Predicate<int []> isSorted = (array) -> IntStream.range(0, array.length - 1)
                .allMatch(i -> array[i] <= array[i + 1]);
        return isSorted.test(arr);
    }

    public static boolean isNull(int[] arr) {
        Predicate<int []> isNUll = (a) -> a == null;
        return isNUll.test(arr);
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
