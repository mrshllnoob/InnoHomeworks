package inno.l2.homework.sorting.algs;

import inno.l2.homework.SortingExecutor;
import inno.l2.homework.sorting.SortingAlgorithm;

import static inno.l2.homework.SortingExecutor.*;

/**
 * Класс в методе (@code sort()) которого реализован алгоритм сортировки Шелла.
 * Реализует интерфейс SortingAlgorithm.
 */
public class ShellSorting implements SortingAlgorithm {

    /**
     * Сортировка Шелла
     *
     * @param values сортируемый массив
     * @return остортированный массив
     */
    @Override
    public Comparable[] sort(Comparable[] values) {
        if (SortingExecutor.isArrayEqNull(values))
            throw new NullPointerException();
        int h = 1;
        while(h< getArrLen() /3)
            h = 3*h + 1;

        while(h>=1) {
            for(int i = h; i< getArrLen(); i++) {
                for(int j=i;j>=h && less(values[j],values[j-h]);j-=h)
                    exchangeElements(values,j,j-h);
            }
            h = h/3;
        }
        return values;
    }
}
