package inno.l3.homework;

import inno.l2.homework.SortingExecutor;

import java.math.BigInteger;
import java.util.Arrays;
import java.util.Collection;
import java.util.TreeSet;

/**
 * Класс (@code MathBox) содержит в себе TreeSet-коллекцию числовых значений
 * и методы для работы с данной коллекцией.
 *
 * (@code MathBox) класс наследует класс (@code ObjectBox) и переопределяет его методы,
 * позволяющие добавлять элемент и удалять элемент из поля-коллекции, а также выводить
 * содержимое этой коллекции в консоль.
 * Также в (@code MathBox) переопределены такие методы класса (@code Object),
 * как (@code toString()), (@code hashCode()) и (@code equals()).
 * Класс (@code MathBox) обобщен для работы с числовыми значениями в своей коллекции.
 * Для поля класса (@code MathBox) была выбрана TreeSet-коллекция, так как данная
 * коллекция соответствует условиям решаемой задачи (уникальность элементов и
 * коллекция должна быть отсортирована).
 * Класс (@code MathBox) сделан финальным, дабы хешкод создаваемого объекта
 * не был перезаписан.
 *
 * @param <T> Тип элементов содержащихся в коллекции-поле класса
 */
public class MathBox<T extends Number & Comparable> extends ObjectBox<T>{

    //private Collection<T> boxedElements;
    //private final Integer hashcode;

    /**
     * Конструктор класса. Внутри конструктора создается и
     * инициализируется объект TreeSet коллекции.
     * @param intArray массив числовых элементов
     */
    public MathBox(T[] intArray) {
        super(intArray);
        setBoxedElements(new TreeSet<>(Arrays.asList(intArray)));
    }

    /**
     * Статичный метод инкапсулирующий логику генерации
     * целочисленного массива средствами класса SortingExecutor
     * и последующей его передачи в конструктор для создания класса
     * (@code MathBox).
     *
     * @param arrLen длина генерируемого массива
     * @param maxRandom диапазон значений генерируемого массива
     * @return объект (@code MathBox)
     */
    public static MathBox createInstanceOfMathBox(int arrLen, int maxRandom) {
        int arrlen = SortingExecutor.setArrLen(arrLen);
        SortingExecutor.setRandomRange(maxRandom);
        return new MathBox(SortingExecutor.generateIntegersArray(arrlen));
    }

    /**
     * Рассчитывает сумму всех элементов массива.
     *
     * @param values коллекция числовых значений
     * @return сумма элементов коллекции
     */
    public BigInteger summator(Collection<T> values) {
        BigInteger result = BigInteger.ZERO;
        for(T elem : values)
            result = result.add(new BigInteger(String.valueOf(elem.longValue())));
        return result;
    }

    /**
     * Выводит в консоль содержимое числовой коллекции.
     *
     * @param boxedElements коллекция числовых элементов
     */
    public void showNumericCollectionElems(Collection<T> boxedElements) {
        for(T elem : boxedElements)
            System.out.print(elem + " ");
        System.out.println();
    }

    /**
     * Метод позволяет разделить каждый элемент числовой коллекции на
     * переданное целочисленное числовое значение
     *
     * @param values коллекция числовых элементов
     * @param delimiterValue целочисленный делитель
     * @return коллекция. содержащая результат работы метода
     */
    public Collection<? extends Number> splitter(final Collection<? extends Number> values, Integer delimiterValue) {
        Collection result = new TreeSet();
        for(Number elem : values)
            result.add((Integer)elem/delimiterValue);
        return result;
    }

    /**
     * Переопределенный метод класса (@code Object). Выводит элементы
     * поля-коллекции в строковом представлении.
     *
     * @return строковое представление класса (@code MathBox)
     */
    @Override
    public String toString() {
        String result = "MathBox object contains current collection of values:\n";
        for(Number elem : this.getBoxedElements())
            result += elem + " ";
        return result;
    }

    /**
     * Переопределенный метод класса (@code Object). Считает хешкод
     * для объекта класса (@code MathBox). Для классов с одинаковыми коллекциями
     * возвращает одинаковые хешкоды. Класс (@code MathBox) объявлен как final,
     * дабы хешкод созданного объекта инициализировался только один раз,
     * при создании.
     *
     * @return целочисленное значение
     */
    @Override
    public int hashCode() {
        if (this.hashcode != null)
            return this.hashcode;
        else {
            int result = 0;
            int counter = 0;
            for (Number elem : this.boxedElements) {
                counter += 1;
                result += elem.intValue() + counter / Math.pow(counter, counter);
            }
            return result;
        }
    }

    /**
     * Переопределенный метод класса (@code Object). Сравнивает объекты
     * и возвращает результат в виде булевых значений.
     *
     * @param obj сравниваемый объект
     * @return true - если объекты одинаковые, false - если разные
     */
    @Override
    public boolean equals(Object obj) {
        return ((obj instanceof MathBox)
                && (((MathBox) obj).getBoxedElements().size()==this.boxedElements.size())
                && this.hashCode()==obj.hashCode());
    }

    /**
     * Геттер поля (@code boxedElements).
     * @return поле-коллекция из (@code MathBox)
     */
    public Collection<T> getBoxedElements() {
        return this.boxedElements;
    }

    /**
     * Сеттер поля (@code boxedElements).
     *
     * @param boxedElements ссылка на присваемую коллекцию
     */
    public void setBoxedElements(Collection<T> boxedElements) {
        this.boxedElements = boxedElements;
    }

    /**
     *Удаление элемента из коллекции, если его значение совпадает
     * со значением числового параметра метода.
     *
     * @param value числовое значение, которое требуется удалить
     */
    public void removeIfExists(Number value) {
        this.getBoxedElements().removeIf((e) -> e==value);
    }

    /**
     * Переопределенный метод из класса (@code ObjectBox),
     * который добавляет объект обобщенного типа в поле-коллекцию.
     *
     * @param obj объект обобщенного по Number типа
     */
    @Override
    public void addObject(T obj) {
        this.getBoxedElements().add(obj);
    }

    /**
     * Переопределенный метод из класса (@code ObjectBox),
     * который удаляет объект обобщенного типа из поля-коллекции.
     *
     * @param obj объект обобщенного по Number типа
     */
    @Override
    public void deleteObject(T obj) {
        this.removeIfExists(obj);
    }

    /**
     * Вывод содержимого коллекции в stdout. Переопределяет
     * метод из класса (@code ObjectBox).
     */
    @Override
    public void dump() {
        System.out.println(this.toString());
    }

}
