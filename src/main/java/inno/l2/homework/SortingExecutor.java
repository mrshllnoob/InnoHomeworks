package inno.l2.homework;

import inno.l2.homework.sorting.SortingAlgorithm;
import inno.l2.homework.sorting.algs.AscendingMergeSorting;

import java.util.Random;

/**
 * <p>Класс (@code SortingExecutor) позволяет сортировать
 * массивы случайных целых чисел.
 *
 * Включает в себя ряд методов для работы с массивами и nested-классов,
 * представляющих собой различные виды сортировок.
 *
 * Реализовано 6 алгоритов сортировки.
 *
 * @author Tsapin Anton
 */
public class SortingExecutor {

    private static Comparable[] values; //
    private static SortingAlgorithm alg;
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
        alg = new AscendingMergeSorting();
        setValues(generateIntegersArray(getArrLen()));
        show(getValues());
        Comparable[] sortedValues = alg.sort(getValues());
        assert isSorted(getValues());
        show(getValues());
    }

    /**
     * Генерирует массив значений класса Integer указанной длины
     *
     * @param arrLen длина генерируемого массива
     * @return возвращает ссылку на сгенерированный целочисленный массив
     */
    public static Integer[] generateIntegersArray(int arrLen) {
        Random rand = new Random();
        Integer[] temp = new Integer[arrLen];
        for(int i=0;i<arrLen;i++)
            temp[i] = rand.nextInt(getRandomRange());
        return temp;
    }

    /**
     * Сравнивает Comparable-объекты
     *
     * @param comp1 ссылка на объект 1
     * @param comp2 ссылка на объект 2
     * @return возвращает true, если объект 1 меньше объекта 2;
     * false, если объект 1 больше или равен объекту 2
     */
    public static boolean less(Comparable comp1, Comparable comp2) {
        return comp1.compareTo(comp2)<0;
    }

    /**
     * Метод меняет местами элементы указанного массива по переданным индексам
     *
     * @param comp ссылка на объект массива
     * @param i индекс элемента 1
     * @param j индекс элемента 2
     */
    public static void exchangeElements(Comparable[] comp, int i,int j) {
        Comparable temp = comp[i];
        comp[i] = comp[j];
        comp[j] = temp;
    }

    /**
     * Вывод массива в stdout
     *
     * @param comp ссылка на объект выводимого в stdout массива
     */
    public static void show(Comparable[] comp) {
        for(int i=0;i<comp.length;i++)
            System.out.print(comp[i] + " ");
        System.out.println();
    }

    /**
     * Проверяет отсортирован ли массив по переданной ссылке.
     *
     * @param comp ссылка на проверяемый массив.
     * @return возвращает true, если отсортировано и false, если нет
     */
    public static boolean isSorted(Comparable[] comp) {
        for(int i=1;i<comp.length;i++)
            if (less(comp[i], comp[i-1]))
                return false;
        return true;
    }

    /**
     * Выдает значение длины массива, генерируемого функцией
     * (@code generateIntegersArray()).
     *
     * @return поле объекта (@code SortingExecutor),
     * обозначающее длину генерируемого целочисленного массива
     */
    public static int getArrLen() {
        return arrLen;
    }

    /**
     * Устанавливает значение длины массива, генерируемого функцией
     * (@code generateIntegersArray()).
     *
     * @param arrLen устанавливаемая длина генерируемого
     *  (@code generateIntegersArray()) массива.
     * @return возвращает устанавливаемое параметром значение.
     */
    public static int setArrLen(int arrLen) {
        SortingExecutor.arrLen = arrLen;
        return arrLen;
    }

    /**
     * Выдает установленное в классе (@code SortingExecutor)
     * максимальное значение диапазона генерируемых функцией
     * (@code generateIntegersArray()) значений для целочисленного массива
     *
     * @return максимальное значение для диапазона
     */
    public static int getRandomRange() {
        return randomRange;
    }

    /**
     * Задает объявленное в классе (@code SortingExecutor)
     * максимальное значение диапазона генерируемых функцией
     * (@code generateIntegersArray()) значений для целочисленного массива
     *
     * @param randomRange устанавливаемый максимум диапазона значений массива
     * @return возвращает устанавливаемое параметром значение
     */
    public static int setRandomRange(int randomRange) {
        SortingExecutor.randomRange = randomRange;
        return randomRange;
    }

    public static Comparable[] getValues() {
        return values;
    }

    public static void setValues(Comparable[] values) {
        SortingExecutor.values = values;
    }

    public static boolean isArrayEqNull(Comparable[] values) {
        if (values == null)
            return true;
        return false;
    }
}
