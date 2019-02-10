package inno.l2.homework.sorting.algs;

import inno.l2.homework.SortingExecutor;
import inno.l2.homework.sorting.SortingAlgorithm;

import static inno.l2.homework.SortingExecutor.*;

/**
 * Класс в методе (@code sort()) которого реализован алгоритм сортировки вставками.
 * Реализует интерфейс SortingAlgorithm.
 */
public class InsertionSorting implements SortingAlgorithm {

    /**
     * Сортировка вставками.
     *
     * @param values сортируемый массив
     * @return остортированный массив
     */
    @Override
    public Comparable[] sort(Comparable[] values) {
        if (SortingExecutor.isArrayEqNull(values))
            throw new NullPointerException();
        for(int i = 1; i< getArrLen(); i++)
            for(int j=i;j>0 && less(values[j],values[j-1]);j--)
                exchangeElements(values, j,j-1);
        return values;
    }
}


