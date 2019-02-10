package inno.l2.homework.sorting.algs;

import inno.l2.homework.SortingExecutor;
import inno.l2.homework.sorting.SortingAlgorithm;

import static inno.l2.homework.SortingExecutor.*;

/**
 * Класс в методе (@code sort()) которого реализован усовершенствованный алгоритм сортировки вставками.
 * Реализует интерфейс SortingAlgorithm.
 */
public class ImprovedInsertionSorting implements SortingAlgorithm {

    /**
     * Усовершенствованная сортировка вставками.
     *
     * @param values сортируемый массив
     * @return отсортированный массив
     */
    @Override
    public Comparable[] sort(Comparable[] values) {
        if (SortingExecutor.isArrayEqNull(values))
            throw new NullPointerException();
        int exchanges = 0;
        for (int i = getArrLen() -1; i > 0; i--) {
            if (less(values[i], values[i-1])) {
                exchangeElements(values, i, i-1);
                exchanges++;
            }
        }
        if (exchanges == 0) return new Integer[0];

        for (int i = 2; i < getArrLen(); i++) {
            Comparable v = values[i];
            int j = i;
            while (less(v, values[j-1])) {
                values[j] = values[j-1];
                j--;
            }
            values[j] = v;
        }
        return values;
    }
}

