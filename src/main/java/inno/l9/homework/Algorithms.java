package inno.l9.homework;

import java.util.function.UnaryOperator;

import static inno.l9.homework.Utility.*;

public class Algorithms {

    public static int[] insertionSorting(int[] arr) {
        UnaryOperator<int[]> insertionSorting = (array) -> {
            if (Utility.isNull(array))
                throw new NullPointerException();
            for(int i = 1; i< array.length; i++)
                for(int j=i;j>0 && Utility.less(array[j],array[j-1]);j--)
                    exchangeElements(array, j,j-1);
            return array;
        };
        return  insertionSorting.apply(arr);
    }

    public static int[] selectionSorting(int[] arr) {
        UnaryOperator<int[]> selectionSorting = (array) -> {
            if (Utility.isNull(array))
                throw new NullPointerException();
            for (int i = 0; i < array.length; i++) {
                int min = i;
                for (int j = i + 1; j < array.length; j++)
                    if (Utility.less(array[j], array[min]))
                        min = j;
                exchangeElements(array, i, min);
            }
            return array;
        };
        return selectionSorting.apply(arr);
    }

    public static void shellSorting(int[] arr) {
        UnaryOperator<int[]> shellSorting = (array) -> {
            if (Utility.isNull(array))
                throw new NullPointerException();
            int h = 1;
            while(h< array.length /3)
                h = 3*h + 1;

            while(h>=1) {
                for(int i = h; i< array.length; i++) {
                    for(int j=i;j>=h && Utility.less(array[j],array[j-h]);j-=h)
                        exchangeElements(array,j,j-h);
                }
                h = h/3;
            }
            return array;
        };
    }
}
