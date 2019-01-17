package inno.l3.homework;

import java.util.ArrayList;
import java.util.Collection;

public class ObjectBox<T extends Object> {

    private Collection<Object> objs = new ArrayList<>();

    public void addObject(T objToAdd) {
        this.objs.add(objToAdd);
    }

    public void deleteObject(T objToDelete) {
        if(this.objs.contains(objToDelete))
            this.objs.remove(objToDelete);
    }

    public void dump() {
        System.out.println("\n\nDump of " + this.getClass() + " :");
        for(Object obj : this.objs)
            System.out.print(obj + " ");
    }

}
