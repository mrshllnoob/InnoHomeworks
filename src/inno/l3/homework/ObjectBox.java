package inno.l3.homework;

import java.util.ArrayList;
import java.util.Collection;

/**
 * Класс содержащий списочный массив объектов типа Object
 * , методы управления данным списком и метод для его вывода
 * в консоль.
 *
 * @author Tsapin Anton
 *
 * @param <T> Тип элементов содержащихся в коллекции-поле класса
 */
public class ObjectBox<T extends Object> {

    private Collection<Object> objs = new ArrayList<>();

    /**
     * Добавление элемента в коллекцию-поле объекта (@code ObjectBox)
     *
     * @param objToAdd ссылка на добавляемый в коллекцию
     */
    public void addObject(T objToAdd) {
        this.objs.add(objToAdd);
    }

    /**
     * Удаление элемента коллекции-поля объекта (@code ObjectBox)
     *
     * @param objToDelete ссылка на удаляемый из коллекции элемент
     */
    public void deleteObject(T objToDelete) {
        if(this.objs.contains(objToDelete))
            this.objs.remove(objToDelete);
    }

    /**
     * Пошаговый вывод элементов коллекции в stdout
     */
    public void dump() {
        System.out.println("\n\nDump of " + this.getClass() + " :");
        for(Object obj : this.objs)
            System.out.print(obj + " ");
    }

}
