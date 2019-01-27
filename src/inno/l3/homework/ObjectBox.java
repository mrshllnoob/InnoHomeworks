package inno.l3.homework;

import java.util.Collection;
import java.util.TreeSet;

/**
 * Класс содержащий списочный массив объектов типа Object
 * , методы управления данным списком и метод для его вывода
 * в консоль.
 *
 *
 * @param <T> Тип элементов содержащихся в коллекции-поле класса
 *
 * @author Tsapin Anton
 */
public class ObjectBox<T extends Comparable> {

    public Collection<T> boxedElements = new TreeSet<>();
    public final Integer hashcode;

    public ObjectBox(T[] intArray) {
        this.hashcode = hashCode();
    }

    /**
     * Добавление элемента в коллекцию-поле объекта (@code ObjectBox)
     *
     * @param objToAdd ссылка на добавляемый в коллекцию
     */
    public void addObject(T objToAdd) {
        this.boxedElements.add(objToAdd);
    }

    /**
     * Удаление элемента коллекции-поля объекта (@code ObjectBox)
     *
     * @param objToDelete ссылка на удаляемый из коллекции элемент
     */
    public void deleteObject(T objToDelete) {
        if(this.boxedElements.contains(objToDelete))
            this.boxedElements.remove(objToDelete);
    }

    /**
     * Пошаговый вывод элементов коллекции в stdout
     */
    public void dump() {
        System.out.println("\n\nDump of " + this.getClass() + " :");
        for(Object obj : this.boxedElements)
            System.out.print(obj + " ");
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

}
