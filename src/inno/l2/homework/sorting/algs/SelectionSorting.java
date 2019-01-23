package inno.l2.homework.sorting.algs;

import inno.l2.homework.SortingExecutor;
import inno.l2.homework.sorting.SortingAlgorithm;

import static inno.l2.homework.SortingExecutor.*;

/**
 * Класс в методе (@code sort()) которого реализован алгоритм сортировки выбором.
 * Реализует интерфейс SortingAlgorithm.
 */
public class SelectionSorting implements SortingAlgorithm {

    /**
     * Сортировка выбором.
     */
    /**
     * Сортировка выбором.
     *
     * @param values сортируемый массив
     * @return остортированный массив
     */
    @Override
    public Comparable[] sort(Comparable[] values) {
        if (SortingExecutor.isArrayEqNull(values))
            throw new NullPointerException();
        for(int i = 0; i< getArrLen(); i++) {
            int min = i;
            for(int j = i+1; j< getArrLen(); j++)
                if(less(values[j], values[min]))
                    min = j;
            exchangeElements(values,i,min);
        }
        return values;
    }
}
