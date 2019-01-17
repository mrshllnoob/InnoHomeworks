package inno.l3.homework;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.TreeSet;

public final class MathBox<T extends Number> extends ObjectBox<T>{

    private Collection<T> boxedElements;

    public MathBox(T[] intArray) {
        setBoxedElements(new TreeSet<>(Arrays.asList(intArray)));
    }

    public static BigInteger summator(final Collection<? extends Number> values) {
        BigInteger result = BigInteger.ZERO;
        for(final Number elem : values)
            result = result.add(new BigInteger(String.valueOf(elem.longValue())));
        return result;
    }

    public static void showNumericCollectionElems(Collection<? extends Number> boxedElements) {
        for(Number elem : boxedElements)
            System.out.print(elem + " ");
        System.out.println();
    }

    public static Collection<? extends Number> splitter(final Collection<? extends Number> values, Integer delimiterValue) {
        Collection result = new ArrayList();
        for(Number elem : values)
            result.add((Integer)elem/delimiterValue);
        return result;
    }

    @Override
    public String toString() {
        String result = "MathBox object contains current collection of values:\n";
        for(Number elem : this.getBoxedElements())
            result += elem + " ";
        return result;
    }

    @Override
    public int hashCode() {
        int result = 0;
        int counter = 0;
        for (Number elem : this.boxedElements) {
            counter += 1;
            result += elem.intValue() + counter / Math.pow(counter,counter);
        }
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        return ((obj instanceof MathBox)
                && (((MathBox) obj).getBoxedElements().size()==this.boxedElements.size())
                && this.hashCode()==obj.hashCode());
    }

    public Collection<T> getBoxedElements() {
        return boxedElements;
    }

    public void setBoxedElements(Collection<T> boxedElements) {
        this.boxedElements = boxedElements;
    }

    public static void removeElementOnValue(Collection<? extends Number> coll, int value) {
        coll.removeIf((e)-> (Integer)e==value);
    }

    @Override
    public void addObject(T obj) {
        this.getBoxedElements().add(obj);
    }

    @Override
    public void deleteObject(T obj) {
        this.getBoxedElements().remove(obj);
    }

    @Override
    public void dump() {

    }

}
