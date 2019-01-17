package inno.l2.homework;

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

    private static Comparable[] values;
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
        values = generateIntegersArray(getArrLen());
        show(values);
        Comparable[] sortedValues = alg.sort(values);
        assert isSorted(values);
        show(values);
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

    /**
     * Класс в методе (@code sort()) которого реализован алгоритм сортировки вставками.
     * Реализует интерфейс SortingAlgorithm.
     */
    public static class InsertionSorting implements SortingAlgorithm {

        /**
         * Сортировка вставками.
         *
         * @param values сортируемый массив
         * @return остортированный массив
         */
        @Override
        public Comparable[] sort(Comparable[] values) {
            for(int i = 1; i< getArrLen(); i++)
                for(int j=i;j>0 && less(values[j],values[j-1]);j--)
                    exchangeElements(values, j,j-1);
            return values;
        }
    }

    /**
     * Класс в методе (@code sort()) которого реализован усовершенствованный алгоритм сортировки вставками.
     * Реализует интерфейс SortingAlgorithm.
     */
    public static class ImprovedInsertionSorting implements SortingAlgorithm {

        /**
         * Усовершенствованная сортировка вставками.
         *
         * @param values сортируемый массив
         * @return отсортированный массив
         */
        @Override
        public Comparable[] sort(Comparable[] values) {
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

    /**
     * Класс в методе (@code sort()) которого реализован алгоритм сортировки выбором.
     * Реализует интерфейс SortingAlgorithm.
     */
    public static class SelectionSorting implements SortingAlgorithm {

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

    /**
     * Класс в методе (@code sort()) которого реализован алгоритм сортировки Шелла.
     * Реализует интерфейс SortingAlgorithm.
     */
    public static class ShellSorting implements SortingAlgorithm {

        /**
         * Сортировка Шелла
         *
         * @param values сортируемый массив
         * @return остортированный массив
         */
        @Override
        public Comparable[] sort(Comparable[] values) {
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

    /**
     * Класс, содержащий реализацию алгоритма нисходящей сортировки слиянием.
     *
     * Имеет перегруженный метод (@code sort())
     * Наследует класс MergeSorting и реализует интерфейс SortingAlgorithm
     */
    public static class DescendingMergeSorting extends MergeSorting {

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

    /**
     * Класс, содержащий реализацию алгоритма восходящей сортировки слиянием.
     *
     * Имеет перегруженный метод (@code sort())
     * Наследует класс MergeSorting и реализует интерфейс SortingAlgorithm
     */
    public static class AscendingMergeSorting extends MergeSorting {
        /**
         * Реализация алгоритма нисходящей сортировки слиянием.
         *
         * @param values сортируемый массив
         * @return остортированный массив
         */
        @Override
        public Comparable[] sort(Comparable[] values) {
            temp = new Comparable[getArrLen()];
            for(int subArraySize = 1; subArraySize< getArrLen(); subArraySize += subArraySize)
                for(int lowestIndex = 0; lowestIndex < getArrLen() - subArraySize; lowestIndex += subArraySize*2)
                    merge(lowestIndex,
                            lowestIndex+subArraySize-1, Math.min(lowestIndex + subArraySize*2-1, getArrLen() -1));
            return values;
        }
    }

    /**
     * Абстрактный класс для алгоритмов сортировки слиянием.
     *
     * Имеет метод (@code merge()) отвечающий за слияние временного (@code temp)
     * и сортируемого массивов.
     * Реализует интерфейс SortingAlgorithm.
     */
    private abstract static class MergeSorting implements SortingAlgorithm{
        public Comparable[] temp;

        public final void merge(int lowestIndex, int middleIndex, int highestIndex) {
            int i = lowestIndex;
            int j = middleIndex + 1;
            for(int k=lowestIndex;k<=highestIndex;k++)
                temp[k] = values[k];
            for(int k = lowestIndex;k<=highestIndex; k++)
                if (i>middleIndex)
                    values[k] = temp[j++];
                else if (j>highestIndex)
                    values[k] = temp[i++];
                else if (less(temp[j],temp[i]))
                    values[k] = temp[j++];
                else
                    values[k] = temp[i++];
        }
    }
}
