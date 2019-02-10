package inno.l2.homework.sorting.algs;

import inno.l2.homework.SortingExecutor;

import static inno.l2.homework.SortingExecutor.getArrLen;

/**
 * Класс, содержащий реализацию алгоритма восходящей сортировки слиянием.
 *
 * Имеет перегруженный метод (@code sort())
 * Наследует класс MergeSorting и реализует интерфейс SortingAlgorithm
 */
public class AscendingMergeSorting extends MergeSorting {
    /**
     * Реализация алгоритма нисходящей сортировки слиянием.
     *
     * @param values сортируемый массив
     * @return остортированный массив
     */
    @Override
    public Comparable[] sort(Comparable[] values) {
        if (SortingExecutor.isArrayEqNull(values))
            throw new NullPointerException();
        temp = new Comparable[getArrLen()];
        for(int subArraySize = 1; subArraySize< getArrLen(); subArraySize += subArraySize)
            for(int lowestIndex = 0; lowestIndex < getArrLen() - subArraySize; lowestIndex += subArraySize*2)
                merge(lowestIndex,
                        lowestIndex+subArraySize-1, Math.min(lowestIndex + subArraySize*2-1, getArrLen() -1));
        return values;
    }
}
