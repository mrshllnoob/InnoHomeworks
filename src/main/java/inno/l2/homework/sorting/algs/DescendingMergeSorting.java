package inno.l2.homework.sorting.algs;

import inno.l2.homework.SortingExecutor;

import static inno.l2.homework.SortingExecutor.getArrLen;

/**
 * Класс, содержащий реализацию алгоритма нисходящей сортировки слиянием.
 *
 * Имеет перегруженный метод (@code sort())
 * Наследует класс MergeSorting и реализует интерфейс SortingAlgorithm
 */
public class DescendingMergeSorting extends MergeSorting {

    /**
     * Реализация метода (@code sort()) интерфейса SortingAlgorithm.
     *
     * Здесь задается размер временного массива (@code temp) и вызывается
     * метод (@code sort(int,int)) с аргументами, указывающими на начало и
     * конец сортируемого массива.
     *
     * @param values сортируемый массив
     * @return остортированный массив
     */
    @Override
    public Comparable[] sort(Comparable[] values) {
        if (SortingExecutor.isArrayEqNull(values))
            throw new NullPointerException();
        temp = new Comparable[values.length];
        sort( 0, getArrLen() - 1);
        return values;
    }

    /**
     * Реализация алгоритма нисходящей сортировки слиянием.
     *
     * Метод модифицирует статическое поле (@code values)
     * класса (@code SortingExecutor).
     * @param lowestIndex наименьшей индекс массива
     * @param highestIndex наибольший индекс массива
     */
    private void sort(int lowestIndex, int highestIndex) {
        if (highestIndex<=lowestIndex) return;
        int middleIndex = lowestIndex + (highestIndex - lowestIndex)/2;
        sort(lowestIndex, middleIndex);
        sort(middleIndex + 1, highestIndex);
        merge(lowestIndex, middleIndex, highestIndex);
    }
}
