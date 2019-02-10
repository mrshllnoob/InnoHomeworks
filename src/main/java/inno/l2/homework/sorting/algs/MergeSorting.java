package inno.l2.homework.sorting.algs;


import inno.l2.homework.sorting.SortingAlgorithm;

import static inno.l2.homework.SortingExecutor.*;

/**
 * Абстрактный класс для алгоритмов сортировки слиянием.
 *
 * Имеет метод (@code merge()) отвечающий за слияние временного (@code temp)
 * и сортируемого массивов.
 * Реализует интерфейс SortingAlgorithm.
 */
abstract class MergeSorting implements SortingAlgorithm {
    public Comparable[] temp;

    public final void merge(int lowestIndex, int middleIndex, int highestIndex) {
        int i = lowestIndex;
        int j = middleIndex + 1;
        for(int k=lowestIndex;k<=highestIndex;k++)
            temp[k] = getValues()[k];
        for(int k = lowestIndex;k<=highestIndex; k++)
            if (i>middleIndex)
                getValues()[k] = temp[j++];
            else if (j>highestIndex)
                getValues()[k] = temp[i++];
            else if (less(temp[j],temp[i]))
                getValues()[k] = temp[j++];
            else
                getValues()[k] = temp[i++];
    }
}
